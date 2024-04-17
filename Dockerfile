# Dockerfile
FROM clojure:openjdk-17-lein

WORKDIR /usr/src/app
# Set environment variables
ENV DATABASE_URL=jdbc:postgresql://host.docker.internal:5432/postgres \
    DB_HOST=host.docker.internal \
    DB_PORT=5432 \
    DB_NAME=postgres \
    DB_USER=postgres \
    DB_PASSWORD=postgres
COPY . /usr/src/app

RUN lein uberjar
CMD ["java", "-jar", "target/uberjar/playback-project-standalone.jar"]