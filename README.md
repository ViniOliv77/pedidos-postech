# PÓS TECH - [Pedidos]

## Features do sistema

### Cadastrar um novo pedido

Esse cadastro será efetuado pela rota POST /api/clients, a documentação deste endpoint pode ser
acessada [aqui](http://localhost:8080/swagger-ui/index.html#/client-controller/createClient)

#### Atualizar um client

Após o cadastro, caso necessário poderemos atualizar o registro utilizando o endpoint PUT
/api/clients/{id}. A documentação deste endpoint pode ser acessada
aqui [aqui](http://localhost:8080/swagger-ui/index.html#/client-controller/updateClient)

### Buscar por um client

Para obter os dados de cadastro do client, pode-se utilizar o endpoint GET /api/clients/{id}. Sua
documentação está [aqui](http://localhost:8080/swagger-ui/index.html#/client-controller/getClient)

#### Consultar todos os restaurantes cadastrados

Para obter uma lista com todos os clients cadastrados, utiliza-se o endpoint GET /api/clients/.
Cuja documentação
está [aqui](http://localhost:8080/swagger-ui/index.html#/client-controller/getClients).
**Atenção, essa consulta é case sensitive**.

### Deletar um client

Para remover um client, pode-se utilizar o endpoint DELETE /api/clients/{id}. Sua documentação
está [aqui](http://localhost:8080/swagger-ui/index.html#/client-controller/deleteClient)

## Inicializando localmente a API

### Docker

Para inicializar a aplicação localmente é necessário o uso do Docker, caso não tenha instalado, faça
o download [aqui](https://docs.docker.com/engine/install/).

#### Docker compose

O docker compose, arquivo responsável por configurar os containers, está no package .\docker,
portanto pode rodar através da sua IDE diretamente ou pelo comando `docker compose up` diretamente
na pasta do arquivo docker-compose.yml

#### Iniciando a aplicação

Utilize a IDE que melhor lhe agrade para iniciar a API

## Utilizando a API

Para fazer requisições a API é necessário o uso de algum software que tenha essa funcionalidade (
Postman, Insomnia, Apidog, ...) ou pode utilizar o terminal para rodar comandos cURL.

Caso utilize o postman, pode importar a collection que está no package .\postman

# Configurando AWS localstack

`aws configure --profile localstack`

`AWS_ACCESS_KEY_ID="test"`

`AWS_SECRET_ACCESS_KEY="test"`

`AWS_DEFAULT_REGION="us-east-1"`

`DEFAULT OUTPUT="json"`

# Criando filas

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name pedido-queue --profile localstack`

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name pedido-update-status-queue --profile localstack`

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name logistica-queue --profile localstack`

`aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name produto-queue --profile localstack`
