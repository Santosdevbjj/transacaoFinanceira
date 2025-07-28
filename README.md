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


O sistema utiliza repositórios em memória para contar e investir, com lógica separada em serviços, e armazenamento dos dados temporariamente enquanto o programa roda.



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



DepositoServiceTest.java	src/test/java/com/seuNome/service	Testes unitários JUnit 5 para a lógica de depósitos, cobrindo cenários em horários diferentes e feriados. Simula LocalDateTime para validar regras.



🚀 **Visão Geral do Fluxo do Sistema**

**1. BancoApp exibe menu**, lê entrada do usuário e chama métodos auxiliares (criarConta, depositar, etc.).


**2. Tipos de transação e depósito** são representados por enums (TipoDeposito, TipoTransacao, TipoConta), garantindo clareza e consistência.


**3. A conta é modelada por Conta (abstrata)**, com subclasses ContaCorrente e ContaPoupanca.


**4. Repositórios (ContaRepository e InvestimentoRepository)** armazenam dados em memória durante a execução.


**5. A lógica de negócio (ex: regras de depósito, atualizações de investimento)** está centralizada nos serviços (DepositoService, InvestimentoService).


**6. Cada operação relevante gera um registro** de transação com data e hora completas.










