#!/bin/sh

BATCH_USER='batch'
BATCH_GROUP='batch'
BATCH_HOME='/opt/batch'


! getent group "$BATCH_GROUP" && {
    groupadd -r "$BATCH_GROUP"
}

! getent passwd "$BATCH_USER" && {

    useradd -r -d "$BATCH_HOME" -g "$BATCH_GROUP" "$BATCH_USER"
}

cat << EOF > /etc/profile.d/batch.sh

export BATCH_HOME="$BATCH_HOME"
PATH="\$PATH:\$BATCH_HOME/bin"

EOF
