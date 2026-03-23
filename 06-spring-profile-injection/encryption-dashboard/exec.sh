#!/bin/bash

java -cp "target/*:bin" -jar jetty-runner.jar target/encryption-dashboard.war
