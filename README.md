# Go Banking interview

One canned-interview to rule them all...

## Instructions for interviewers

1. To run
  ```bash
  git clone git@github.com:dugancathal/gobanking.git
  cd gobanking
  . bin/setup [java|go (backend language)] [react|angular2 (frontend framework)] 
  bin/test all
  ```

1. Import stories/gobanking.csv to tracker
1. Select a story and get crackin'

## To view the app in the browser

1. Run `. bin/setup` as described in the instructions for interviewers

1. Serve the app by running `bin/boot`

1. Navigate to port 8080 on the docker machine url (run `docker-machine url` to determine the host)

## Note on running the Java tests locally (outside of docker)

You will need to specify the local endpoints for BANK_URL, PRODUCT_URL, PURCHASE_URL, CART_URL, either in the IDE or
from the command line. For most setups, this should work:
```bash
CART_URL=http://localhost:8080 PRODUCT_URL=http://localhost:8080 BANK_URL=http://localhost:8080 PURCHASE_URL=http://localhost:8080 ./gradlew test
```

### A note on structure

This repository can accommodate multiple backends and multiple frontends, and also
includes a Rails API gateway. The arguments specified to the setup script will
configure the environment to use the specified frontend and backend.

Executing `bin/test` (TODO: create this) will stand up the Docker instances
needed to test the specified backend and frontend combo. (TODO: make it run the
frontend, backend, and gateway specs as well.)

System tests go in `/spec`. Tests for the gateway go in `/banking_gateway/spec`.
Go backend tests go in `/backends/go/.../spec`. (TODO: create the other test dirs
and mention them.) Each of these can be run individually.

At the top-level, there's a directory for the GOPATH that serves as the backend,
a directory for the API Gateway in Rails, and a directory for each JS frontend.

All of the stories are oriented toward a horizontal slice of the application (frontend, backend).
If a vertical orientation is desired, write a story.

# TODO

* Setup script for React frontend, installing global dependencies on webpack and jasmine
* Inform the user that they may need to call `npm watch` in `frontends/react`
* `webpack-dev-server --content-base src/client/` in `/frontends/react` to serve
the application, or a different command if angular2 is used. Unify these commands
into a `bin/run` script.

## A note on Docker
There are several Docker instances that will be created, each defined in one `Dockerfile`.
`docker-compose.yml` is a "recipe" for `docker-compose` to create and start all of
those containers with the specified options; it's a substitute for calling `docker`
repeatedly directly.

The `Dockerfile`s have an `EXPOSE` directive which causes the specified port to be
available to other containers (but not to the host). `docker-compose.yml` contains
`port` directives which publish ports on each container to the host. The first
port number is the external port; the second one is the internal port. The `link`
directives in this file create hostnames by which these containers can refer to
each other, but the host is not aware of these names.

Because we're on OSX, the containers cannot be run directly. Instead, they're in
a VM controlled by `docker-machine`. A consequence of this is that the ports published
to the host will not be visible at `localhost`; instead, they'll be visible at
the hostname of the address revealed by calling `docker-machine url`. Keep this
in mind if you wish to execute tests locally (e.g. on the host) which rely on the
containers existing.
