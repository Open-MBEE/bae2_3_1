import socket
import struct
import binascii
import sys

class OneWaySocket:
    """
    Class to perform client side socket connection
    """
    debugMode = False

    def __init__(self, host_addr, port, send, mode=False):
        """
        Connect up the socket here.
        """
        self.debugMode = mode
        self.client_socket = None
        self.sock = None
        self.endianness = [
            ('@', 'native, native'),
            ('=', 'native, standard'),
            ('<', 'little-endian'),
            ('>', 'big-endian'),
            ('!', 'network'),
            ]
        self.endiannessIndex = 0
        #self.endiannessTest = (1, 'ab', 2.7)
        #self.endiannessFormat = 'I 2s f'
        self.endiannessTest = 1
        self.endiannessFormat = 'i'
        try:
            if send:
                self.debugPrint( "Setting up socket server on host addr %s, port %d\n" % (host_addr, port) )
            else:
                self.debugPrint( "Connecting to host addr %s, port %d\n" % (host_addr, port) )

            # Create the socket
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

            # Connect to server
            if send:
                self.sock.connect((host_addr, port))
                self.debugPrint( "connected maybe" )
            else:
                self.sock.bind((host_addr, port))
                self.sock.listen(5)
                self.client_socket, address = self.sock.accept()
                self.debugPrint( "I got a connection from " + str(address) )

        except IOError as e:
            print "EXCEPTION: Could not connect to socket at host addr %s, port %s" % (host_addr, port)

    def endianGet( self ):
        if type(self.endiannessTest) == tuple or type(self.endiannessTest) == list:
            packedValues = [ struct.Struct(code + ' ' + self.endiannessFormat ).pack(*self.endiannessTest) for code, name in self.endianness ]
        else:
            packedValues = [ struct.Struct(code + ' ' + self.endiannessFormat ).pack(self.endiannessTest) for code, name in self.endianness ]
        minPackLength = min([len(x) for x in packedValues])
        msg = self.receiveInPieces(minPackLength)
        for i in range(len(self.endianness)):
            if packedValues[i][0:minPackLength] == msg:
                self.endiannessIndex = i
                break
        # Need to eat any additional characters coming over the socket since we
        # just got the minimum.
        if len(packedValues[self.endiannessIndex]) > minPackLength:
            msg = self.receiveInPieces(len(packedValues[self.endiannessIndex]) - minPackLength)
        self.debugPrint( "endianness is " + str(self.endianness[self.endiannessIndex]) )

    def endianSend( self ):
        self.sock.send(struct.pack(self.endiannessFormat, self.endiannessTest))

    def unpack(self, formatString, data):
        s = struct.Struct( self.endianness[self.endiannessIndex][0] + ' ' + formatString )
        msg = s.unpack(data)
        return msg

    def receive(self):
        # First, get the format of the data to be sent.
        # Get a 4 byte int for the length of the format string.
        msg = self.receiveInPieces(4)
        self.debugPrint( "received packed integer " + binascii.hexlify(msg) + " = '" + msg + "'" )
        length=self.unpack("i", msg)
        length = length[0]
        if length == -1:
            self.debugPrint( "got quit signal" )
            return None
        self.debugPrint( "got length of format string: " + str(length) )
        # Receive the format string.
        formatString = self.receiveInPieces(length)
        self.debugPrint( "got formatString: '" + formatString + "'" )

        # Now get the message body.
        # Get a 4 byte int for the message length.
        msg = self.receiveInPieces(4)
        self.debugPrint( "received packed integer " + binascii.hexlify(msg) + " = '" + msg + "'" )
        length=self.unpack("i", msg)
        length = length[0]
        self.debugPrint( "got length of data: " + str(length) )
        msg = self.receiveInPieces(length)
        self.debugPrint( "got packed data: " + binascii.hexlify(msg) + " = '" + msg + "'" )
        d = self.unpack(formatString, msg)
        self.debugPrint( "got data: " + str(d) )
        return d

    def receiveInPieces(self, length):
        msg = ''
        while len(msg) < length:
            chunk = self.client_socket.recv(length-len(msg))
            if chunk == '':
                raise RuntimeError("socket connection broken")
            msg = msg + chunk
            self.debugPrint( "total length = " + str(length) + ", msg so far = '" + msg + "'" )
        return msg

    def send(self, formatString, data):
        msg = struct.pack("i",len(formatString))
        self.debugPrint( "sending packed length of format string: " + binascii.hexlify(msg) + " = " + msg )
        self.sock.send(msg)
        self.debugPrint( "sending format string: " + formatString )
        self.sock.send(formatString)
        msg = struct.pack("i",len(data))
        self.debugPrint( "sending packed length of data: " + binascii.hexlify(msg) + " = " + msg )
        self.sock.send(msg)
        msg = struct.pack(formatString, data)
        self.debugPrint( "sending packed data " + binascii.hexlify(msg) + " = " + msg )
        self.sock.send(msg)

    def sendQuit( self ):
        msg = struct.pack("i",-1)
        self.debugPrint( "sending packed quit signal: " + binascii.hexlify(msg) + " = " + msg )
        self.sock.send(msg)

    def close( self ):
        if self.client_socket != None:
            self.client_socket.close()
        self.sock.close()
    
    def debugPrint(self, s):
        if self.debugMode:
            print s

#
# Main test
#
def main( argv ):
    port = 5432

    print "argv = " + str(argv)
    print "argv[1] = " + str(argv[1])
    
    recv = True
    
    if len(argv) > 1:
        if argv[1] == "send":
            recv = False
    s = OneWaySocket('127.0.0.1', port, not recv)

    if recv:
        s.endianGet()
        for i in range(3):
            x = s.receive()
            if x == None:
                break
            print "got '" + str(x) + "'"
    else:
        s.endianSend()
        s.send("5s","Hello")
        s.sendQuit()
    s.close()

    # print "Position: %f %f %f\n" % (x_est[13], x_est[14], x_est[15])
    # print "Velocity: %f %f %f\n" % (x_est[7], x_est[8], x_est[9])

if __name__ == "__main__":
    main(sys.argv)
