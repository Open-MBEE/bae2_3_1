package gov.nasa.jpl.ae.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public abstract class CaptureStdoutStderr {
    public abstract Object run();

    public Object result = null;

    PrintStream outPrintStream = null;
    PrintStream errPrintStream = null;

    public ByteArrayOutputStream baosOut = null;
    public ByteArrayOutputStream baosErr = null;

    public CaptureStdoutStderr() {
        baosOut = new ByteArrayOutputStream();
        baosErr = new ByteArrayOutputStream();
        outPrintStream = new PrintStream(baosOut);
        errPrintStream = new PrintStream(baosErr);
        captureRun();
    }

    protected void captureRun() {
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        System.out.flush();
        System.err.flush();
        System.setOut(outPrintStream);
        System.setErr(errPrintStream);
        result = run();
        System.out.flush();
        System.err.flush();
        System.setOut( oldOut );
        System.setErr( oldErr );
    }

    public CaptureStdoutStderr(String outFileName, String errFileName) {
        try {
            outPrintStream = new PrintStream( outFileName );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            baosOut = new ByteArrayOutputStream();
            outPrintStream = new PrintStream(baosOut);
        }
        try {
            errPrintStream = new PrintStream(errFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            baosErr = new ByteArrayOutputStream();
            errPrintStream = new PrintStream(baosErr);
        }
        captureRun();
    }

}
