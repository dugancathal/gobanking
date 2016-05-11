# Go Banking interview

One canned-interview to rule them all...

## Instructions for interviewers

1. Copy and paste into terminal
  ```bash
  git clone git@github.com:dugancathal/gobanking.git
  cd gobanking
  . bin/setup go react
  ```

1. Import stories.csv to tracker
1. Select a story and get crackin'

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
