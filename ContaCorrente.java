package com.sergioSantos.domain;

public class ContaCorrente extends Conta {
    public ContaCorrente(String numero) { super(numero); }
    @Override public TipoConta getTipo() { return TipoConta.CORRENTE; }
}
