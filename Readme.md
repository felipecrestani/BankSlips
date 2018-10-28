
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
- Kubernetes


## Estrutura de pastas o projeto

- Config - Configuraçãoes do projeto
- Controller - End Points
- DTO - Classes de apresentação
- Entity - Classes das Entidades
- Erros - Configuraçoes de erros padrões
- Exception - Configurações das exceções
- Repository - Acesso ao banco
- Service - Serviços e regras de negócio

# Iniciando a Aplicação

## Manualmente Local

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

## Executar Docker


    docker push felipecrestani/bankslipsapi
    docker run -d -p 8080:80 --name bankslipsapi felipecrestani/bankslipsapi


Acessar http://localhost:8080/swagger-ui.html

## CD/CI Azure Devops





