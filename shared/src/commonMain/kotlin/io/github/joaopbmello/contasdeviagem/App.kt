package io.github.joaopbmello.contasdeviagem

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.window.core.layout.WindowSizeClass
import io.github.joaopbmello.contasdeviagem.presentation.navegacao.GrafoDoApp
import io.github.joaopbmello.contasdeviagem.presentation.tema.ContasDeViagemTema
import io.github.joaopbmello.contasdeviagem.presentation.viagensDeExemplo

@Composable
fun App() {
    ContasDeViagemTema {
        var viagens by remember { mutableStateOf(viagensDeExemplo) }
        var selecionada by rememberSaveable { mutableStateOf<Int?>(null) }
        val largo =
            currentWindowAdaptiveInfo()
                .windowSizeClass
                .isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
        GrafoDoApp(
            viagens = viagens,
            onViagensChange = { viagens = it },
            selecionada = selecionada,
            onSelecionar = { selecionada = it },
            largo = largo,
        )
    }
}
