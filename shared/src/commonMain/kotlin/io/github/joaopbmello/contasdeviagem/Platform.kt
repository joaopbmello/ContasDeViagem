package io.github.joaopbmello.contasdeviagem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform