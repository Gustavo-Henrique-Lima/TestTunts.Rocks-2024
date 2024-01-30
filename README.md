# TestTunts.Rocks-2024

O projeto consiste em uma aplicação que lê uma planilha armazenada no Google Drive contendo informações de estudantes, sendo necessário preencher as colunas de "Situação" e "Nota para Aprovação Final". A aplicação, de forma automatizada, recebe os dados da planilha, processa as informações e gera os resultados correspondentes para a coluna mencionada. Além disso, integra uma autenticação segura utilizando OAuth do Google para garantir a segurança e a autorização adequada.

## Como Rodar o Projeto

### Pré-requisitos

- Java JDK 11 ou superior
- Maven 3.1 ou superior
### Passos

1. **Clonar o Repositório:**

   ```bash
   git clone git@github.com:Gustavo-Henrique-Lima/TestTunts.Rocks-2024.git

2. **Navegue até o diretório do projeto:**

    ```bash
    cd TestTunts.Rocks-2024

3. **Instale as dependências:**

    ```bash
    mvn install

4. **Inicie o servidor de desenvolvimento:**

    ```bash
   mvn spring-boot:run

## Tecnologias utilizadas
    Java e Spring Boot.

## Teste unitários
    A aplicação conta com alguns testes unitários que são responsáveis por verificar e validar a funcionalidade de calculo de média e situação dos alunos.

## Funcionalidades

   ### Leitura da Planilha

      A aplicação é capaz de acessar e ler a planilha de estudantes armazenada no Google Drive.

   ### Processamento de Dados

      Realiza o processamento dos dados da planilha, identificando lacunas nas colunas "Situação" e "Nota para Aprovação Final".
      Aplica regras específicas para determinar a situação do estudante e calcular a nota necessária para a aprovação final.

   ### Geração de Resultados

      Produz automaticamente os resultados para a coluna "Situação" e "Nota para Aprovação Final" com base nas regras estabelecidas.
   ### Estudantes(Endpoint)

    Listar estudantes:
      Endpoint para recuperar a lista completa de todos os estudantes.
## Documentação

  O projeto inclui documentação detalhada para facilitar o entendimento e a interação com a aplicação.
  A seguir estão os recursos de documentação disponíveis.

  ### Swagger

   A API é documentada usando o Swagger, que fornece uma interface interativa para explorar os endpoints 
  da aplicação.
  ### Acesso ao Swagger:
  **Com o projeto rodando**
  
  O Swagger pode ser acessado através do link: [Swagger UI](http://localhost:8080/swagger-ui/index.html).
  
  A interface do Swagger oferece uma visão interativa dos endpoints, permitindo testar as operações
  diretamente na documentação.

## Perfis de configuração

  #### **Perfil de Teste (Padrão)**
  
  - **Banco de Dados**: Utiliza o H2 Database, dispensando configurações adicionais.

## Licença
 Este projeto está licenciado sob a licença MIT.
