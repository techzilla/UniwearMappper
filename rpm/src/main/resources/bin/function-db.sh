#!/usr/bin/env bash

DIR="${DIR:-$(dirname "$0")}"
PROPERTIES="$DIR/../config/application.conf"
if [ ! -r "$PROPERTIES" ]; then
	echo "Cannot read $PROPERTIES"
	exit 1
fi

__TMPDIR=${__TMPDIR:-$(mktemp -d)}
trap "rm -rf '$__TMPDIR'" EXIT


mkTemp() {
    local tempfile

    tempfile=$( mktemp -p "$__TMPDIR" )
    retvar=$?

    echo "$tempfile"

  	return $retvar

}

getProperty() {
	local key=$1
	local value

	value=$(grep -m1 "$key" "$PROPERTIES" | cut -d'=' -f2 | sed 's/"\(.*\)"/\1/')
	echo "$value"
}

db_user=$(getProperty db.batch.user)
db_pass=$(getProperty db.batch.password)
db_host=$(getProperty db.batch.url | tr -s '/' ':' | cut -d':' -f 3)
db_port=$(getProperty db.batch.url | tr -s '/' ':' | cut -d':' -f 4)
db_name=$(getProperty db.batch.url | tr -s '/' ':' | cut -d':' -f 5)


mysqlExecFile() {
	local file="$1"
	local conf

	conf="$(mkTemp)"
	chmod 644 "${conf}"

	printf "%s\\n" \
		"[client]" \
		"default-character-set=utf8mb4" \
		"user=${db_user}" \
		"password=${db_pass}" \
		"host=${db_host}" \
		"port=${db_port}" \
		"database=${db_name}" \
		>"${conf}"

	if result=$(HOME="/sys" mysql --defaults-extra-file="${conf}" < "${file}"); then
        echo "$result"
		retvar=0
	else
		retvar=$?
	fi

	rm "${conf}"

	return $retvar

}
