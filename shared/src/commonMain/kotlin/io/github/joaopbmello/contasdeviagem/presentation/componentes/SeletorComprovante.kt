package io.github.joaopbmello.contasdeviagem.presentation.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import io.github.joaopbmello.contasdeviagem.domain.TipoComprovante

private val alvoMinimo = 48.dp
private val espacoAntesDoTexto = 12.dp

@Composable
fun SeletorComprovante(
    selecionado: TipoComprovante,
    onSelecionar: (TipoComprovante) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().selectableGroup()) {
        Text(
            text = "Comprovante",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 4.dp),
        )
        TipoComprovante.entries.forEach { tipo ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .heightIn(min = alvoMinimo)
                        .selectable(
                            selected = tipo == selecionado,
                            role = Role.RadioButton,
                            onClick = { onSelecionar(tipo) },
                        ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = tipo == selecionado, onClick = null)
                Spacer(modifier = Modifier.width(espacoAntesDoTexto))
                Text(text = tipo.nome, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
