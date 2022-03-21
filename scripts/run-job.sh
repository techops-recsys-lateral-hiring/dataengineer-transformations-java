#!/bin/bash

set -e

#OUTPUT_PATH="./output"
JAR=build/libs/dataengineer.jar


rm -rf $OUTPUT_PATH

./gradlew clean build -x checkStyleMain -x checkStyleTest

spark-submit \
    --master local \
    --class $JOB \
    $JAR \
    $INPUT_FILE_PATH \
    $OUTPUT_PATH

