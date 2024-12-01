# PÓS TECH - [Pedidos]

## Features do sistema

### Cadastrar um novo pedido

Esse cadastro será efetuado pela rota POST /api/order, a documentação deste endpoint pode ser
acessada [aqui](http://localhost:8081/swagger-ui/index.html#/order-controller/createOrder)

#### Atualizar um pedido

Após o cadastro, caso necessário poderemos atualizar o registro utilizando o endpoint PUT
/api/order/{id}. A documentação deste endpoint pode ser acessada
aqui [aqui](http://localhost:8081/swagger-ui/index.html#/order-controller/putOrder)

### Buscar por um pedido

Para obter os dados de cadastro do pedido, pode-se utilizar o endpoint GET /api/order/{id}. Sua
documentação está [aqui](http://localhost:8081/swagger-ui/index.html#/order-controller/getOrder)

#### Consultar todos os pedidos cadastrados

Para obter uma lista com todos os pedidos cadastrados, utiliza-se o endpoint GET /api/order.
Cuja documentação
está [aqui](http://localhost:8081/swagger-ui/index.html#/order-controller/getOrders).

### Cancelar um pedido

Para cancelar um pedido, pode-se utilizar o endpoint PUT /api/order/{id}/cancel. Sua documentação
está [aqui](http://localhost:8081/swagger-ui/index.html#/order-controller/cancelOrder). Esta ação
cancela o pedido também no serviço de logística

### Atualização de status do pedido baseado na logistica

O status do pedido é atualizado automáticamente, via comunicação SQS com o serviço de logística.

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
