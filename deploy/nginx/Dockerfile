FROM nginx:alpine

RUN rm -f /etc/nginx/conf.d/default.conf

COPY conf.d/default-http.conf.template /opt/atumari/nginx/atumari-http.conf.template
COPY conf.d/default-https.conf.template /opt/atumari/nginx/atumari-https.conf.template
COPY docker-entrypoint.d/40-select-atumari-config.sh /docker-entrypoint.d/40-select-atumari-config.sh
COPY docker-entrypoint.d/50-reload-certificates.sh /docker-entrypoint.d/50-reload-certificates.sh

RUN chmod +x \
    /docker-entrypoint.d/40-select-atumari-config.sh \
    /docker-entrypoint.d/50-reload-certificates.sh
