package io.github.joaopbmello.contasdeviagem.presentation.componentes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import io.github.joaopbmello.contasdeviagem.presentation.comBarra

private const val DIGITOS_DA_DATA = 4
private const val POSICAO_DA_BARRA = 2

@Composable
fun CampoData(
    digitos: String,
    onDigitosChange: (String) -> Unit,
    rotulo: String,
    modifier: Modifier = Modifier,
    erro: String? = null,
) {
    CampoFormulario(
        valor = digitos,
        onValorChange = { texto -> onDigitosChange(texto.filter(Char::isDigit).take(DIGITOS_DA_DATA)) },
        rotulo = rotulo,
        modifier = modifier,
        erro = erro,
        dica = "Dia e mês, como 18/08",
        teclado = KeyboardType.Number,
        transformacao = MascaraDeData,
    )
}

private object MascaraDeData : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digitos = text.text
        val mapa =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = if (offset > POSICAO_DA_BARRA) offset + 1 else offset

                override fun transformedToOriginal(offset: Int): Int =
                    (if (offset > POSICAO_DA_BARRA) offset - 1 else offset).coerceIn(0, digitos.length)
            }
        return TransformedText(AnnotatedString(comBarra(digitos)), mapa)
    }
}
