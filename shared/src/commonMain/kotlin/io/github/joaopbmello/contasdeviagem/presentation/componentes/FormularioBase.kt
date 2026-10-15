package io.github.joaopbmello.contasdeviagem.presentation.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val larguraMaxima = 600.dp
private val espacamento = 16.dp
private val espacoEntreCampos = 12.dp

@Composable
fun FormularioBase(
    titulo: String,
    rotuloSalvar: String,
    podeSalvar: Boolean,
    onSalvar: () -> Unit,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier,
    subtitulo: String? = null,
    campos: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { BarraDeTopo(titulo = titulo, subtitulo = subtitulo, onVoltar = onVoltar) },
    ) { espacoDoScaffold ->
        Box(
            modifier = Modifier.fillMaxSize().padding(espacoDoScaffold).imePadding(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier =
                    Modifier
                        .widthIn(max = larguraMaxima)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(espacamento),
                verticalArrangement = Arrangement.spacedBy(espacoEntreCampos),
            ) {
                campos()
                Button(onClick = onSalvar, enabled = podeSalvar, modifier = Modifier.fillMaxWidth()) {
                    Text(text = rotuloSalvar)
                }
                if (!podeSalvar) {
                    Text(
                        text = "Preencha todos os campos corretamente para salvar.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}
