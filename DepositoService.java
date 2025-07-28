package com.sergioSantos.service;
import com.sergioSantos.domain.*;
import java.time.*;
import java.util.*;

public class DepositoService {
    public static void depositar(Conta conta,
                                 double valor,
                                 TipoDeposito tipoDep,
                                 LocalDateTime agora,
                                 List<LocalDate> feriados) {

        DayOfWeek dow = agora.getDayOfWeek();
        LocalTime lt = agora.toLocalTime();
        boolean ehFeriado = feriados.contains(agora.toLocalDate());

        if (tipoDep.name().startsWith("CAIXA_ELETRONICO")) {
            if (!(dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY || ehFeriado)) {
                if (lt.isBefore(LocalTime.of(10, 0)) || lt.isAfter(LocalTime.of(16, 0))) {
                    // fora expediente
                }
            }
        }

        conta.saldo += valor;

        TipoTransacao tipoTrans = tipoDep.name().contains("CHEQUE")
            ? TipoTransacao.DEPOSITO_CHEQUE
            : TipoTransacao.DEPOSITO_DINHEIRO;

        conta.registrar(new Transacao(tipoTrans, tipoDep, valor, agora,
                                       "Depósito " + tipoDep.name()));
    }
}
