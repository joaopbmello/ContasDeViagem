package io.github.joaopbmello.contasdeviagem.presentation.viagem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.joaopbmello.contasdeviagem.domain.Despesa
import io.github.joaopbmello.contasdeviagem.domain.formatarValorEmReais

private val EspacamentoInterno = 16.dp
private val EspacoEntreTextoEValor = 12.dp

@Composable
fun DespesaItem(
    despesa: Despesa,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(EspacamentoInterno),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = despesa.descricao,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "${despesa.data}, ${despesa.cidade}, ${despesa.tipoComprovante.rotulo}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Spacer(modifier = Modifier.width(EspacoEntreTextoEValor))
            Text(
                text = formatarValorEmReais(despesa.valorEmCentavos),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}