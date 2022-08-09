#!/bin/bash

set -e

./gradlew clean test -x checkStyleMain -x checkStyleTest