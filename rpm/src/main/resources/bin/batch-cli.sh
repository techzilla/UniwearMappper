#!/usr/bin/env bash

DIR="$(dirname "$0")"

RUN_JAVA_OPTS="$RUN_JAVA_OPTS -DlogDir=${DIR}/../logs" "$DIR/run-java.sh" biz.uniwear.batch.wrapper.MappingCli "$@"