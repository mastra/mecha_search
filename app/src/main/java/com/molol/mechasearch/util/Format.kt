package com.molol.mechasearch.util

import java.text.NumberFormat
import java.util.*

fun Int.toPrice(currencyCode: String = "ARS"): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0

    format.currency = Currency.getInstance(currencyCode)

    return format.format(this).replace(format.currency.symbol, "")
}