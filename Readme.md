mvn clean package\n
docker-compose build\n
docker-compose up -d\n
docker-compose stop\n
\n
docker ps\n
docker exec -it  tomcat-1 sh\n
env | grep -i postgres\n
POSTGRES_PORT=tcp://172.17.0.2:5432\n
docker run --name some-postgres -e POSTGRES_PASSWORD=123 -d postgres\n






