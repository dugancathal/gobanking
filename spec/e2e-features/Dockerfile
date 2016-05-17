FROM ruby:2.3.0

RUN apt-get update -y
RUN apt-get install -y \
      libqt5webkit5-dev qt5-default \
      gstreamer1.0-plugins-base gstreamer1.0-tools gstreamer1.0-x \
      xvfb

COPY Gemfile* /usr/src/app/
WORKDIR /usr/src/app

RUN bundle install

COPY spec/ /usr/src/app/spec/

CMD xvfb-run -a bundle exec rspec
