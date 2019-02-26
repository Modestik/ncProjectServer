mvn clean package
docker-compose build
docker-compose up -d
docker-compose stop

docker ps
docker exec -it  tomcat-1 sh
env | grep -i postgres
POSTGRES_PORT=tcp://172.17.0.2:5432
docker run --name some-postgres -e POSTGRES_PASSWORD=123 -d postgres





