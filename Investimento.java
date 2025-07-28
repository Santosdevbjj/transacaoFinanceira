package com.sergioSantos.domain;
import java.time.LocalDateTime;
public class Investimento {
    public final String id;
    public final ContaPoupanca conta;
    public double valor;
    public final LocalDateTime criado;
    public Investimento(String id, ContaPoupanca conta, double valorInicial) {
        this.id = id;
        this.conta = conta;
        this.valor = valorInicial;
        this.criado = LocalDateTime.now();
    }
}
