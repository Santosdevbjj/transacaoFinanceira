package com.sergioSantos.domain;
import java.util.*;
public abstract class Conta {
    protected final String numero;
    protected double saldo;
    protected final List<Transacao> historico = new ArrayList<>();
    public Conta(String numero) { this.numero = numero; this.saldo = 0; }
    public String getNumero() { return numero; }
    public double getSaldo() { return saldo; }
    public List<Transacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }
    public void registrar(Transacao t) { historico.add(t); }
    public abstract TipoConta getTipo();
}
