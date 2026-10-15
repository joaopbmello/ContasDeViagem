package io.github.joaopbmello.contasdeviagem.presentation.viagens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.joaopbmello.contasdeviagem.presentation.FormularioViagem
import io.github.joaopbmello.contasdeviagem.presentation.componentes.CampoData
import io.github.joaopbmello.contasdeviagem.presentation.componentes.CampoFormulario
import io.github.joaopbmello.contasdeviagem.presentation.componentes.FormularioBase

/**
 * Formulário de nova viagem. A cidade de saída já vem preenchida com Natal/RN, de onde
 * as viagens partem, mas pode ser trocada.
 */
@Composable
fun NovaViagemScreen(
    formulario: FormularioViagem,
    onFormularioChange: (FormularioViagem) -> Unit,
    onSalvar: () -> Unit,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FormularioBase(
        titulo = "Nova viagem",
        rotuloSalvar = "Salvar viagem",
        podeSalvar = formulario.valido,
        onSalvar = onSalvar,
        onVoltar = onVoltar,
        modifier = modifier,
    ) {
        CampoFormulario(
            valor = formulario.destino,
            onValorChange = { onFormularioChange(formulario.copy(destino = it)) },
            rotulo = "Destino",
            dica = "Ex.: interior do Ceará",
        )
        CampoFormulario(
            valor = formulario.saida,
            onValorChange = { onFormularioChange(formulario.copy(saida = it)) },
            rotulo = "Cidade de saída",
        )
        CampoData(
            digitos = formulario.inicio,
            onDigitosChange = { onFormularioChange(formulario.copy(inicio = it)) },
            rotulo = "Início",
            erro = formulario.erroInicio,
        )
        CampoData(
            digitos = formulario.fim,
            onDigitosChange = { onFormularioChange(formulario.copy(fim = it)) },
            rotulo = "Fim",
            erro = formulario.erroFim,
        )
    }
}
