#!/bin/sh
set -eu

certificate_path="/etc/letsencrypt/live/atumary.com/fullchain.pem"

if [ -f "$certificate_path" ]; then
    cp /opt/atumari/nginx/atumari-https.conf.template /etc/nginx/conf.d/atumari.conf
    echo "HTTPS certificate found; enabling HTTPS configuration."
else
    cp /opt/atumari/nginx/atumari-http.conf.template /etc/nginx/conf.d/atumari.conf
    echo "HTTPS certificate not found; starting with HTTP configuration."
fi
