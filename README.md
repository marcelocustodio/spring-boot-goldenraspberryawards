# Spring Boot Golden Raspberry Awards

### Documentação de Referência

API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria de pior Filme do Golden Raspberry Awards lendo dados de um arquivo CSV e inserindo num banco em memória H2. O esquema do banco bem como a tabela Producer se encontram no arquivo data.sql, carregado automaticamente.

O objetivo é obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois prêmios mais rápido.

Para realizar uma requisição GET no programa Postman, basta digitar a seguinte linha na caixa de URL:

* http://localhost:8081/api/obterIntervaloDePremios

Pode-se obter também o mesmo resultado lançando mão do utilitário CURL.

Se você preferir uma saída mais elaborada no navegador com a lista dos produtores e os anos de suas vitórias, pode inserir a seguinte URL na caixa de endereços do mesmo:

* http://localhost:8081/relatorio

O arquivo de testes ( GoldenRaspberryAwardsApplicationTests ) valida a saída com os produtores já conhecidos com menor e com maior intervalo de tempo entre as premiações.

Se a aplicação você roda com Run as -> spring boot, o teste roda com run->junit test
