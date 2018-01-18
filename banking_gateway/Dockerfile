FROM ruby:2.5.0

EXPOSE 80

COPY Gemfile* /usr/src/app/
WORKDIR /usr/src/app
RUN bundle install --deployment -j4

COPY . /usr/src/app

CMD rails server -b 0.0.0.0 -p 80