package io.github.joaopbmello.contasdeviagem.presentation.viagem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import io.github.joaopbmello.contasdeviagem.domain.Viagem
import io.github.joaopbmello.contasdeviagem.domain.formatarValorEmReais
import io.github.joaopbmello.contasdeviagem.presentation.componentes.BarraDeTopo
import io.github.joaopbmello.contasdeviagem.presentation.componentes.EstadoVazio

private val espacoDaLista = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 96.dp)
private val espacoEntreItens = 8.dp
private val espacamentoDoTotal = 16.dp

@Composable
fun ViagemScreen(
    viagem: Viagem?,
    onLancarDespesa: () -> Unit,
    modifier: Modifier = Modifier,
    onVoltar: (() -> Unit)? = null,
) {
    if (viagem == null) {
        Scaffold(
            modifier = modifier,
            topBar = { BarraDeTopo(titulo = "Viagem não encontrada", onVoltar = onVoltar) },
        ) { espacoDoScaffold ->
            EstadoVazio(
                titulo = "Esta viagem não existe",
                mensagem = "Confira o endereço ou volte para a lista de viagens.",
                modifier = Modifier.padding(espacoDoScaffold),
            )
        }
    } else {
        DetalheDaViagem(viagem = viagem, onLancarDespesa = onLancarDespesa, onVoltar = onVoltar, modifier = modifier)
    }
}

@Composable
private fun DetalheDaViagem(
    viagem: Viagem,
    onLancarDespesa: () -> Unit,
    onVoltar: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BarraDeTopo(
                titulo = viagem.destino,
                subtitulo = "Saída de ${viagem.saida} · ${viagem.periodo}",
                onVoltar = onVoltar,
            )
        },
        bottomBar = { TotalDaViagem(totalEmCentavos = viagem.totalEmCentavos) },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onLancarDespesa) { Text(text = "Lançar despesa") }
        },
    ) { espacoDoScaffold ->
        if (viagem.despesas.isEmpty()) {
            EstadoVazio(
                titulo = "Nenhuma despesa lançada",
                mensagem = "Lance o primeiro gasto da viagem e o total é somado sozinho.",
                modifier = Modifier.padding(espacoDoScaffold),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(espacoDoScaffold),
                contentPadding = espacoDaLista,
                verticalArrangement = Arrangement.spacedBy(espacoEntreItens),
            ) {
                items(items = viagem.despesas, key = { it.id }) { despesa -> DespesaItem(despesa = despesa) }
            }
        }
    }
}

@Composable
private fun TotalDaViagem(
    totalEmCentavos: Long,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surfaceContainerHigh) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(espacamentoDoTotal)
                    .semantics(mergeDescendants = true) {},
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Total das despesas", style = MaterialTheme.typography.titleMedium)
            Text(text = "R$ ${formatarValorEmReais(totalEmCentavos)}", style = MaterialTheme.typography.titleMedium)
        }
    }
}
