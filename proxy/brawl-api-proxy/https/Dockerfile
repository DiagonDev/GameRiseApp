FROM nginx:latest

COPY letsencrypt/ /etc/letsencrypt/
COPY flask_proxy /etc/nginx/sites-available/flask_proxy
COPY nginx.conf /etc/nginx/nginx.conf

RUN mkdir /etc/nginx/sites-enabled
RUN ln -s /etc/nginx/sites-available/flask_proxy /etc/nginx/sites-enabled/flask_proxy
