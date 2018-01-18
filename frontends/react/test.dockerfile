FROM node:9.4.0-onbuild

COPY . /usr/src/app/
WORKDIR /usr/src/app

RUN npm start
CMD npm test