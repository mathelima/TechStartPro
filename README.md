# Descrição:
Esse projeto é uma API Rest feita em Java utilizando Spring Boot que armazena dados de produtos e categorias.

A API lê um arquivo CSV contido na pasta TechStartPro\tech-start-pro e importa para o banco de dados.

O banco de dados utilizado foi o H2 e ele pode ser acessado através da url http://localhost:8080/h2-console/ com o projeto rodando.

A API permite realizar um CRUD de produtos e cada uma das funcionalidades será explicada no tópico Execução.



# Instruções de configuração:
Esse projeto foi executado em um computador com sistema operacional Windows 10.

Para rodar o projeto é necessário ter o java instalado. A versão utilizada foi a jdk 8u261 para windows x64.

Além disso, é necessário ter uma IDE compatível com o Java, a utilizada foi o IntelliJ Comunity Edition 2020.2.2.

Também é necessário ter alguma ferramenta para realizar as requisições na API, a utilizada foi o Postman.

O maven é responsável por gerenciar as dependências do projeto e se encarrega de baixá-las na primeira execução, as bibliotecas e frameworks utilizados podem ser vistos no arquivo pom.xml.



# Execução
Para rodar o projeto, basta rodar a classe TechStartProApplication do pacote com.olist.techstartpro dentro da pasta src/main/java/

Com o projeto rodando, é possível realizar requisições HTTP correspondentes ao CRUD de produto.

Para visualizar a lista de todos os produtos basta enviar um método Get para a url http://localhost:8080/product

Para visualizar um produto podemos filtrar por 4 campos (ou uma composição desses quatro), enviando um método Get para a url http://localhost:8080/product/search enviando um JSON com esses campos no body, como por exemplo:

    {
	    "name": "",
	    "description": "sofá amarelo",
	    "value": "",
	    "category": [1,3,4]
    }



Um produto é criado através de um método Post para a url http://localhost:8080/product enviando um JSON no body com os campos name, description, value e category, como por exemplo:

    {
	    "name": "sofá x",
	    "description": "sofá amarelo",
	    "value": 500.50,
	    "category": [1,3,4]
    }



A edição de um produto é feita através de um método Put para a url http://localhost:8080/product enviando um JSON alterando os campos desejados no body, por exemplo:

    {
	    "name": "sofá x",
	    "description": "sofá branco",
	    "value": 700.50,
	    "category": [1,3,5]
    }



Para deletar um produto basta realizar um método Delete para a url http://localhost:8080/{id} onde id é o id do produto que deseja excluir.

