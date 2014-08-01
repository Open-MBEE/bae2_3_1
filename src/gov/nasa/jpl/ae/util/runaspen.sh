cd $ASPEN_ROOT
source setup.sh
aspen -j -m $1 -i $2 -f simple > aspen.log
