#! /bin/sh
PROJECT_NAME=chat-project
APP_NAME=chatapp
oc new-project $PROJECT_NAME
oc new-build --strategy=docker --name=$APP_NAME .
oc start-build $APP_NAME --from-dir=. && oc logs -f bc/$APP_NAME
oc new-app $APP_NAME
oc expose service $APP_NAME