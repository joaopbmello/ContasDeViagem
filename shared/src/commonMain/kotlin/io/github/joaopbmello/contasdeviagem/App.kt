package io.github.joaopbmello.contasdeviagem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.joaopbmello.contasdeviagem.domain.Despesa
import io.github.joaopbmello.contasdeviagem.domain.TipoComprovante
import io.github.joaopbmello.contasdeviagem.presentation.viagem.ViagemScreen

private val despesasProvisorias =
    listOf(
        Despesa(
            id = "1",
            data = "18/08",
            cidade = "Lajes/RN",
            descricao = "Café da manhã",
            tipoComprovante = TipoComprovante.NOTA_FISCAL,
            valorEmCentavos = 3_900,
        ),
        Despesa(
            id = "2",
            data = "18/08",
            cidade = "Solonópolis/CE",
            descricao = "Almoço",
            tipoComprovante = TipoComprovante.RECIBO,
            valorEmCentavos = 3_100,
        ),
        Despesa(
            id = "3",
            data = "18/08",
            cidade = "Lajes/RN",
            descricao = "Abastecimento",
            tipoComprovante = TipoComprovante.NOTA_FISCAL,
            valorEmCentavos = 17_060,
        ),
    )

@Composable
fun App() {
    MaterialTheme {
        ViagemScreen(
            titulo = "Interior do Ceará",
            periodo = "18/08 a 20/08",
            despesas = despesasProvisorias,
            onDespesaClick = {},
            onLancarDespesa = {},
        )
    }
}
