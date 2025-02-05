#!/bin/bash

echo "Building the application with Gradle..."
./gradlew build -x test

echo "Starting the application using Docker Compose..."
docker-compose up --build -d
