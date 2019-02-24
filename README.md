# Projeto Pedidos

## Básico do projeto

O projeto consiste de um domínio de pedidos de produtos. Mas para efetuar o pedido, o cliente (que faz parte do domínio) tem que ser
cadastrado para utlizar os endpoint. Existe a opção do cadastro.

A ideia do projeto foi utilizar o principio de responsabilidade unica que está presente no SOLID. Por isso, cada classe tem o seu
encapsulamento e não existe regras de negócio espalhadas, principalmente nas controllers.

Para ajudar em um melhor design, os testes foram criados com o objetivo de ter a inversão de dependência e também a diminuição de erros.

## Endpoints

Através do endpoint POST /api/cliente o cliente poderá eftuar o cadastro. Abaixo um exemplo do corpo da requisição.

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

Para testes, o usuário joaomaria@gmail.com já está cadastrado com a senha 123456. Isso é importante para dado o login do usuário obter
o token para utilizar os demais endpoints.

Através do endpoint POST /token e com o corpo abaixo é possível obter o token de acesso.

```
{
	"email": "joaomaria@gmail.com",
	"password": "123456"
}
```

O token de acesso será recebido através do header Authorization, exemplo abaixo.

Authorization →Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvbWFyaWFAZ21haWwuY29tIiwiZXhwIjoxNTUxOTAxMDA0fQ.rcWwI1brXIW4erB5VmsPckXjIvdLLh0sgQRWTize4UmYMLwM-g_UcAXBOUmqjV6SKUVcGWZK9spOnP8yAccw_A

Dado esse token, para cada requisição deverá ser utilizada, caso contrário o sistema irá enviar o status de 403.

Com o cliente cadastrado e token obtido, ele poderá efetuar pedidos dos produtos que estão cadastrados na plataforma. Mas antes, 
para saber quais são esses produtos, poderá acessar o endpoint GET /api/produto e assim saber a lista dos produtos com seus preços 
e quantidades

Quando o cliente selecionar os produtos do seu desejo, poderá usar o endpoint POST /api/pedido. Um exemplo do corpo da mensagem é 
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

## O que foi feito no projeto

- Testes automatizados, há mais de 70 testes de unidade. 
- Spring data
- Spring security usando JWT
- Git
- Flyway
- Publicado no azure utilizando docker instance

