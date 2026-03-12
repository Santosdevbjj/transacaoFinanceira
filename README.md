# 🏦 BancoPOO — Sistema Bancário em Java com POO

![NTT-Java](https://github.com/user-attachments/assets/af4f7d76-2bff-4dc5-9bb7-74f8e79ff1c9)

**Bootcamp NTT DATA — Java e IA para Iniciantes**

![Java Version](https://img.shields.io/badge/java-17+-blue.svg)

---

## 1. Problema de Negócio

Aprender Programação Orientada a Objetos com exemplos genéricos como "Animal que faz som" não prepara o desenvolvedor para os desafios reais do mercado. O problema é claro: **como demonstrar domínio de POO de forma que faça sentido para quem contrata?**

A resposta está em construir um sistema que replica um domínio complexo e familiar — o bancário — onde herança, encapsulamento, polimorfismo e abstração não são apenas conceitos decorados, mas decisões de design com consequências reais: uma regra de horário errada libera depósito fora do expediente; uma hierarquia de classes mal desenhada força duplicação de lógica em toda a aplicação.

Este projeto foi construído para provar que POO não é teoria — é a diferença entre um sistema que aguenta crescer e um que quebra na primeira mudança.

---

## 2. Contexto

Este projeto foi desenvolvido durante o **Bootcamp NTT DATA — Java e IA para Iniciantes**, com foco na aplicação prática dos pilares da Programação Orientada a Objetos em um cenário de negócio real.

O sistema simula uma instituição bancária operando via console, com dois tipos de conta (corrente e poupança), múltiplos canais de depósito, regras de atendimento por horário e dia da semana, transferências via PIX, gerenciamento de investimentos e histórico completo de transações com timestamp.

Toda a lógica é mantida em memória durante a execução, com arquitetura em camadas separando domínio, repositório e serviço — o mesmo padrão utilizado em aplicações Spring Boot reais.

---

## 3. Premissas do Projeto

Para o desenvolvimento do sistema, as seguintes premissas foram adotadas:

- **Persistência em memória:** os dados de contas e investimentos existem apenas durante a execução. A arquitetura foi projetada para facilitar a substituição futura por um banco de dados real sem alterar as regras de negócio.
- **Regras de horário:** depósitos na boca do caixa são permitidos apenas entre 10h e 16h em dias úteis. Sábados, domingos e feriados seguem comportamento distinto, configurável via lista de datas.
- **Hierarquia de contas:** `Conta` é abstrata — nenhuma conta pode existir sem ser Corrente ou Poupança. Investimentos estão disponíveis apenas para `ContaPoupanca`.
- **Rastreabilidade total:** toda operação financeira (depósito, saque, PIX, investimento, resgate) gera um `record` de `Transacao` com tipo, valor, timestamp e detalhe textual.
- **Enums como contratos:** `TipoDeposito`, `TipoTransacao` e `TipoConta` garantem que nenhum valor inválido entre no sistema — o compilador é a primeira camada de validação.

---

## 4. Estratégia da Solução

A construção seguiu uma abordagem orientada ao design antes da implementação:

**Passo 1 — Modelagem do domínio**
Definição da hierarquia de classes antes de escrever qualquer linha de lógica: `Conta` (abstrata) → `ContaCorrente` e `ContaPoupanca`. `Transacao` modelada como `record` imutável — um registro financeiro não deve ser alterado após criado.

**Passo 2 — Enums como guardiões de regras**
`TipoDeposito`, `TipoTransacao` e `TipoConta` foram criados para eliminar o uso de Strings soltas no código. Isso garante consistência, facilita manutenção e torna o código autodocumentado.

**Passo 3 — Repositórios em memória**
`ContaRepository` e `InvestimentoRepository` encapsulam o acesso aos dados, simulando o padrão Repository que seria usado com JPA em produção. Essa decisão facilita a migração para persistência real no futuro.

**Passo 4 — Camada de serviço com regras de negócio**
`DepositoService` centraliza toda a lógica de validação de horário, dia da semana, feriados e tipo de depósito. `InvestimentoService` cuida de aportes, rendimentos e resgates. Nenhuma dessas regras vaza para o `BancoApp`.

**Passo 5 — Menu interativo como ponto de entrada**
`BancoApp` funciona como a camada de apresentação: lê entradas do usuário, delega para serviços e repositórios, e exibe resultados. Sem lógica de negócio — apenas coordenação.

---

## 5. Decisões Técnicas

**Por que `record` para Transacao?**
`record` em Java garante imutabilidade por padrão, gera `equals`, `hashCode` e `toString` automaticamente, e deixa explícito que `Transacao` é apenas um portador de dados — sem comportamento próprio. É a escolha semântica correta para um registro financeiro.

**Por que enums em vez de Strings ou constantes?**
Strings são perigosas: `"CORRENTE"` e `"corrente"` são valores diferentes para o compilador, mas equivalentes para o negócio. Enums eliminam essa classe inteira de bugs e tornam o código legível sem comentários.

**Por que repositórios separados do serviço?**
Misturar acesso a dados com regras de negócio é a causa mais comum de código difícil de testar e manter. A separação de responsabilidades garante que, ao trocar o armazenamento em memória por um banco real, apenas o repositório muda — o serviço permanece intacto.

**O que eu faria diferente hoje?**
Adicionaria testes unitários com JUnit 5 para validar as regras de horário e as operações financeiras. Também implementaria uma interface `ContaRepository` antes da classe concreta, facilitando o uso de mocks em testes e a substituição da implementação em memória por JPA.

---

## 6. Insights do Desenvolvimento

Durante o projeto, ficou evidente que:

- **A classe abstrata `Conta` foi a decisão mais impactante do design.** Ao centralizar saldo, histórico e o método de registro de transação na superclasse, qualquer nova operação em `ContaCorrente` ou `ContaPoupanca` herda rastreabilidade automaticamente — sem código duplicado.
- **As regras de horário revelaram a complexidade escondida em sistemas bancários reais.** O que parece simples ("só funciona das 10h às 16h") exige tratamento de `DayOfWeek`, `LocalTime`, feriados customizáveis e comportamentos distintos por canal (boca do caixa vs. caixa eletrônico).
- **Enums tornaram o menu interativo mais seguro.** Com `TipoConta` como enum, adicionar um novo tipo de conta no futuro requer apenas um novo valor no enum — e o compilador aponta onde o sistema precisa ser atualizado.
- **O padrão Repository, mesmo sem banco de dados, mudou a qualidade do código.** A decisão de encapsular as listas em classes de repositório separadas tornou o `BancoApp` significativamente mais simples e o código geral mais testável.

---

## 7. Resultados

Com o sistema implementado, o projeto entrega:

- ✅ Hierarquia de contas modelada com herança e abstração reais
- ✅ Dois canais de depósito com regras de horário e dia da semana validadas
- ✅ Transferências via PIX com registro de transação em ambas as contas
- ✅ Gerenciamento completo de investimentos: aplicação, aporte, rendimento e resgate
- ✅ Histórico de transações com timestamp completo (data, hora, minuto, segundo)
- ✅ Arquitetura em camadas (domínio → repositório → serviço → apresentação) pronta para escalar

---

## 8. Próximos Passos

- [ ] Adicionar testes unitários com **JUnit 5** para as regras de negócio de depósito e investimento
- [ ] Implementar **persistência real** com SQLite ou arquivo JSON para manter dados entre execuções
- [ ] Extrair interface `ContaRepository` para facilitar testes com mocks
- [ ] Adicionar **autenticação por PIN** por conta
- [ ] Migrar o menu console para uma interface web com **Spring Boot + Thymeleaf**
- [ ] Gerar **relatórios e exportação CSV** do histórico de transações

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 17+ | Linguagem principal |
| `java.time` (LocalDate, LocalTime, LocalDateTime) | Lógica de horário e timestamp das transações |
| Records (`record`) | Modelagem imutável da entidade `Transacao` |
| Enums | Contratos de tipo para conta, depósito e transação |
| Collections (List, Map) | Repositórios em memória |
| Scanner + switch-case | Menu interativo via console |

---

## 📂 Estrutura do Projeto

```
src/
└── com/sergioSantos/
    ├── app/
    │   └── BancoApp.java                  # Menu principal e ponto de entrada
    ├── service/
    │   ├── DepositoService.java           # Regras de depósito, horário e feriados
    │   └── InvestimentoService.java       # Lógica de aportes, rendimento e resgate
    ├── repository/
    │   ├── ContaRepository.java           # Repositório em memória de contas
    │   └── InvestimentoRepository.java    # Repositório em memória de investimentos
    └── domain/
        ├── Conta.java                     # Classe abstrata base (saldo, histórico, transações)
        ├── ContaCorrente.java             # Subclasse de conta corrente
        ├── ContaPoupanca.java             # Subclasse de conta poupança (suporte a investimentos)
        ├── Investimento.java              # Entidade de investimento vinculada à ContaPoupanca
        ├── Transacao.java                 # Record imutável de registro financeiro
        ├── TipoDeposito.java              # Enum: boca do caixa / caixa eletrônico e meios
        ├── TipoTransacao.java             # Enum: depósito, saque, PIX, investimento, resgate
        └── TipoConta.java                 # Enum: CORRENTE ou POUPANCA
```

---

## ▶️ Como Executar o Projeto

**Pré-requisitos:** Java 17+, Git

```bash
# Clone o repositório
git clone https://github.com/Santosdevbjj/transacaoFinanceira.git

# Acesse a pasta
cd transacaoFinanceira

# Compile o projeto
javac -d out -sourcepath src src/com/sergioSantos/app/BancoApp.java

# Execute a aplicação
java -cp out com.sergioSantos.app.BancoApp
```

O menu interativo será exibido no terminal. Siga as instruções para criar contas, realizar operações e consultar o histórico de transações.

---

## 🧠 Conceitos de POO Demonstrados

| Pilar | Onde foi aplicado |
|---|---|
| **Abstração** | `Conta` abstrata define o contrato sem expor implementação |
| **Herança** | `ContaCorrente` e `ContaPoupanca` estendem `Conta` |
| **Encapsulamento** | Atributos `private` com acesso controlado; `record` para `Transacao` |
| **Polimorfismo** | Serviços operam sobre `Conta` sem saber o tipo concreto |
| **Composição** | `Investimento` referencia `ContaPoupanca`; `Transacao` compõe o histórico |
| **Enums** | Contratos de tipo que eliminam Strings soltas e bugs silenciosos |

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License** — consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## Autor

**Sergio Santos**

[![Portfólio](https://img.shields.io/badge/Portfólio-Sérgio_Santos-111827?style=for-the-badge&logo=githubpages&logoColor=00eaff)](https://portfoliosantossergio.vercel.app)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Sérgio_Santos-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/santossergioluiz)
