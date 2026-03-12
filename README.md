## Criando um Aplicativo de Controle de Transações Financeiras com POO.

![NTT-Java](https://github.com/user-attachments/assets/af4f7d76-2bff-4dc5-9bb7-74f8e79ff1c9)


**Bootcamp, NTT DATA - Java e IA para Iniciantes.**



🏦 **BancoPOO – Sistema Bancário em Java (Console)**

📌 **Descrição do Projeto**

Este é um projeto Java com foco em Programação Orientada a Objetos (POO) que demonstra herança, encapsulamento, polimorfismo, abstração e reuso de código.

A aplicação simula um sistema bancário básico, permitindo:

Criação de contas (corrente e poupança)

Depósitos diferenciados:

Boca do caixa (dinheiro ou cheque)

Caixa eletrônico (dinheiro, envelope, cheque)

Regras por horário de atendimento (10h–16h) em dias úteis, e comportamento distinto em sábados, domingos e feriados


Saques

Transferências via PIX

Criação e gestão de investimentos (aplicação inicial, aportes extras, atualização de rendimento, resgate)

Histórico de transações com timestamp completo (data, hora, minuto, segundo)

Menu console interativo (BancoApp no pacote app)


O sistema utiliza repositórios em memória para contas e investimento, com lógica separada em serviços, e armazenamento dos dados temporariamente enquanto o programa roda.




🗂️ **Estrutura e Função de Cada Arquivo**

**Arquivo	Pacote	Descrição**

**README.md**	—	Documentação do projeto, visão geral, propósito, uso e estrutura.

**LICENSE.md**	—	Texto completo da MIT License, com aviso de direitos autorais.

**BancoApp.java**	com.sergioSantos.app	Classe principal que inicia o programa, exibe o menu de opções e chama os serviços e repositórios conforme escolhas do usuário.

**DepositoService.java**	com.sergioSantos.service	Lógica de negócio para depósitos, incluindo validação de tipo de depósito, horário, dia da semana e feriados. Encapsula regras de crédito e registro de transações.

**InvestimentoService.java**	com.sergioSantos.service	Funções para aplicar valores iniciais e extras em investimentos, resgatar e atualizar rendimento. Registra as transações correspondentes.

**ContaRepository.java**	com.sergioSantos.repository	Repositório em memória que gerencia contas — salvar, buscar e listar objetos Conta.

**InvestimentoRepository.java**	com.sergioSantos.repository	Repositório em memória que gerencia investimentos — salvar, buscar e listar objetos Investimento.

**Investimento.java**	com.sergioSantos.domain	Entidade de investimento vinculada a uma ContaPoupanca; armazena ID, valor atual e data de criação.

**ContaCorrente.java**	com.sergioSantos.domain	Subclasse de Conta que representa conta corrente (sem restrições especiais).

**ContaPoupanca.java**	com.sergioSantos.domain	Subclasse de Conta que representa conta poupança, utilizada especialmente para investimentos.

**Conta.java**	com.sergioSantos.domain	Classe abstrata que define os campos e métodos comuns das contas: número, saldo, histórico e registro de transações.

**Transacao.java**	com.sergioSantos.domain	record que representa uma transação financeira; guarda tipo, tipo de depósito, valor, timestamp e detalhe.

**TipoDeposito.java**	com.sergioSantos.domain	Enum com os modos de depósito suportados: boca do caixa (dinheiro/cheque), caixa eletrônico (dinheiro, envelope, cheque).

**TipoTransacao.java**	com.sergioSantos.domain	Enum que classifica transações: depósito (cheque/dinheiro), saque, PIX, investimento, resgate.

**TipoConta.java**	com.sergioSantos.domain	Enum para tipos de conta: CORRENTE ou POUPANCA.




🚀 **Visão Geral do Fluxo do Sistema**

**1. BancoApp exibe menu**, lê entrada do usuário e chama métodos auxiliares (criarConta, depositar, etc.).


**2. Tipos de transação e depósito** são representados por enums (TipoDeposito, TipoTransacao, TipoConta), garantindo clareza e consistência.


**3. A conta é modelada por Conta (abstrata)**, com subclasses ContaCorrente e ContaPoupanca.


**4. Repositórios (ContaRepository e InvestimentoRepository)** armazenam dados em memória durante a execução.


**5. A lógica de negócio (ex: regras de depósito, atualizações de investimento)** está centralizada nos serviços (DepositoService, InvestimentoService).


**6. Cada operação relevante gera um registro** de transação com data e hora completas.



🧠 **Conceitos de Aprendizado Demonstrados**

**OOP:** classes abstratas, Conta, subclasses para tipos de conta, enums (TipoConta, TipoTransacao, TipoDeposito)

**Encapsulamento:** uso de private, métodos de acesso, record para Transacao

**Polimorfismo e abstração**, composição com Transacao e repositórios

**Repositórios em memória** para contas e investimentos

**Regras de lógica temporal** (DayOfWeek, LocalTime, feriados customizáveis)

**Menu de console** com Scanner, validações e fluxo interativo

**Documentação interna** nos métodos (via comentários) 



⚙️ **Tecnologias**

**Linguagem:** **Java 17+**

**Data/hora:** java.time (LocalDate, LocalTime, LocalDateTime)

**Estrutura de dados:** List, Map, etc.

**Lógica de menu** com Scanner e switch-case












📄 **Licença**

Este projeto está licenciado sob a MIT License — **veja o arquivo LICENSE** para mais detalhes — uma licença permissiva e comum em projetos open‑source.







## 📝 Licença
Este projeto está sob licença MIT — consulte o arquivo LICENSE.

## 🙌 Contribuições e melhorias
- Adicionar GUI (JavaFX/Swing)
- Persistência real (JSON/SQLite)
- Autenticação por PIN
- Relatórios/exportação CSV





![Java Version](https://img.shields.io/badge/java-17+-blue.svg) 


---

**Contato:**

[![Portfólio Sérgio Santos](https://img.shields.io/badge/Portfólio-Sérgio_Santos-111827?style=for-the-badge&logo=githubpages&logoColor=00eaff)](https://portfoliosantossergio.vercel.app)

[![LinkedIn Sérgio Santos](https://img.shields.io/badge/LinkedIn-Sérgio_Santos-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/santossergioluiz)

---


