# Projeto Pedidos

## Básico do projeto

O projeto consiste de um domínio de pedidos de produtos. Mas para efetuar o pedido, o cliente (que faz parte do domínio) tem que ser
cadastrado para utlizar os endpoint. Existe a opção do cadastro.

A ideia do projeto foi utilizar o principio de responsabilidade única que está presente no SOLID. Por isso, cada classe tem o seu
encapsulamento e é pequena. Também não existem regras de negócio espalhadas, principalmente nas controllers.

Para ajudar em um melhor design, os testes foram criados com o objetivo de ter a inversão de dependência e a diminuição de erros.

## Endpoints

O projeto se encontra no azure através do endereço **http://20.37.138.157**

Através do endpoint **POST /api/cliente** o cliente poderá efetuar o cadastro. Abaixo um exemplo do corpo da requisição.

```
{
	"nome": "Teste",
	"email": "joaomaria@gmail.com",
	"senha": "123456",
	"rua": "teste",
	"cidade": "cidade",
	"bairro": "bairro",
	"cep":"cep",
	"estado": "estado"
}
```

Para testes, o usuário joaomaria@gmail.com já se encontra no banco de dados com a senha 123456. 

**Uma parte importante do sistema é a segurança, por isso foi utilizado o padrão JWT e obter o token é a primeira coisa a ser feita antes de acessar os demais endpoints.

Através do endpoint **POST /token** e com o corpo abaixo é possível obter o token de acesso.

```
{
	"email": "joaomaria@gmail.com",
	"password": "123456"
}
```

O token de acesso será recebido através do header Authorization, exemplo abaixo.

Authorization →Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvbWFyaWFAZ21haWwuY29tIiwiZXhwIjoxNTUxOTAxMDA0fQ.rcWwI1brXIW4erB5VmsPckXjIvdLLh0sgQRWTize4UmYMLwM-g_UcAXBOUmqjV6SKUVcGWZK9spOnP8yAccw_A

Obtido o token, para cada requisição deverá ser utilizada, caso contrário o sistema irá enviar o status de 403.

O cliente poderá efetuar pedidos dos produtos que estão cadastrados na plataforma. Mas antes, 
para saber quais são esses produtos, poderá acessar o endpoint **GET /api/produto** e assim saber a lista dos produtos com seus preços 
e quantidades

Quando o cliente selecionar os produtos do seu desejo, poderá usar o endpoint **POST /api/pedido**. Um exemplo do corpo da mensagem é 
colocado abaixo

```
[
	{
		"produtoId": 1,
		"quantidade": 2
	}
]
```

Conforme pode ser visto no corpo da mensagem, o usuário poderá enviar uma lista de produtos desejados.

## Padrão REST

Respeitando o padrão REST, toda vez que um POST for acionado, o status 201 de criado será enviado e um header chamado Location terá
o caminho para encontrar a entidade salva, exemplo location -> api/pedido/1

Outros endpoints poderá ser acessado

- POST /api/categoria
- GET /api/categoria/{id}
- POST /api/produto
- GET /api/produto/{id}

## Publicação

A publicação foi feita através da plataforma Azure.

Para o banco de dados foi criado uma instancia nova que suas configurações poderá ser vista no arquivo application.properties. 

O sistema web foi publicado através do Docker Instance. Na raiz do projeto existe um arquivo Dockerfile para publicar no docker hub e assim utilizar a imagem criada no Azure.

Abaixo os comandos para publicação.

```
docker build -t salmeidabatista/pedidos:1.1 .
docker push -t salmeidabatista/pedidos:1.1 .
```

A imagem salmeidabatista/pedidos:1.1 já existe no docker hub e para fins de teste poderá ser usado o comando abaixo para publicar na maquina local.

```
docker run -p 5000:80 salmeidabatista/pedidos:1.1
```

## O que foi feito no projeto

- Testes automatizados, há mais de 70 testes de unidade. 
- Spring data com SQL Server publicado no Azure
- Spring security usando JWT
- Git
- Flyway
- Publicado no Azure utilizando docker instance

