package io.github.joaopbmello.contasdeviagem.presentation.viagem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import io.github.joaopbmello.contasdeviagem.presentation.FormularioDespesa
import io.github.joaopbmello.contasdeviagem.presentation.componentes.CampoData
import io.github.joaopbmello.contasdeviagem.presentation.componentes.CampoFormulario
import io.github.joaopbmello.contasdeviagem.presentation.componentes.FormularioBase
import io.github.joaopbmello.contasdeviagem.presentation.componentes.SeletorComprovante

@Composable
fun NovaDespesaScreen(
    nomeDaViagem: String,
    formulario: FormularioDespesa,
    onFormularioChange: (FormularioDespesa) -> Unit,
    onSalvar: () -> Unit,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FormularioBase(
        titulo = "Lançar despesa",
        subtitulo = nomeDaViagem.ifBlank { null },
        rotuloSalvar = "Salvar despesa",
        podeSalvar = formulario.valido,
        onSalvar = onSalvar,
        onVoltar = onVoltar,
        modifier = modifier,
    ) {
        CampoFormulario(
            valor = formulario.descricao,
            onValorChange = { onFormularioChange(formulario.copy(descricao = it)) },
            rotulo = "Produto ou serviço",
            dica = "Ex.: almoço, hotel, abastecimento",
        )
        CampoData(
            digitos = formulario.data,
            onDigitosChange = { onFormularioChange(formulario.copy(data = it)) },
            rotulo = "Data",
            erro = formulario.erroData,
        )
        CampoFormulario(
            valor = formulario.cidade,
            onValorChange = { onFormularioChange(formulario.copy(cidade = it)) },
            rotulo = "Cidade",
            dica = "Ex.: Mossoró/RN",
        )
        CampoFormulario(
            valor = formulario.valor,
            onValorChange = { texto ->
                onFormularioChange(formulario.copy(valor = texto.filter { it.isDigit() || it == ',' || it == '.' }))
            },
            rotulo = "Valor (R$)",
            erro = formulario.erroValor,
            dica = "Ex.: 39,90",
            teclado = KeyboardType.Decimal,
        )
        SeletorComprovante(
            selecionado = formulario.tipoComprovante,
            onSelecionar = { onFormularioChange(formulario.copy(tipoComprovante = it)) },
        )
    }
}
