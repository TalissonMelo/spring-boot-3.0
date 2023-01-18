1º Gerar o build do projeto 
            
    ./mvnw clean package 

2º Criar o bando de dados 

    docker container run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --network NAME_NETWORK --name NAME_IMAGEM_MYSQL mysql:8.0

3º Gerar a imagem Docker do projeto 

    docker image build -t "NAME_IMAGE_DOCKER_CREATED" .

4º Inicializar a Imagem Docker do projeto

    docker container run --rm -p 8080:8080 -e DB_HOST=NOME_CONTAINER_MYSQL --network NOME_NETWORK NOME_IMAGEM