./gradlew :pimonitor-server-app:craateDockerfile
./gradlew --stop
cd ./integration-tests
docker compose down
docker compose up --build --abort-on-container-exit
docker compose down