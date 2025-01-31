# Sistema de E-Commerce

## Objetivo do Projeto
Este projeto é um sistema básico de E-Commerce desenvolvido em Java utilizando o framework Spring Boot. Ele permite o cadastro de produtos, clientes e a realização de compras, simulando o funcionamento de uma loja virtual. O sistema inclui validações de dados, tratamento de erros e manipulação de dados, garantindo a integridade das informações.

### Funcionalidades Principais:
- **Cadastro de Produtos**: Nome único, preço maior que 0 e quantidade maior ou igual a 0.
- **Cadastro de Clientes**: Nome, CPF único e válido, e email único e válido.
- **Realização de Compras**: Registro de compras, atualização de estoque e validação de disponibilidade de produtos.

---

## Tecnologias Utilizadas
- **Spring Boot** (Spring Web, Spring Data JPA, Validation)
- **H2 Database** (banco de dados em memória)
- **Jackson Databind** (manipulação de JSON)
- **ThunderClient** (testes de API)

---

## Como Executar o Projeto no IntelliJ
1. **Clone o repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <PASTA_DO_PROJETO>

2. **Abra o projeto no IntelliJ**: No IntelliJ, clique em File > Open e selecione a pasta do projeto.


3. **Configure o Maven**: Certifique-se de que o Maven está configurado no IntelliJ.
Aguarde o IntelliJ baixar as dependências do projeto.


4. **Execute o projeto**: Localize a classe principal do projeto (geralmente Application.java).
Selecione-a e pressione SHIFT-F10 para executar a aplicação.


## Como Testar no ThunderClient
1. **Instale o ThunderClient**:
Certifique-se de que o ThunderClient está instalado no seu VS Code.

2. **Endpoints Disponíveis**:

- **Produtos**:

GET /produtos → Retorna todos os produtos cadastrados.

POST /produtos → Cadastra um novo produto.

   DELETE /produtos/{nome} → Deleta um produto pelo NOME.
    

Exemplo de POST:
```
{
"nome": "Arroz",
"preco": 5.99,
"quantidade": 10
}
```

- **Clientes**:

POST /clientes → Cadastra um novo cliente. Exemplo de JSON:

GET /clientes/{cpf} → Retorna os dados de um cliente pelo CPF.

PUT /clientes/{cpf} → Atualiza os dados de um cliente.

Exemplo de POST:
```
{
"nome": "João Silva",
"cpf": "62732714020",
"email": "joao@email.com"
}
```

- **Compras**:

POST /compras → Registra uma nova compra. Exemplo de JSON:

```
{
    "cpf": "62732714020",
    "produtos": [
        {
            "nome": "Arroz"
        },
        {
            "nome": "Sal"
        }
    ]
}
```


3. **Realize os testes**:
Configure o ThunderClient para enviar requisições para http://localhost:8080.
Teste os endpoints utilizando os exemplos de JSON fornecidos.

## Tratamento de Erros

**Validações de Dados**:
Produtos com nome duplicado, preço inválido ou quantidade negativa não podem ser cadastrados.
Clientes com CPF ou email duplicados ou inválidos não podem ser cadastrados.

**Erros de Compra**:
Caso um produto esteja em falta, o sistema retorna:

```
{
"erro": "Produto em falta: [nome do produto]"
}
```

Se mais de um produto estiver em falta, todos os produtos indisponíveis serão listados.

## Estrutura do Projeto
**Models**:
Carrinho, CarrinhoEntity, Cliente, ClienteEntity, Compras, ComprasEntity, Produto, ProdutoEntity.

**Repositories**:
ClienteRepository, ComprasRepository, ProdutoRepository.

**Services**:
ClienteService, ComprasService, ProdutoService.

**Controllers**:
ClienteController, ComprasController, ProdutoController.

**GlobalExceptionHandler**:
Tratamento centralizado de exceções.

## Observações
O projeto utiliza o banco de dados H2 em memória, portanto, os dados serão perdidos ao reiniciar a aplicação.
Certifique-se de que as dependências do Maven estão atualizadas antes de executar o projeto.