package com.sergioSantos.app;

import com.sergioSantod.domain.*;
import com.sergioSantos.repository.*;
import com.sergioSantos.service.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BancoApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final ContaRepository contaRepo = new ContaRepository();
    private static final InvestimentoRepository invRepo = new InvestimentoRepository<>();
    private static final List<LocalDate> feriados = List.of(
        LocalDate.of(2025, 1, 1), LocalDate.of(2025, 4, 21)
    );
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Criar Investimento");
            System.out.println("3 - Investir (aplique extra)");
            System.out.println("4 - Depositar na Conta");
            System.out.println("5 - Sacar na Conta");
            System.out.println("6 - Transferência PIX");
            System.out.println("7 - Sacar Investimento");
            System.out.println("8 - Listar Contas");
            System.out.println("9 - Listar Investimentos");
            System.out.println("10 - Histórico de Conta");
            System.out.println("11 - Atualizar Investimentos");
            System.out.println("12 - Sair");
            System.out.print("Escolha: ");

            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1 -> criarConta();
                case 2 -> criarInvestimento();
                case 3 -> investirExtra();
                case 4 -> depositar();
                case 5 -> sacar();
                case 6 -> transferirPix();
                case 7 -> resgatarInvestimento();
                case 8 -> listarContas();
                case 9 -> listarInvestimentos();
                case 10 -> historicoConta();
                case 11 -> atualizarInvestimentos();
                case 12 -> { System.out.println("Encerrando..."); return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarConta() {
        System.out.print("Número: "); String num = sc.nextLine();
        System.out.print("Tipo (1‑Corrente,2‑Poupança): "); int t = sc.nextInt(); sc.nextLine();
        Conta c = (t == 1) ? new ContaCorrente(num) : new ContaPoupanca(num);
        contaRepo.salvar(c);
        System.out.println("Conta criada.");
    }

    private static void criarInvestimento() {
        System.out.print("ID investimento: "); String id = sc.nextLine();
        System.out.print("Conta poupança: "); String nc = sc.nextLine();
        Conta c = contaRepo.buscar(nc);
        if (!(c instanceof ContaPoupanca cp)) {
            System.out.println("Conta poupança inválida."); return;
        }
        System.out.print("Valor inicial: "); double v = sc.nextDouble(); sc.nextLine();
        Investimento inv = new Investimento(id, cp, v);
        invRepo.salvar(inv);
        InvestimentoService.aplicarInicial(cp, inv);
        System.out.println("Investimento criado.");
    }

    private static void investirExtra() {
        System.out.print("ID investimento: "); String id = sc.nextLine();
        Investimento inv = invRepo.buscar(id);
        if (inv == null) {
            System.out.println("Inv não existe."); return;
        }
        System.out.print("Valor extra: "); double v = sc.nextDouble(); sc.nextLine();
        InvestimentoService.aplicarExtra(inv, v);
        System.out.println("Aplicado extra.");
    }

    private static void depositar() {
        System.out.print("Conta: "); String nc = sc.nextLine();
        Conta c = contaRepo.buscar(nc);
        if (c == null) {
            System.out.println("Conta não encontrada."); return;
        }
        System.out.print("Valor: "); double v = sc.nextDouble(); sc.nextLine();
        System.out.println("Tipo depósito: 1‑boca caixa dinheiro, 2‑boca caixa cheque, 3‑caixa eletrônico dinheiro, 4‑envelope, 5‑cheque caixa eletrônico");
        int td = sc.nextInt(); sc.nextLine();
        TipoDeposito tipo = switch (td) {
            case 1 -> TipoDeposito.BOCA_CAIXA_DINHEIRO;
            case 2 -> TipoDeposito.BOCA_CAIXA_CHEQUE;
            case 3 -> TipoDeposito.CAIXA_ELETRONICO_DINHEIRO;
            case 4 -> TipoDeposito.CAIXA_ELETRONICO_ENVELOPE;
            case 5 -> TipoDeposito.CAIXA_ELETRONICO_CHEQUE;
            default -> {
                System.out.println("Tipo inválido."); yield null;
            }
        };
        if (tipo == null) return;
        LocalDateTime agora = LocalDateTime.now();
        DepositoService.depositar(c, v, tipo, agora, feriados);
        System.out.println("Depósito OK em " + agora.format(fmt));
    }

    private static void sacar() {
        System.out.print("Conta: "); String nc = sc.nextLine();
        Conta c = contaRepo.buscar(nc);
        if (c == null) { System.out.println("Conta não encontrada."); return; }
        System.out.print("Valor saque: "); double v = sc.nextDouble(); sc.nextLine();
        if (c.getSaldo() >= v) {
            c.saldo -= v;
            c.registrar(new Transacao(TipoTransacao.SAQUE, null, v, LocalDateTime.now(), "Saque"));
            System.out.println("Saque OK.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    private static void transferirPix() {
        System.out.print("Origem: "); String o = sc.nextLine();
        System.out.print("Destino: "); String d = sc.nextLine();
        Conta co = contaRepo.buscar(o), cd = contaRepo.buscar(d);
        if (co == null || cd == null) { System.out.println("Conta inválida."); return; }
        System.out.print("Valor: "); double v = sc.nextDouble(); sc.nextLine();
        if (co.getSaldo() >= v) {
            co.saldo -= v;
            cd.saldo += v;
            LocalDateTime now = LocalDateTime.now();
            co.registrar(new Transacao(TipoTransacao.TRANSFERENCIA_PIX, null, v, now, "PIX p/ "+d));
            cd.registrar(new Transacao(TipoTransacao.TRANSFERENCIA_PIX, null, v, now, "PIX de "+o));
            System.out.println("PIX efetuado.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    private static void resgatarInvestimento() {
        System.out.print("ID inv: "); String id = sc.nextLine();
        Investimento inv = invRepo.buscar(id);
        if (inv == null) { System.out.println("Investimento não existe."); return; }
        System.out.print("Valor: "); double v = sc.nextDouble(); sc.nextLine();
        InvestimentoService.resgatar(inv, v);
        System.out.println("Resgatado.");
    }

    private static void listarContas() {
        System.out.println("Contas:");
        contaRepo.listar().forEach(c ->
            System.out.printf("%s (%s): %.2f%n", c.getNumero(), c.getTipo(), c.getSaldo()));
    }

    private static void listarInvestimentos() {
        System.out.println("Investimentos:");
        invRepo.listar().forEach(inv ->
            System.out.printf("%s em %s: %.2f%n", inv.id, inv.conta.getNumero(), inv.valor));
    }

    private static void historicoConta() {
        System.out.print("Conta: "); String nc = sc.nextLine();
        Conta c = contaRepo.buscar(nc);
        if (c == null) { System.out.println("Não encontrada."); return; }
        c.getHistorico().forEach(t ->
            System.out.printf("%s | %s | %s | %.2f | %s%n",
                t.timestamp().format(fmt),
                t.tipo(), t.tipoDeposito(), t.valor(), t.detalhe()));
    }

    private static void atualizarInvestimentos() {
        invRepo.listar().forEach(inv -> InvestimentoService.atualizarRendimento(inv, 0.01));
        System.out.println("Investimentos atualizados.");
    }
}
