#!/bin/bash

PROFILE=${1:-aes}
mvn clean compile
mvn jetty:run -Dspring.profiles.active="$PROFILE"
