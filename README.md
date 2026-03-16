# Cypher

Sistema de gestão e análise de vendas com detecção automática de fraudes, desenvolvido em Java com banco de dados SQLite.

## Sobre o projeto

O Cypher permite cadastrar e gerenciar vendas associando produtos, categorias, vendedores e departamentos. Cada venda passa por um motor de análise de fraude que classifica o nível de risco com base em regras de negócio, podendo bloquear transações suspeitas e registrá-las automaticamente no banco de dados.

## Tecnologias

- Java 21 (Eclipse Temurin)
- SQLite + JDBC
- IntelliJ IDEA

## Estrutura do projeto

```
src/
└── main/
    └── java/
        └── cypher/security/
            ├── config/
            │   └── DatabaseConfig.java
            ├── model/
            │   ├── Venda.java
            │   ├── Produto.java
            │   ├── Vendedor.java
            │   ├── Categoria.java
            │   └── Departamento.java
            ├── repository/
            │   ├── VendaRepository.java
            │   ├── ProdutoRepository.java
            │   ├── VendedorRepository.java
            │   ├── CategoriaRepository.java
            │   └── DepartamentoRepository.java
            ├── fraud/
            │   ├── RegraFraude.java
            │   ├── RegraPrecoSuspeito.java
            │   ├── RegraVendedorSuspeito.java
            │   ├── RegraCadastramentoRapido.java
            │   ├── MotorFraude.java
            │   └── ResultadoAnalise.java
            ├── service/
            │   └── VendaService.java
            ├── controller/
            │   └── VendaController.java
            └── dto/
                └── VendaDTO.java
```

## Banco de dados

O banco é criado automaticamente em `vendas.db` na primeira execução. As tabelas são:

- `categorias` — categorias de produtos
- `produtos` — produtos com preço de mercado e categoria
- `departamentos` — departamentos da empresa com andar
- `vendedores` — vendedores com CPF único e departamento
- `vendas` — transações com nível de risco calculado
- `fraude` — registro automático de vendas com risco alto

## Motor de Fraude

Cada venda é avaliada por três regras independentes:

| Regra | Critério |
|---|---|
| `RegraPrecoSuspeito` | Preço menor que 50% ou maior que 200% do preço de mercado |
| `RegraVendedorSuspeito` | Vendedor com menos de 5 vendas realizando venda acima de R$ 2.000 |
| `RegraCadastramentoRapido` | Produto cadastrado há menos de 24h com venda acima de R$ 1.000 |

O score acumulado define o nível de risco:

| Score | Nível | Ação |
|---|---|---|
| 0 | BAIXO | Venda aprovada |
| 1 | MEDIO | Alerta exibido, venda sob responsabilidade do usuário |
| 2+ | ALTO | Venda bloqueada e registrada na tabela `fraude` |

## Como rodar

1. Certifique-se de ter o JDK 21 instalado
2. Clone o repositório
```bash
git clone https://github.com/seu-usuario/cypher.git
cd cypher
```
3. Abra no IntelliJ IDEA
<<<<<<< HEAD
4. O banco `vendas.db` será criado automaticamente ao rodar o projeto
=======
4. O banco `vendas.db` será criado automaticamente ao rodar o projeto
