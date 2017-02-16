FROM java:openjdk-8-jdk-alpine

# Useful ENV vars for payara domain
ENV PAYARA_URL https://s3-eu-west-1.amazonaws.com/payara.fish/Payara+Downloads/Payara+4.1.1.164/payara-4.1.1.164.zip
ENV SERVICE_DIR /webservice
ENV DOMAIN_NAME service
ENV GLASSFISH_DIR $SERVICE_DIR/payara41/glassfish
ENV DOMAIN_DIR $GLASSFISH_DIR/domains/$DOMAIN_NAME
ENV DOMAIN_CONFIG_DIR $DOMAIN_DIR/config
ENV DOMAIN_APPS_DIR $DOMAIN_DIR/autodeploy
ENV ASADMIN $GLASSFISH_DIR/bin/asadmin --user admin

# Install required software
RUN echo "> Installing packages" && apk update && apk add bash ca-certificates unzip wget openssl && \
    mkdir $SERVICE_DIR && \
    echo "> Installing payara" && wget $PAYARA_URL -O $SERVICE_DIR/payara.zip && \
    unzip $SERVICE_DIR/payara.zip -d $SERVICE_DIR && \
    rm -rf $SERVICE_DIR/payara.zip

# Runtime management scripts
COPY run.sh /

# Domain config
RUN $ASADMIN create-domain --nopassword $DOMAIN_NAME && \
    $ASADMIN start-domain $DOMAIN_NAME && \
    $ASADMIN set-hazelcast-configuration --enabled=true -f hazelcast.xml && \
    $ASADMIN stop-domain $DOMAIN_NAME
COPY hazelcast.xml $DOMAIN_CONFIG_DIR
COPY build/libs/chatapp.war $DOMAIN_APPS_DIR/chatapp.war

# Permissions for non-root execution
RUN chmod 777 /run.sh && chmod -R 777 $SERVICE_DIR

# Do it
EXPOSE 8080
CMD ["/run.sh"]