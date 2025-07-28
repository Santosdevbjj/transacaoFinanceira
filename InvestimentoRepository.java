package com.sergioSantos.repository;
import com.sergioSantos.domain.Investimento;
import java.util.*;

public class InvestimentoRepository {
    private final Map<String, Investimento> invs = new HashMap<>();
    public void salvar(Investimento i) { invs.put(i.id, i); }
    public Investimento buscar(String id) { return invs.get(id); }
    public Collection<Investimento> listar() { return invs.values(); }
}
