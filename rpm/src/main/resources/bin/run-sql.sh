#!/usr/bin/env bash

DIR="$(dirname "$0")"
# shellcheck source=/dev/null
. "$DIR/function-db.sh"

__tempfile="$(mkTemp)"
echo "$*" > "$__tempfile"

mysqlExecFile "$__tempfile"
