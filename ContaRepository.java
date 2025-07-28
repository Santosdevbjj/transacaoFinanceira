package com.sergioSantos.repository;
import com.sergioSantos.domain.Conta;
import java.util.*;

public class ContaRepository {
    private final Map<String, Conta> contas = new HashMap<>();
    public void salvar(Conta c) { contas.put(c.getNumero(), c); }
    public Conta buscar(String numero) { return contas.get(numero); }
    public Collection<Conta> listar() { return contas.values(); }
}
