#!/bin/bash

#A tool to send messages via serial port
#Author Cong Bao, Xingren Guan

port="/dev/ttyS0"
delay="1000"
size="1000"
number="50"

Help () {
  echo "Usage: rfi [-options] [args...]"
  echo "where options include:"
  echo "  -p  the port used to write codes"
  echo "      Default value is /dev/ttyS0"
  echo "  -d  the delay time between two pieces of messages in ms"
  echo "      Default value is 1000"
  echo "  -s  the size of each message in byte"
  echo "      Default value is 1000"
  echo "  -n  the number of messages to send"
  echo "      Default value is 50"
  echo "  -h  show this help"
}

if [ $# -eq 0 ]
  then Help
else

while getopts "p:d:s:n:h" arg
do
  case $arg in
    p) port=$OPTARG;;
    d) delay=$OPTARG;;
    s) size=$OPTARG;;
    n) number=$OPTARG;;
    h) Help
       exit 0
       ;;
    ?) echo "use -h to see help";;
  esac
done

echo
echo "Params:"
echo "port=$port; delay=$delay; size=$size; number=$number"
echo
echo "Start sending messages..."
`java -jar /home/bao/DoStool/rfiAtk/RFI.jar $port $delay $size $number`
echo "Done. Thanks!"

fi
