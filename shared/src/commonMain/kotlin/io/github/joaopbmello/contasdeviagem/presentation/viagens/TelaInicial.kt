package io.github.joaopbmello.contasdeviagem.presentation.viagens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.joaopbmello.contasdeviagem.domain.Viagem
import io.github.joaopbmello.contasdeviagem.presentation.componentes.EstadoVazio
import io.github.joaopbmello.contasdeviagem.presentation.viagem.ViagemScreen

private const val PESO_DA_LISTA = 1f
private const val PESO_DO_DETALHE = 1.5f

/**
 * Início do app, adaptado à largura da janela.
 *
 * Janela compacta (celular em pé): só a lista, e tocar numa viagem abre outra tela.
 * Janela larga (celular deitado, tablet, desktop): lista e viagem aberta lado a lado.
 * A decisão de layout fica separada do conteúdo, que é o mesmo nas duas larguras.
 */
@Composable
fun TelaInicial(
    largo: Boolean,
    viagens: List<Viagem>,
    selecionada: Int?,
    onAbrirViagem: (Int) -> Unit,
    onNovaViagem: () -> Unit,
    onLancarDespesa: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (largo) {
        Row(modifier = modifier.fillMaxSize()) {
            ViagensScreen(
                viagens = viagens,
                onAbrirViagem = onAbrirViagem,
                onNovaViagem = onNovaViagem,
                selecionada = selecionada,
                modifier = Modifier.weight(PESO_DA_LISTA),
            )
            VerticalDivider()
            val viagem = viagens.find { it.id == selecionada }
            if (viagem == null) {
                EstadoVazio(
                    titulo = "Nenhuma viagem aberta",
                    mensagem = "Escolha uma viagem na lista para ver as despesas.",
                    modifier = Modifier.weight(PESO_DO_DETALHE),
                )
            } else {
                ViagemScreen(
                    viagem = viagem,
                    onLancarDespesa = { onLancarDespesa(viagem.id) },
                    modifier = Modifier.weight(PESO_DO_DETALHE),
                )
            }
        }
    } else {
        ViagensScreen(
            viagens = viagens,
            onAbrirViagem = onAbrirViagem,
            onNovaViagem = onNovaViagem,
            modifier = modifier,
        )
    }
}
