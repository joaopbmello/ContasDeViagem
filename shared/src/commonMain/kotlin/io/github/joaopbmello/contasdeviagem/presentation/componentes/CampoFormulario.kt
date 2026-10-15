package io.github.joaopbmello.contasdeviagem.presentation.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CampoFormulario(
    valor: String,
    onValorChange: (String) -> Unit,
    rotulo: String,
    modifier: Modifier = Modifier,
    erro: String? = null,
    dica: String? = null,
    teclado: KeyboardType = KeyboardType.Text,
    transformacao: VisualTransformation = VisualTransformation.None,
) {
    val textoDeApoio = erro ?: dica
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(text = rotulo) },
        supportingText = textoDeApoio?.let { texto -> { Text(text = texto) } },
        isError = erro != null,
        singleLine = true,
        visualTransformation = transformacao,
        keyboardOptions = KeyboardOptions(keyboardType = teclado, imeAction = ImeAction.Next),
        modifier = modifier.fillMaxWidth().semantics { if (erro != null) error(erro) },
    )
}
