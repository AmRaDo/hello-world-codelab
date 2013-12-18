#!/bin/sh

# Script for running service locally

# note: we will claim system is running in 'xna.ningops.net',
# but override domains for calling other services

mvn \
 -DjettyPort=8080 \
 -Dxn.host.external.name=xna.ningops.net \
 jetty:run-war

