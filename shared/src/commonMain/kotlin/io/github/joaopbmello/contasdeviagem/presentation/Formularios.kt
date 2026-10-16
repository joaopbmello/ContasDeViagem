package io.github.joaopbmello.contasdeviagem.presentation

import io.github.joaopbmello.contasdeviagem.domain.Despesa
import io.github.joaopbmello.contasdeviagem.domain.TipoComprovante
import io.github.joaopbmello.contasdeviagem.domain.Viagem
import io.github.joaopbmello.contasdeviagem.domain.dataValida
import io.github.joaopbmello.contasdeviagem.domain.valorEmCentavosOuNulo

private const val DIGITOS_DA_DATA = 4
private const val POSICAO_DA_BARRA = 2

internal fun comBarra(digitos: String): String =
    if (digitos.length > POSICAO_DA_BARRA) {
        "${digitos.take(POSICAO_DA_BARRA)}/${digitos.drop(POSICAO_DA_BARRA)}"
    } else {
        digitos
    }

internal fun erroDeData(digitos: String): String? =
    if (digitos.length == DIGITOS_DA_DATA && !dataValida(comBarra(digitos))) {
        "Data inválida: confira o dia e o mês"
    } else {
        null
    }

data class FormularioViagem(
    val destino: String = "",
    val saida: String = "Natal/RN",
    val inicio: String = "",
    val fim: String = "",
) {
    val erroInicio: String? get() = erroDeData(inicio)

    val erroFim: String? get() = erroDeData(fim)

    val valido: Boolean get() = destino.isNotBlank() && saida.isNotBlank() && datasValidas

    private val datasValidas: Boolean get() = dataValida(comBarra(inicio)) && dataValida(comBarra(fim))

    fun paraViagem(id: Int): Viagem =
        Viagem(
            id = id,
            destino = destino.trim(),
            saida = saida.trim(),
            inicio = comBarra(inicio),
            fim = comBarra(fim),
        )
}

data class FormularioDespesa(
    val descricao: String = "",
    val data: String = "",
    val cidade: String = "",
    val valor: String = "",
    val tipoComprovante: TipoComprovante = TipoComprovante.NOTA_FISCAL,
) {
    val erroData: String? get() = erroDeData(data)

    val erroValor: String?
        get() =
            if (valor.isNotEmpty() && valorEmCentavosOuNulo(valor) == null) {
                "Valor inválido: use números, como 39,90"
            } else {
                null
            }

    val valido: Boolean get() = textosPreenchidos && dataValida(comBarra(data)) && valorEmCentavosOuNulo(valor) != null

    private val textosPreenchidos: Boolean get() = descricao.isNotBlank() && cidade.isNotBlank()

    fun paraDespesa(id: String): Despesa? {
        val centavos = valorEmCentavosOuNulo(valor)
        return if (valido && centavos != null) {
            Despesa(
                id = id,
                data = comBarra(data),
                cidade = cidade.trim(),
                descricao = descricao.trim(),
                tipoComprovante = tipoComprovante,
                valorEmCentavos = centavos,
            )
        } else {
            null
        }
    }
}
