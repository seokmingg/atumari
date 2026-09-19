#!/bin/sh
set -eu

cd "$(dirname "$0")/.."

docker compose up -d spring nginx
docker compose run --rm --entrypoint /bin/sh certbot -c '
    if [ -z "$CERTBOT_EMAIL" ]; then
        echo "deploy/.env의 CERTBOT_EMAIL을 입력하세요." >&2
        exit 1
    fi

    certbot certonly \
        --webroot \
        --webroot-path=/var/www/certbot \
        --email "$CERTBOT_EMAIL" \
        --agree-tos \
        --no-eff-email \
        -d "$DOMAIN"
'
docker compose restart nginx
