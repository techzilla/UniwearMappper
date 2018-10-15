#!/usr/bin/env bash

DIR="$(dirname "$0")"
PROPERTIES="$DIR/../config/application.properties"
if [ ! -r "$PROPERTIES" ]; then
	echo "Cannot read $PROPERTIES"
	exit 1
fi

getProperty() {
	local key=$1
	local value

	value=$(grep -m1 "$key" "$PROPERTIES" | cut -d'=' -f2)
	echo "$value"
}

db_user=$(getProperty db.batch.user)
db_pass=$(getProperty db.batch.password)
db_host=$(getProperty db.batch.url |  tr -s '/' ':' | cut -d':' -f 3)
db_port=$(getProperty db.batch.url |  tr -s '/' ':' | cut -d':' -f 4)
db_name=$(getProperty db.batch.url |  tr -s '/' ':' | cut -d':' -f 5)


mysqlExecFile() {
	local file="$1"
	local conf

	conf="$(mktemp)"
	chmod 600 "${conf}"

	printf "%s\n" \
		"[client]" \
		"user=${db_user}" \
		"password=${db_pass}" \
		"host=${db_host}" \
		"port=${db_port}" \
		"database=${db_name}" \
		>"${conf}"

	if result=$(HOME="/sys" mysql --defaults-file="${conf}" < "${file}"); then
		status=0
	else
		echo "$result"
		status=$?
	fi

	rm "${conf}"
	return $status

}
