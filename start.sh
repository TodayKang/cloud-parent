#!/bin/sh

app=provider-eureka.jar

echo '************ deploy start, package:' ${app} '************'
#stop PID
echo '---- try stop' ${app} '----'
tpid=$(ps -ef | grep $app | grep -v grep | grep -v kill | awk '{ print $2 }')
if [[ -n ${tpid} ]]; then
  echo '----' ${app} 'running, PID=' ${tpid} ', do stop process ....'
  kill -15 $tpid
  sleep 5
else
  echo '----' ${app} 'not running ....'
fi

# kill PID
tpid=$(ps -ef | grep $app | grep -v grep | grep -v kill | awk '{ print $2 }')
if [[ -n ${tpid} ]]; then
  echo '----' ${app} 'still running, PID=' ${app} ', do kill process....'
  kill -9 $tpid
  sleep 5
else
    echo '----' ${app} 'stop success ...'
fi

# rm PID
tpid=$(ps -ef | grep $app | grep -v grep | grep -v kill | awk '{ print $2 }')
if [[ -n ${tpid} ]]; then
  echo '----' ${app} 'cannot kill, PID=' ${app} ', do rm process....'
  rm -f $tpid
  sleep 3
else
    echo '----' ${app} 'is NOT RUNNING ....'
fi

echo '************ begin to deploy ************'
nohup java -jar ./$app --server.port=2001 &
nohup java -jar ./$app --server.port=2002 &
nohup java -jar ./$app --server.port=2003 &
sleep 10

tpid=$(ps -ef | grep $app | grep -v grep | grep -v kill | awk '{ print $2 }')
echo '************ deploy success, package:' ${app} ',PID:' ${tpid} '************'
