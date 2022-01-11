#!/bin/bash

PID=`ps -e | grep java | awk '{print $1}'`

if [ $PID ]
    then
        kill -9 $PID    // 이미 서버가 실행중이라면 종료시킨다.
fi
TRANSFERRED_FILE=`find ~/build/libs/ ! -name magnet-0.0.1-SNAPSHOT.jar | grep jar // 받아온 파일을 검색
`
mv $TRANSFERRED_FILE ~/build/libs/magnet-0.0.1-SNAPSHOT.jar // 서비스용 파일에 덮어씌움

java -jar -Dserver.port=8080 ~/build/libs/smagnet-0.0.1-SNAPSHOT.jar
// 서버를 실행시키고 로그는 log.txt에 모은다.