
# Desafio API Rest - Gerador de boletos

O objetivo do desafio é construir uma API REST para geração de boletos que será consumido por  um módulo de um sistema de gestão financeira de microempresas.

No final do desafio vamos ter os seguintes endpoints para:  
- Criar boleto   
- Listar boletos  
- Ver detalhes  
- Pagar um boleto  
- Cancelar um boleto

# Stack

Foi utilizado os seguintes compoenentes/frameworks:

- Java 1.8
- Spring Boot
- Banco H2
- Maven
- Repository com JPA
- Lombok
- Swagger - Documentação automática da API
- Actuator - Monitorar a aplicação, geração de métricas, tráfegos, status e etc...
- Hamcrest - Biblioteca para auxilar na fluência da escrita dos testes

## IDE

- Visual Studio Code
- IntelliJ

## Infra
- Docker
- Azure DevOps - CD/CI


## Estrutura de pastas o projeto Java

- Config - Configuraçãoes do projeto
- Controller - End Points
- DTO - Classes de apresentação
- Entity - Classes das Entidades
- Erros - Configuraçoes de erros padrões
- Exception - Configurações das exceções
- Repository - Acesso ao banco
- Service - Serviços e regras de negócio

# Iniciando a Aplicação

## Iniciar mualmente

Na raiz do projeto ~/bankslips

####
    mvn package
    java -jar banksliprest-0.0.1-SNAPSHOT.jar
####

Acessar http://localhost:8080/swagger-ui.html

## Criar Imagem Docker

Na raiz do projeto ~/bankslips


    docker build -t bankslipsapi .
    docker run -d -p 8080:80 --name bankslipsapi bankslipsapi


Acessar http://localhost:8080/swagger-ui.html

## Executar imagem do Docker HUB

    docker run -d -p 8080:8080 --name bankslipsapi felipecrestani/bankslipsapi


Acessar http://localhost:8080/swagger-ui.html

# CD/CI Azure Devops

Para automatizar o CD/CI foi utilizado o Azure Devops.

Foi configurado o gatilho para qualquer commit na Master do projeto seja disparado o processo abaixo:

![Processo de Build](https://github.com/felipecrestani/BankSlips/blob/master/assets/Test%20Build%20Docker%20Push.png?raw=true)

- Get Source
- Maven Package
- Docker Build
- Docker Push to Docker Hub

Resultado do Pipeline

![Resultado do Pipeline](https://github.com/felipecrestani/BankSlips/blob/master/assets/CDCI.png?raw=true)

O resultado final é a atualização da imagem no registry 

https://hub.docker.com/r/felipecrestani/bankslipsapi/


 




