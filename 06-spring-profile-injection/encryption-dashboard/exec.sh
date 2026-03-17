#!/bin/bash
PROFILE=${1:-aes}

mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.App" -Dspring.profiles.active="$PROFILE"
