# playback-project


## Arquitetura

### Escolha do banco de dados

Para este projeto foi escolhido o banco de dados Postgres, 
por ser um banco de dados relacional e por ser um banco de dados 
que possui uma boa documentação e uma comunidade ativa.
Assim como suporte para a linguagem Clojure através da biblioteca HugSql,
foi o qual encontrei mais exemplos e documentação disponível.
Além disso postgres é um banco de dados que possui uma boa performance
e é amplamente utilizado em produção junto com o suporte à UUID nativo.

# Arquitetura da aplicação

A arquitetura da aplicação foi feita de forma a separar as responsabilidades
em diferentes camadas, de forma a facilitar a manutenção e a evolução da aplicação.

A aplicação foi dividida em 3 camadas principais:

- Handlers: Responsável por receber as requisições HTTP e chamar os serviços necessários.
- UseCase : Responsável por implementar a lógica de negócio da aplicação.
- Database: Responsável por realizar a conexão com o banco de dados.
- Validations: Responsável por realizar a validação dos dados recebidos nas requisições.

### Comunicação entre os componentes

A comunicação entre os componentes é dividida entre 3 componentes:
- banco de dados ( Postgres )
- Aplicação ( Clojure )
- Cliente ( Frontend / Postman / etc)

A arquitetura pode ser visualizada na imagem abaixo:

![Arquitetura](/doc/arquitetura.png)

## Como usar

### Como rodar o projeto:

O projeto se utiliza do docker para subir o banco de dados e a aplicação.
Recomendo que execute o projeto utilizando o docker-compose, pois o 
mesmo contém as variáveis de ambiente necessárias para executar o projeto.
Para executar o projeto, basta rodar o comando:

```docker-compose up```

A aplicação estará disponível em `localhost:3000`.
E o banco de dados estará disponível em `localhost:5432`.

Para o build da aplicação, no diretório está presente um arquivo Dockerfile
que pode ser utilizado para gerar a imagem da aplicação.

Também está presente no diretório raiz do projeto um arquivo
```sql.init``` onde contém o script para criação do banco de dados e das tabelas
necessárias.

### Como executar testes unitários

Para executar os testes unitários, basta rodar o comando:

```docker-compose up --build test```

Pois o mesmo também utiliza o docker para rodar os testes, devido à facilidade que fornece
para rodar os testes em um ambiente isolado e com o mesmo banco de dados que a aplicação ao invés de um H2
onde podem existir diferenças de comportamento.
## License

Copyright © 2024 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
