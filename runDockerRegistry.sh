docker run -d \
  -p 5000:5000 \
  --restart=always \
  --name registry \
  -v /auth:/auth \
  -e "REGISTRY_AUTH=htpasswd" \
  -e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
  -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
  -v /etc/letsencrypt/live/simia.coupler.app/fullchain.pem:/certs/fullchain.pem \
  -v /etc/letsencrypt/live/simia.coupler.app/privkey.pem:/certs/privkey.pem \
  -e REGISTRY_HTTP_TLS_CERTIFICATE=/certs/fullchain.pem \
  -e REGISTRY_HTTP_TLS_KEY=/certs/privkey.pem \
  -v /storage-docker:/var/lib/registry \
  registry:2