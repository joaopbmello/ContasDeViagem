package io.github.joaopbmello.contasdeviagem.presentation.navegacao

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import io.github.joaopbmello.contasdeviagem.domain.Viagem
import io.github.joaopbmello.contasdeviagem.domain.comDespesa
import io.github.joaopbmello.contasdeviagem.domain.comNovaViagem
import io.github.joaopbmello.contasdeviagem.domain.proximoId
import io.github.joaopbmello.contasdeviagem.presentation.FormularioDespesa
import io.github.joaopbmello.contasdeviagem.presentation.FormularioViagem
import io.github.joaopbmello.contasdeviagem.presentation.viagem.NovaDespesaScreen
import io.github.joaopbmello.contasdeviagem.presentation.viagem.ViagemScreen
import io.github.joaopbmello.contasdeviagem.presentation.viagens.NovaViagemScreen
import io.github.joaopbmello.contasdeviagem.presentation.viagens.TelaInicial

@Composable
fun GrafoDoApp(
    viagens: List<Viagem>,
    onViagensChange: (List<Viagem>) -> Unit,
    selecionada: Int?,
    onSelecionar: (Int) -> Unit,
    largo: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Viagens, modifier = modifier) {
        composable<Viagens> {
            TelaInicial(
                largo = largo,
                viagens = viagens,
                selecionada = selecionada,
                onAbrirViagem = { id -> if (largo) onSelecionar(id) else navController.navigate(ViagemDetalhe(id)) },
                onNovaViagem = { navController.navigate(NovaViagem) },
                onLancarDespesa = { id -> navController.navigate(NovaDespesa(id)) },
            )
        }
        destinoDaViagem(navController, viagens)
        destinoNovaViagem(navController, viagens, onViagensChange, onSelecionar)
        destinoNovaDespesa(navController, viagens, onViagensChange)
    }
}

private fun NavGraphBuilder.destinoDaViagem(
    navController: NavHostController,
    viagens: List<Viagem>,
) {
    composable<ViagemDetalhe>(deepLinks = listOf(navDeepLink<ViagemDetalhe>(basePath = BASE_DO_DEEP_LINK))) { entrada ->
        val id = entrada.toRoute<ViagemDetalhe>().id
        ViagemScreen(
            viagem = viagens.find { it.id == id },
            onLancarDespesa = { navController.navigate(NovaDespesa(id)) },
            onVoltar = { navController.navigateUp() },
        )
    }
}

private fun NavGraphBuilder.destinoNovaViagem(
    navController: NavHostController,
    viagens: List<Viagem>,
    onViagensChange: (List<Viagem>) -> Unit,
    onSelecionar: (Int) -> Unit,
) {
    composable<NovaViagem> {
        var formulario by remember { mutableStateOf(FormularioViagem()) }
        NovaViagemScreen(
            formulario = formulario,
            onFormularioChange = { formulario = it },
            onSalvar = {
                val nova = formulario.paraViagem(viagens.proximoId())
                onViagensChange(viagens.comNovaViagem(nova))
                onSelecionar(nova.id)
                navController.navigateUp()
            },
            onVoltar = { navController.navigateUp() },
        )
    }
}

private fun NavGraphBuilder.destinoNovaDespesa(
    navController: NavHostController,
    viagens: List<Viagem>,
    onViagensChange: (List<Viagem>) -> Unit,
) {
    composable<NovaDespesa> { entrada ->
        val viagemId = entrada.toRoute<NovaDespesa>().viagemId
        val viagem = viagens.find { it.id == viagemId }
        var formulario by remember { mutableStateOf(FormularioDespesa()) }
        NovaDespesaScreen(
            nomeDaViagem = viagem?.destino.orEmpty(),
            formulario = formulario,
            onFormularioChange = { formulario = it },
            onSalvar = {
                val idDaDespesa = "$viagemId-${(viagem?.despesas?.size ?: 0) + 1}"
                formulario.paraDespesa(idDaDespesa)?.let { despesa ->
                    onViagensChange(viagens.comDespesa(viagemId, despesa))
                }
                navController.navigateUp()
            },
            onVoltar = { navController.navigateUp() },
        )
    }
}
