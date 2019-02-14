#!/bin/bash
#
#
JAR_PATCH="/home/alex/Documents/works/music-loader/deploy/app/music_loader-0.0.1-SNAPSHOT.jar"
PROPERTY_PATCH="file:/home/alex/Documents/works/music-loader/deploy/app/config/application.properties"


java -jar $JAR_PATCH --spring.config.location=$PROPERTY_PATCH