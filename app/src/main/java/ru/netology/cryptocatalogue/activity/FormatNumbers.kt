package ru.netology.cryptocatalogue.activity

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class FormatNumbers {

    val formatSymbols = DecimalFormatSymbols(Locale.US).apply { decimalSeparator = '.' }
    val oneDecimalFormat = DecimalFormat("0.0", formatSymbols).apply {
        roundingMode = RoundingMode.DOWN
    }

    val noDecimalFormat = DecimalFormat("0", formatSymbols).apply {
        roundingMode = RoundingMode.DOWN
    }


    fun bigNumbersFormat(number: Double): String {
        when(number) {
            in 0.0..999.0 -> {
                return oneDecimalFormat.format(number)
            }

            in 1000.0..10_000.0 -> {
                val value = number / 1000.0
                val formatted = oneDecimalFormat.format(value)
                return "$formatted K"
            }

            in 10_000.0..999_999.0 -> {
                val value = number / 1000.0
                val formatted = noDecimalFormat.format(value)
                return "$formatted K"
            }

            in 1_000_000.00..999_999_999.00 -> {
                val value = number / 1_000_000.0
                val formatted = oneDecimalFormat.format(value)
                return "$formatted M"
            }
            else -> {
                val value = number/1_000_000_000.0
                val formatted = oneDecimalFormat.format(value)
                return "$formatted Bn"
            }
        }
    }

}