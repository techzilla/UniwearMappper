#!/bin/sh

DIR="$(dirname "$0")"
find "$DIR/../target/rpm/bin" -type f -name '*.sh' -exec chmod +x {} +
