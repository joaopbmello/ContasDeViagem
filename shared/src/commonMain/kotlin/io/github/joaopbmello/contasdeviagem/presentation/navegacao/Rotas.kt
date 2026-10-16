package io.github.joaopbmello.contasdeviagem.presentation.navegacao

import kotlinx.serialization.Serializable

@Serializable
object Viagens

@Serializable
object NovaViagem

@Serializable
data class ViagemDetalhe(
    val id: Int,
)

@Serializable
data class NovaDespesa(
    val viagemId: Int,
)

const val BASE_DO_DEEP_LINK = "contasdeviagem://viagem"
