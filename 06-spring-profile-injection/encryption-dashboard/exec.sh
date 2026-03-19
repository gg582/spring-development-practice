#!/bin/bash

ALGO=$1

if [ ALGO == "chacha" ]; then
	PROFILE=${1:-chacha}
elif [ ALGO == "seed" ]; then
	PROFILE=${1:-seed}
else
	PROFILE=${1:-aes}
fi
mvn clean compile
mvn jetty:run -Dspring.profiles.active="$PROFILE"
