package io.github.joaopbmello.contasdeviagem.presentation.viagens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.joaopbmello.contasdeviagem.domain.Viagem
import io.github.joaopbmello.contasdeviagem.presentation.componentes.BarraDeTopo
import io.github.joaopbmello.contasdeviagem.presentation.componentes.EstadoVazio

private val espacoDaLista = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 96.dp)
private val espacoEntreItens = 8.dp

/**
 * Lista de viagens, que é o início do app.
 *
 * Não guarda estado: recebe as viagens e devolve os eventos. [selecionada] destaca a viagem
 * aberta ao lado quando a janela é larga.
 */
@Composable
fun ViagensScreen(
    viagens: List<Viagem>,
    onAbrirViagem: (Int) -> Unit,
    onNovaViagem: () -> Unit,
    modifier: Modifier = Modifier,
    selecionada: Int? = null,
) {
    Scaffold(
        modifier = modifier,
        topBar = { BarraDeTopo(titulo = "Minhas viagens") },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onNovaViagem) { Text(text = "Nova viagem") }
        },
    ) { espacoDoScaffold ->
        if (viagens.isEmpty()) {
            EstadoVazio(
                titulo = "Nenhuma viagem cadastrada",
                mensagem = "Cadastre a próxima viagem para começar a lançar as despesas.",
                modifier = Modifier.padding(espacoDoScaffold),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(espacoDoScaffold),
                contentPadding = espacoDaLista,
                verticalArrangement = Arrangement.spacedBy(espacoEntreItens),
            ) {
                items(items = viagens, key = { it.id }) { viagem ->
                    ViagemItem(
                        viagem = viagem,
                        onClick = { onAbrirViagem(viagem.id) },
                        selecionada = viagem.id == selecionada,
                    )
                }
            }
        }
    }
}
