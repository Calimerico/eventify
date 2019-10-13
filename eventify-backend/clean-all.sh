#!/bin/bash
docker-compose stop
docker-compose rm -f
docker system prune -a -f
docker volume prune -f

