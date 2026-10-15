package io.github.joaopbmello.contasdeviagem.presentation.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTopo(
    titulo: String,
    modifier: Modifier = Modifier,
    subtitulo: String? = null,
    onVoltar: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column(modifier = Modifier.semantics(mergeDescendants = true) { heading() }) {
                Text(text = titulo, maxLines = 1, overflow = TextOverflow.Ellipsis)
                if (subtitulo != null) {
                    Text(
                        text = subtitulo,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        },
        navigationIcon = {
            if (onVoltar != null) {
                TextButton(onClick = onVoltar) { Text(text = "Voltar") }
            }
        },
    )
}
