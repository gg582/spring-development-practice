#!/bin/bash

## 사용자 입력에 따른 스프링 프로파일 전환
ALGO=$1
if [ ALGO == "chacha" ]; then
	PROFILE=${1:-chacha}
elif [ ALGO == "seed" ]; then
	PROFILE=${1:-seed}
else
	PROFILE=${1:-aes}
fi

java -cp "target/*:bin" -Dspring.profiles.active="$PROFILE" -jar jetty-runner.jar target/encryption-dashboard.war
