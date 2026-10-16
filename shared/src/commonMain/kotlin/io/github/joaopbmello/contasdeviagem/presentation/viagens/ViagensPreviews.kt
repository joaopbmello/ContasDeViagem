package io.github.joaopbmello.contasdeviagem.presentation.viagens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaopbmello.contasdeviagem.presentation.FormularioViagem
import io.github.joaopbmello.contasdeviagem.presentation.tema.ContasDeViagemTema
import io.github.joaopbmello.contasdeviagem.presentation.viagensDeExemplo

@Preview
@Composable
private fun ViagensComDadosPreview() {
    ContasDeViagemTema {
        ViagensScreen(viagens = viagensDeExemplo, onAbrirViagem = {}, onNovaViagem = {})
    }
}

@Preview
@Composable
private fun ViagensEscuroPreview() {
    ContasDeViagemTema(escuro = true) {
        ViagensScreen(viagens = viagensDeExemplo, onAbrirViagem = {}, onNovaViagem = {}, selecionada = 1)
    }
}

@Preview
@Composable
private fun ViagensVaziaPreview() {
    ContasDeViagemTema {
        ViagensScreen(viagens = emptyList(), onAbrirViagem = {}, onNovaViagem = {})
    }
}

@Preview
@Composable
private fun NovaViagemVaziaPreview() {
    ContasDeViagemTema {
        NovaViagemScreen(formulario = FormularioViagem(), onFormularioChange = {}, onSalvar = {}, onVoltar = {})
    }
}

@Preview
@Composable
private fun NovaViagemComErroPreview() {
    ContasDeViagemTema {
        NovaViagemScreen(
            formulario = FormularioViagem(destino = "Interior do Ceará", inicio = "3210", fim = "2210"),
            onFormularioChange = {},
            onSalvar = {},
            onVoltar = {},
        )
    }
}
