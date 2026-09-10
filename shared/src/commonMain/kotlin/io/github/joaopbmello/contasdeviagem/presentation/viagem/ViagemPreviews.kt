package io.github.joaopbmello.contasdeviagem.presentation.viagem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaopbmello.contasdeviagem.domain.Despesa
import io.github.joaopbmello.contasdeviagem.domain.TipoComprovante

private val despesasDeExemplo =
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

@Preview
@Composable
private fun ViagemScreenComDespesasPreview() {
    MaterialTheme {
        ViagemScreen(
            titulo = "Interior do Ceará",
            periodo = "18/08 a 20/08",
            despesas = despesasDeExemplo,
            onDespesaClick = {},
            onLancarDespesa = {},
        )
    }
}

@Preview
@Composable
private fun ViagemScreenVaziaPreview() {
    MaterialTheme {
        ViagemScreen(
            titulo = "Interior do Ceará",
            periodo = "18/08 a 20/08",
            despesas = emptyList(),
            onDespesaClick = {},
            onLancarDespesa = {},
        )
    }
}