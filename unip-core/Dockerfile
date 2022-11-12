FROM adoptopenjdk/openjdk11:alpine
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/inventario.jar
WORKDIR /app
ENTRYPOINT java -jar inventario.jar