<h1 align="center">FormaNT RENTAL CARS</h1>
FormaNT - Rental Cars é uma aplicação web baseada em Java que permite a um utilizador registar-se e alugar um carro. O utilizador pode escolher o carro que deseja alugar, a data de inicio e fim do aluguel.

<hr/>

# 🚗 Aplicação

- Página de busca: os usuários podem pesquisar carros filtrando por vários parâmetros;
- Checar reservas: pode-se verificar as reservas feitas por cada utilizador;
- Sistema de Login: os usuários podem se registrar e fazer login na aplicação;
- Sistema de Edição/Atualização: pode-se adicionar, editar e remover carros, e adicionar, editar e remover utilizadores.

<hr/>

# 🚗 Tecnologia

FormaNT - Rental Cars é construido usando as seguintes tecnologias:

BACKEND:
- Java: versão 17;
- PostgreSQL: versão 7.2;
- Spring Boot: versão 3.1.2;

<hr/>

# 🚗 Como executar o projeto

## Passo 1: Baixar e extrair o código 

Primeiramente, baixe o código do website e extraia o arquivo ZIP para uma pasta no seu sistema local.

## Passo 2: Faça as configurações necessárias

### 🐘 Configurar o banco de dados
Esta aplicação usa o PostgreSQL como DB. Você precisará instalar o sistema e criar um banco de dados para a aplicação.

🚨 NOTE: O banco de dados vem configurado no projeto para rodar na porta 5432. Se você estiver usando outra porta, altere o arquivo "application.properties" para a porta que você está usando.

- PORTA: 5432
- USUARIO: postgres
- SENHA: password

## Passo 3: Execute seu projeto

Abra seu editor de código (Como o IntelliJ), navegue até o diretório do projeto e execute a aplicação.

<hr/>

# 🚨 Avisos Importantes

- 🚨 O Tomcat está configurado para rodar na porta 8080, então, além do PostgreSQL na porta 5432, certifique-se de que não tenha nenhum outro aplicativo rodando nesta porta (8080). Caso haja, faça as alterações necessárias (application.properties).
- 🚨 O projeto já vem com algumas dependências previamente instaladas. Caso seja necessário realizar alterações, lembre-se que o mesmo foi desenvolvido seguindo as configurações acima.