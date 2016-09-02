#!/bin/bash
mvn package
cp target/ROLL*dependencies.jar ./ROLL.jar
java -jar ROLL.jar
