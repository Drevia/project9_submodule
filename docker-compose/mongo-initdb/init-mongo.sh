#!/bin/bash
echo "Importing MongoDB dump..."
mongorestore --username root --password password --authenticationDatabase admin --db patientNote /docker-entrypoint-initdb.d/patientNote.bson
echo "MongoDB dump imported."