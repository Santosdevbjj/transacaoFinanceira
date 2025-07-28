package com.sergioSantos.domain;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String numero) { super(numero); }
    @Override public TipoConta getTipo() { return TipoConta.POUPANCA; }
}
