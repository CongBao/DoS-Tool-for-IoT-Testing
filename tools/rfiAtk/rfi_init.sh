#!/bin/bash

#A tool to write codes into Arduino
#Author Cong Bao, Xingren Guan

port="/dev/ttyS0"
hexFile="/home/bao/DoStool/rfiAtk/Broker.ino.hex"

Help () {
  echo "Usage: rfi_init [-options] [args...]"
  echo "where options include:"
  echo "  -p  the port used to write codes"
  echo "      Default value is /dev/ttyS0"
  echo "  -f  the file to write"
  echo "      Default value is /home/bao/DoStool/rfiAtk/Broker.ino.hex"
  echo "  -h  show this help"
}

echo "Please make sure avrdude is installed."

if [ $# -eq 0 ]
  then Help
else

while getopts "p:f:h" arg
do
  case $arg in
    p) port=$OPTARG;;
    f) hexFile=$OPTARG;;
    h) Help
       exit 0
       ;;
    ?) echo "use -h to see help";;
  esac
done

echo
echo "Params:"
echo "port=$port; file=$hexFile"
echo
echo "Start writing codes..."
`avrdude -c avrisp -p m328p -P $port -U flash:w:$hexFile:i -F`
echo "Done. Thanks!"

fi
