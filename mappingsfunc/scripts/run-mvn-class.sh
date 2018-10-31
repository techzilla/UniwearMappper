#!/bin/sh

CLASS_NAME="${1%%.class}"
shift
if [ -z "$CLASS_NAME" ]; then
  echo "Class name required"
  exit 1
fi

#
export MAVEN_OPTS='-XX:+TieredCompilation -XX:TieredStopAtLevel=1'



#
get_pom_info() {
    __exp="$1"
    mvn help:evaluate -Dexpression="$__exp" | grep -Ev '(^\[|Download\w+:)'
}

exec_mvn_class() {
    __class="$1"
    shift
    export MAVEN_OPTS='-Dorg.slf4j.simpleLogger.defaultLogLevel=warn'
    mvn exec:java -Dexec.mainClass="$__class" -Dexec.classpathScope=test -Dexec.args="" -Dexec.arguments="$@"
    unset MAVEN_OPTS

}

CLASS_DIR="$(get_pom_info "project.build.outputDirectory")"


CLASS_FILE="$(find "$CLASS_DIR" -type f -iname "${CLASS_NAME}.class" -print -quit)"
if [ -z "$CLASS_FILE" ]; then
  echo "Class not found"
  exit 1
fi

CLASS="$( echo "${CLASS_FILE##${CLASS_DIR}/}" | tr '/' '.')"

exec_mvn_class "${CLASS%%.class}" "$@"
