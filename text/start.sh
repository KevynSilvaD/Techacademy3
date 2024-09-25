#!/bin/bash

echo "Instalando Java..."
apt-get update
apt-get install -y default-jre

echo "Iniciando a aplicação..."
java -jar out/artifacts/kevyn_silva/kevyn_silva.jar
