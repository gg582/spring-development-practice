#!/bin/bash
mvn clean compile
mvn package

echo "[INFO] Downloading jetty-runner.jar..."
curl -L -o jetty-runner.jar https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.4.51.v20230217/jetty-runner-9.4.51.v20230217.jar
