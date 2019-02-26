mvn clean package</br>
docker-compose build</br>
docker-compose up -d</br>
docker-compose stop</br>
</br>
docker ps\n
docker exec -it  tomcat-1 sh</br>
env | grep -i postgres</br>
POSTGRES_PORT=tcp://172.17.0.2:5432</br>
docker run --name some-postgres -e POSTGRES_PASSWORD=123 -d postgres</br>






