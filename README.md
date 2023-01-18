# Anime

#### Aplicação Monolítica - Projeto para verificar atualizações do Spring Boot 3.0 e utilização do JAVA 17

1º Gerar o build do projeto 
            
    ./mvnw clean package 

2º Criar o bando de dados 

    docker container run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --network NAME_NETWORK --name NAME_IMAGEM_MYSQL mysql:8.0

3º Gerar a imagem Docker do projeto 

    docker image build -t "NAME_IMAGE_DOCKER_CREATED" .

4º Inicializar a Imagem Docker do projeto

    docker container run --rm -p 8080:8080 -e DB_HOST=NOME_CONTAINER_MYSQL --network NOME_NETWORK NOME_IMAGEM

--------------------------------------------------------------------

5º Efetuando geração de imagem juntamento com MAVEN
	
	1º Gerar o build do projeto 
  
       ./mvnw clean package -Pdocker

	2º Inicializar a Imagem Docker do projeto

        docker container run --rm -p 8080:8080 -e DB_HOST=NOME_CONTAINER_MYSQL --network NOME_NETWORK NOME_IMAGEM