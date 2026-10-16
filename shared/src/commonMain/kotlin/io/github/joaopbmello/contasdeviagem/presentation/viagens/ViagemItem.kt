package io.github.joaopbmello.contasdeviagem.presentation.viagens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.joaopbmello.contasdeviagem.domain.Viagem
import io.github.joaopbmello.contasdeviagem.domain.formatarValorEmReais

private val espacamentoInterno = 16.dp
private val espacoAntesDoResumo = 8.dp

/**
 * Uma viagem na lista: destino, saída, período, quantidade de despesas e total.
 *
 * O cartão inteiro é tocável e é um só elemento para o leitor de tela. Em janela larga,
 * a viagem aberta ao lado fica destacada e é anunciada como selecionada.
 */
@Composable
fun ViagemItem(
    viagem: Viagem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selecionada: Boolean = false,
) {
    val cores =
        if (selecionada) {
            CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        } else {
            CardDefaults.cardColors()
        }
    val semanticaDeSelecao = if (selecionada) Modifier.semantics { selected = true } else Modifier
    Card(onClick = onClick, modifier = modifier.fillMaxWidth().then(semanticaDeSelecao), colors = cores) {
        Column(modifier = Modifier.padding(espacamentoInterno)) {
            Text(
                text = viagem.destino,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Saída de ${viagem.saida} · ${viagem.periodo}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(espacoAntesDoResumo))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = quantidadeDeDespesas(viagem.despesas.size), style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "R$ ${formatarValorEmReais(viagem.totalEmCentavos)}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

private fun quantidadeDeDespesas(quantidade: Int): String =
    when (quantidade) {
        0 -> "Nenhuma despesa"
        1 -> "1 despesa"
        else -> "$quantidade despesas"
    }
