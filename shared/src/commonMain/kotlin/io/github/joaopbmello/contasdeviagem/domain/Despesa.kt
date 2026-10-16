package io.github.joaopbmello.contasdeviagem.domain

enum class TipoComprovante(
    val rotulo: String,
    val nome: String,
) {
    NOTA_FISCAL("NF", "Nota fiscal"),
    RECIBO("Recibo", "Recibo"),
}

data class Despesa(
    val id: String,
    val data: String,
    val cidade: String,
    val descricao: String,
    val tipoComprovante: TipoComprovante,
    val valorEmCentavos: Long,
)
