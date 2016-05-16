FROM nginx

COPY default.nginx.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf

COPY index.html /usr/share/nginx/html
COPY dist/ /usr/share/nginx/html/dist/