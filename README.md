# Projeto Controle Contatos

## Tecnologias

- [Spring](https://spring.io/)
- [Java 17](https://www.oracle.com/br/java/technologies/downloads/#jdk17-windows)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Postman](https://www.postman.com/downloads/)

## Configuração do Projeto

Deve ter o Git, Maven, Java, Docker e Postman instalado.

### Clonar o Repositório

```bash
git clone https://github.com/henespejo/ControleContatos_java_avaliacao.git
cd ControleContatos_java_avaliacao/
```

### Iniciar o banco de dados PostgreSQL no Docker

```bash
docker-compose up -d
```

### Iniciar a aplicação Java

```bash
mvn package
java -jar target/*.jar
```

A aplicação já estará disponível e em http://localhost:8080/token?username=NOME

Copie o código e utilize no Postman ou Swagger para testar a aplicação.

No Postman em New -> HTTP -> Authorization -> Type: Bearer Token. Adicionar o código para testar os endpoinds da aplicação.

Para o acesso a documentação da API ir em: http://localhost:8080/swagger-ui/index.html
