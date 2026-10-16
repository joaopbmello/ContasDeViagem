package io.github.joaopbmello.contasdeviagem.domain

private val formatoData = Regex("""\d{2}/\d{2}""")
private val formatoValor = Regex("""\d{1,7}(,\d{1,2})?""")
private const val ULTIMO_DIA = 31
private const val ULTIMO_MES = 12
private const val CENTAVOS_POR_REAL = 100L
private const val CASAS_DECIMAIS = 2

fun dataValida(texto: String): Boolean {
    if (!formatoData.matches(texto)) return false
    val (dia, mes) = texto.split("/").map { it.toInt() }
    return dia in 1..ULTIMO_DIA && mes in 1..ULTIMO_MES
}

fun valorEmCentavosOuNulo(texto: String): Long? {
    val limpo = texto.trim().replace('.', ',')
    if (!formatoValor.matches(limpo)) return null
    val reais = limpo.substringBefore(",").toLong()
    val centavos = limpo.substringAfter(",", missingDelimiterValue = "").padEnd(CASAS_DECIMAIS, '0').toLong()
    return (reais * CENTAVOS_POR_REAL + centavos).takeIf { it > 0 }
}
