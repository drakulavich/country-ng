#!/bin/bash

CONTAINER_NAME="countryng"
POSTGRES_VERSION="15.1"
POSTGRES_PASSWORD="secret"
DB_PORT=5432
VOLUME_NAME="countryng_data"

echo "Starting PostgreSQL $POSTGRES_VERSION container named $CONTAINER_NAME with persistent storage..."

# Create Docker volume if it doesn't exist
docker volume inspect $VOLUME_NAME > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "Creating Docker volume '$VOLUME_NAME'..."
    docker volume create $VOLUME_NAME
fi

# Run the Docker container
docker run -d \
  --name $CONTAINER_NAME \
  -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
  -e POSTGRES_DB=$CONTAINER_NAME \
  -v $VOLUME_NAME:/var/lib/postgresql/data \
  -p $DB_PORT:5432 \
  postgres:$POSTGRES_VERSION

# Check if the container started successfully
if [ $? -eq 0 ]; then
    echo "PostgreSQL container '$CONTAINER_NAME' started successfully on port $DB_PORT."
    echo "Data will be persisted in Docker volume '$VOLUME_NAME'."
else
    echo "Failed to start PostgreSQL container '$CONTAINER_NAME'."
    exit 1
fi
