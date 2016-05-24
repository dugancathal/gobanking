FROM node:5-onbuild

COPY . /usr/src/app/
WORKDIR /usr/src/app

RUN npm start
CMD npm test