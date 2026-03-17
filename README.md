# Cypher 🛡️
> Sistema de gestão e análise de vendas com detecção automática de fraudes

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![SQLite](https://img.shields.io/badge/SQLite-JDBC-blue?style=flat-square&logo=sqlite)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=flat-square)

---

## Sobre o projeto

O **Cypher** é um sistema de gestão de vendas que integra um motor de análise de fraudes em tempo real. Cada transação é avaliada por um conjunto de regras de negócio independentes que calculam um score de risco, podendo alertar ou bloquear automaticamente vendas suspeitas.

---

## Funcionalidades

- Cadastro e gerenciamento de vendas, produtos, vendedores, categorias e departamentos
- Motor de fraude com 3 regras independentes e score acumulado
- Bloqueio automático de transações de alto risco
- Registro automático de fraudes detectadas no banco de dados
- Banco de dados criado automaticamente na primeira execução

---

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
| 0 | 🟢 BAIXO | Venda aprovada |
| 1 | 🟡 MÉDIO | Alerta exibido, venda sob responsabilidade do usuário |
| 2+ | 🔴 ALTO | Venda bloqueada e registrada na tabela `fraude` |

---

## Tecnologias

- **Java 21** (Eclipse Temurin)
- **SQLite** com JDBC puro (sem ORM)
- **IntelliJ IDEA**

---

## Arquitetura

O projeto segue uma arquitetura em camadas inspirada no padrão MVC:

```
src/
└── main/
    └── java/
        └── cypher/security/
            ├── config/         # Configuração do banco de dados
            ├── model/          # Entidades (Venda, Produto, Vendedor...)
            ├── repository/     # Acesso ao banco via JDBC
            ├── service/        # Regras de negócio
            ├── controller/     # Ponto de entrada das operações
            ├── dto/            # Objetos de transferência de dados
            └── fraud/          # Motor de detecção de fraudes
                ├── RegraFraude.java          (interface)
                ├── RegraPrecoSuspeito.java
                ├── RegraVendedorSuspeito.java
                ├── RegraCadastramentoRapido.java
                ├── MotorFraude.java
                └── ResultadoAnalise.java
```

---

## Banco de Dados

O banco `vendas.db` é criado automaticamente em SQLite na primeira execução. Tabelas:

| Tabela | Descrição |
|---|---|
| `categorias` | Categorias de produtos |
| `produtos` | Produtos com preço de mercado e categoria |
| `departamentos` | Departamentos da empresa com andar |
| `vendedores` | Vendedores com CPF único e departamento |
| `vendas` | Transações com nível de risco calculado |
| `fraude` | Registro automático de vendas com risco alto |

---

## Como rodar

**Pré-requisito:** JDK 21 instalado

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/cypher.git
cd cypher
```

Abra o projeto no **IntelliJ IDEA** e execute a classe principal.  
O banco `vendas.db` será criado automaticamente na raiz do projeto.

