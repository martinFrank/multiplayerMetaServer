# we will use openjdk 8 with alpine as it is a very small linux distribution
FROM openjdk:8-jre-alpine3.9

# copy the packaged jar file into our docker image
COPY target/multiplayerMetaServer-0.0.1.jar /multiplayerMetaServer-0.0.1.jar
COPY multiplayerMetaServer.yml /multiplayerMetaServer.yml

# set the startup command to execute the jar
CMD ["java", "-jar", "multiplayerMetaServer-0.0.1.jar", "server", "multiplayerMetaServer.yml"]