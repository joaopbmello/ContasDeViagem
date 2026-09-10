package io.github.joaopbmello.contasdeviagem.presentation.viagem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.joaopbmello.contasdeviagem.domain.Despesa
import io.github.joaopbmello.contasdeviagem.domain.formatarValorEmReais

private val EspacamentoDaLista = 16.dp
private val EspacoEntreItens = 8.dp
private val EspacamentoDoTotal = 16.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViagemScreen(
    titulo: String,
    periodo: String,
    despesas: List<Despesa>,
    onDespesaClick: (Despesa) -> Unit,
    onLancarDespesa: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = titulo)
                        Text(
                            text = periodo,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                },
            )
        },
        bottomBar = {
            TotalDaViagem(totalEmCentavos = despesas.sumOf { it.valorEmCentavos })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onLancarDespesa) {
                Text(text = "Lançar despesa")
            }
        },
    ) { espacoDoScaffold ->
        if (despesas.isEmpty()) {
            ViagemSemDespesas(modifier = Modifier.fillMaxSize().padding(espacoDoScaffold))
        } else {
            ListaDeDespesas(
                despesas = despesas,
                onDespesaClick = onDespesaClick,
                espacoDoScaffold = espacoDoScaffold,
            )
        }
    }
}

@Composable
private fun ListaDeDespesas(
    despesas: List<Despesa>,
    onDespesaClick: (Despesa) -> Unit,
    espacoDoScaffold: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = espacoDoScaffold,
        verticalArrangement = Arrangement.spacedBy(EspacoEntreItens),
    ) {
        items(items = despesas, key = { it.id }) { despesa ->
            DespesaItem(
                despesa = despesa,
                onClick = { onDespesaClick(despesa) },
                modifier = Modifier.padding(horizontal = EspacamentoDaLista),
            )
        }
    }
}

@Composable
private fun ViagemSemDespesas(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(EspacamentoDaLista),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Nenhuma despesa lançada nesta viagem",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Lance o primeiro gasto e o total é somado sozinho.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun TotalDaViagem(
    totalEmCentavos: Long,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(EspacamentoDoTotal),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Total das despesas",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = formatarValorEmReais(totalEmCentavos),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}