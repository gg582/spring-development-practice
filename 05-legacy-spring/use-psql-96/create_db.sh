#!/bin/sh

if [ $(whoami) != "root" ]
then
	echo "Please run as root."
	exit 1
fi

docker run --name legacy-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6
