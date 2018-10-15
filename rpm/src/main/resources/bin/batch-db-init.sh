#!/usr/bin/env bash

USER="${USER:-root}"
PASS="${PASS:-root}"

DIR="$(dirname "$0")"
# shellcheck source=/dev/null
. "$DIR/function-db.sh"

mysql -u"$USER" -p"$PASS" <<EOF
CREATE DATABASE $db_name;
GRANT ALL PRIVILEGES ON $db_name.* TO '$db_user'@'localhost' IDENTIFIED BY '$db_pass';
FLUSH PRIVILEGES;
EOF


mysqlExecFile "$DIR/../setup/sql/schema-mysql.sql"
