package io.github.joaopbmello.contasdeviagem

class Greeting {
    private val platform = getPlatform()

    fun greet(): String = sayHello(platform.name)
}
