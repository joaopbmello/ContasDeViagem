package io.github.joaopbmello.contasdeviagem

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ContasDeViagem",
    ) {
        App()
    }
}