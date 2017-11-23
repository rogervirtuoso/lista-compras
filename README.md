    Criador: Roger Chechetto Virtuoso
 
  Lista de Compras
  -

Tecnologias utilizadas:
 * Backend
   * Java 8
   * Maven
   * Hibernate
   * Jersey
 * Frontend
   * HTML5
   * AngularJS
   * Bootstrap
 * Banco
   * PostgreSQL
 * Servidor de Aplicação
   * Apache Tomcat      

Instruções para subir a aplicação:
-

* Baixar a aplicação do repositório
* Criar um novo database com o nome *lista-compras*
* Executar o script localizado em *lista-compras/src/main/resources/sql/banco.sql* no database
* Na pasta raiz do projeto, executar o goal*mvn install*, para baixar as dependências necessárias e construir o WAR do projeto na pasta target.
* Realizar o deploy do conteúdo do WAR no Tomcat com o nome *lista-compras*.
  * Uma opção que pode facilitar é realizar o deploy da própria IDE, no caso, utilizado o IntelliJ.


    Projeto ainda em fase de desenvolvimento.


