package io.github.joaopbmello.contasdeviagem.domain

import kotlin.math.absoluteValue

private const val CENTAVOS_POR_REAL = 100
private const val CASAS_DECIMAIS = 2
private const val DIGITOS_POR_GRUPO = 3

fun formatarValorEmReais(centavos: Long): String {
    val sinal = if (centavos < 0) "-" else ""
    val absoluto = centavos.absoluteValue
    val reais = absoluto / CENTAVOS_POR_REAL
    val resto = absoluto % CENTAVOS_POR_REAL

    val reaisComSeparador =
        reais
            .toString()
            .reversed()
            .chunked(DIGITOS_POR_GRUPO)
            .joinToString(".")
            .reversed()

    return "$sinal$reaisComSeparador,${resto.toString().padStart(CASAS_DECIMAIS, '0')}"
}