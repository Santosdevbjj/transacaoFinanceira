package com.sergioSantos.service;
import com.sergioSantos.domain.*;
import java.time.LocalDateTime;

public class InvestimentoService {
    public static void aplicarInicial(ContaPoupanca cp, Investimento inv) {
        cp.saldo -= inv.valor;
        cp.registrar(new Transacao(TipoTransacao.INVESTIMENTO, null,
                                   inv.valor, LocalDateTime.now(),
                                   "Aplicação inicial em " + inv.id));
    }

    public static void aplicarExtra(Investimento inv, double valorExtra) {
        inv.conta.saldo -= valorExtra;
        inv.valor += valorExtra;
        inv.conta.registrar(new Transacao(TipoTransacao.INVESTIMENTO, null,
                                           valorExtra, LocalDateTime.now(),
                                           "Aplicação extra em " + inv.id));
    }

    public static void resgatar(Investimento inv, double valor) {
        inv.valor -= valor;
        inv.conta.saldo += valor;
        inv.conta.registrar(new Transacao(TipoTransacao.RESGATE, null,
                                           valor, LocalDateTime.now(),
                                           "Resgate de " + inv.id));
    }

    public static void atualizarRendimento(Investimento inv, double taxa) {
        double rend = inv.valor * taxa;
        inv.valor += rend;
        inv.conta.registrar(new Transacao(TipoTransacao.INVESTIMENTO, null,
                                           rend, LocalDateTime.now(),
                                           "Rendimento " + inv.id));
    }
}
