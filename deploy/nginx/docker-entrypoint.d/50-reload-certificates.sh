#!/bin/sh

(
    while sleep 6h; do
        nginx -s reload
    done
) &
