package com.sergioSantos.domain;
import java.time.LocalDateTime;
public record Transacao(
    TipoTransacao tipo,
    TipoDeposito tipoDeposito,
    double valor,
    LocalDateTime timestamp,
    String detalhe
) {}
