#! /bin/sh
# For a detailed description about SIGTERM and bash and in order to understand how this bash script works
# please read http://veithen.github.io/2014/11/16/sigterm-propagation.html

# Who am I?
whoami

# Cleanup things GF4 likes to screw up (useful for container restart)
rm -rf $DOMAIN_DIR/osgi-cache $DOMAIN_DIR/generated

# Gracefully handle container shutdown via "docker stop"
trap "$ASADMIN stop-domain $DOMAIN_NAME" TERM

# Start domain now
$ASADMIN start-domain -v $DOMAIN_NAME &
PID=$!
wait $PID
wait $PID
EXIT_STATUS=$?
