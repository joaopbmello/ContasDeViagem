package io.github.joaopbmello.contasdeviagem.domain

data class Viagem(
    val id: Int,
    val destino: String,
    val saida: String,
    val inicio: String,
    val fim: String,
    val despesas: List<Despesa> = emptyList(),
) {
    val totalEmCentavos: Long get() = despesas.sumOf { it.valorEmCentavos }

    val periodo: String get() = "$inicio a $fim"
}

fun List<Viagem>.proximoId(): Int = (maxOfOrNull { it.id } ?: 0) + 1

fun List<Viagem>.comNovaViagem(viagem: Viagem): List<Viagem> = listOf(viagem) + this

fun List<Viagem>.comDespesa(
    viagemId: Int,
    despesa: Despesa,
): List<Viagem> = map { if (it.id == viagemId) it.copy(despesas = it.despesas + despesa) else it }
