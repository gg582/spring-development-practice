#!/bin/bash

PROFILE=${1:-aes}
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.app.controller.RestController" -Dspring.profiles.active="$PROFILE"
