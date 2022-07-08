#!/bin/bash

set -e

./gradlew clean integrationTest -x checkStyleMain -x checkStyleTest