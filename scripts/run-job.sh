#!/bin/bash

set -e

JAR=build/libs/dataengineer.jar

./gradlew clean build -x checkStyleMain -x checkStyleTest

spark-submit \
    --master local \
    --class $JOB \
    $JAR

