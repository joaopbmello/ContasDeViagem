package io.github.joaopbmello.contasdeviagem.presentation.viagem

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaopbmello.contasdeviagem.presentation.FormularioDespesa
import io.github.joaopbmello.contasdeviagem.presentation.tema.ContasDeViagemTema
import io.github.joaopbmello.contasdeviagem.presentation.viagensDeExemplo

@Preview
@Composable
private fun ViagemComDespesasPreview() {
    ContasDeViagemTema {
        ViagemScreen(viagem = viagensDeExemplo.first(), onLancarDespesa = {}, onVoltar = {})
    }
}

@Preview
@Composable
private fun ViagemComDespesasEscuroPreview() {
    ContasDeViagemTema(escuro = true) {
        ViagemScreen(viagem = viagensDeExemplo.first(), onLancarDespesa = {}, onVoltar = {})
    }
}

@Preview
@Composable
private fun ViagemSemDespesasPreview() {
    ContasDeViagemTema {
        ViagemScreen(
            viagem = viagensDeExemplo.first().copy(despesas = emptyList()),
            onLancarDespesa = {},
            onVoltar = {},
        )
    }
}

@Preview
@Composable
private fun ViagemNaoEncontradaPreview() {
    ContasDeViagemTema {
        ViagemScreen(viagem = null, onLancarDespesa = {}, onVoltar = {})
    }
}

@Preview
@Composable
private fun NovaDespesaVaziaPreview() {
    ContasDeViagemTema {
        NovaDespesaScreen(
            nomeDaViagem = "Sertão do RN",
            formulario = FormularioDespesa(),
            onFormularioChange = {},
            onSalvar = {},
            onVoltar = {},
        )
    }
}

@Preview
@Composable
private fun NovaDespesaComErrosPreview() {
    ContasDeViagemTema {
        NovaDespesaScreen(
            nomeDaViagem = "Sertão do RN",
            formulario =
                FormularioDespesa(descricao = "Almoço", data = "3213", cidade = "Caicó/RN", valor = "12,345"),
            onFormularioChange = {},
            onSalvar = {},
            onVoltar = {},
        )
    }
}
