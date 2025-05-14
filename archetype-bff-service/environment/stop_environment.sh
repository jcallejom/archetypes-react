#!/bin/bash
set -e

BASEDIR=$(dirname "$0")

stopEnvironment(){
  docker compose -f "$BASEDIR"/docker/docker-compose.yml down -v
  docker compose -f "$BASEDIR"/docker/docker-compose-ottel.yml down -v
  docker compose -f "$BASEDIR"/docker/docker-compose-kafka-debz.yml down -v
  docker compose -f "$BASEDIR"/docker//docker-compose-kafka-n.yml down -v
}

stopEnvironment