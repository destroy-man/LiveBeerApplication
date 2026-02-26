package ru.korobeynikov.livebeerapplication.presentation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        for (i in text.text.indices) {
            if (i == 3) out += "("
            if (i == 6) out += ") "
            if (i == 9 || i == 11) out += " "
            out += text.text[i]
        }
        return TransformedText(AnnotatedString(out), phoneNumberOffsetTranslator)
    }

    private val phoneNumberOffsetTranslator = object : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int =
            when (offset) {
                in 0..3 -> 3
                in 4..6 -> offset + 1
                in 7..9 -> offset + 3
                in 10..11 -> offset + 4
                else -> offset + 5
            }

        override fun transformedToOriginal(offset: Int): Int =
            when (offset) {
                in 0..3 -> 3
                in 4..8 -> offset - 1
                in 9..13 -> offset - 4
                else -> offset - 7
            }
    }
}