## Mô tả

API Demo cho lớp devops

## Chạy môi trường development

docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=spring_db -d mysql
</br>
mvn spring-boot:start

## Chạy docker

docker build -t java-test .
</br>
docker-compose up

## Chạy unit test

mvn test