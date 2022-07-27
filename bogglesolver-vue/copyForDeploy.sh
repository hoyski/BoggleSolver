#!/bin/sh

rm -r ../bogglesolverservice/src/main/resources/static
mkdir ../bogglesolverservice/src/main/resources/static
cp -r dist/* ../bogglesolverservice/src/main/resources/static