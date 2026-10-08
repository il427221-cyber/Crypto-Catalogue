package ru.netology.cryptocatalogue.activity

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@SuppressLint("DefaultLocale")
@Composable
fun PercentChangeBadge(value: String?) {
    val change = value?.toDoubleOrNull()

    val color = when {
        change == null -> MaterialTheme.colorScheme.onSurfaceVariant
        change > 0 -> Color(0xFF2E7D32) // зелёный
        change < 0 -> Color(0xFFC62828) // красный
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    val text = when {
        change == null -> "--"
        else -> String.format("%.2f", change)
    }

    Text(
        text = text,
        color = color,
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodySmall
    )
}