## Build with Gradle
./gradlew build

## Run jar file in Docker container
docker build -t pet-clinic .
docker run --rm -it -p 8085:8080 pet-clinic
