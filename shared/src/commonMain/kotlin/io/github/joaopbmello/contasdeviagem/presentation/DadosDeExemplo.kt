package io.github.joaopbmello.contasdeviagem.presentation

import io.github.joaopbmello.contasdeviagem.domain.Despesa
import io.github.joaopbmello.contasdeviagem.domain.TipoComprovante
import io.github.joaopbmello.contasdeviagem.domain.Viagem

internal val viagensDeExemplo =
    listOf(
        Viagem(
            id = 1,
            destino = "Sertão do RN",
            saida = "Natal/RN",
            inicio = "06/10",
            fim = "08/10",
            despesas =
                listOf(
                    Despesa("1-1", "06/10", "Caicó/RN", "Café da manhã", TipoComprovante.NOTA_FISCAL, 2_550),
                    Despesa("1-2", "06/10", "Jucurutu/RN", "Almoço", TipoComprovante.RECIBO, 4_200),
                    Despesa("1-3", "07/10", "Pau dos Ferros/RN", "Abastecimento", TipoComprovante.NOTA_FISCAL, 19_830),
                    Despesa("1-4", "07/10", "Pau dos Ferros/RN", "Hotel", TipoComprovante.RECIBO, 14_000),
                ),
        ),
        Viagem(
            id = 2,
            destino = "Litoral Norte",
            saida = "Natal/RN",
            inicio = "13/10",
            fim = "14/10",
            despesas =
                listOf(
                    Despesa("2-1", "13/10", "Touros/RN", "Jantar", TipoComprovante.RECIBO, 3_500),
                ),
        ),
    )
