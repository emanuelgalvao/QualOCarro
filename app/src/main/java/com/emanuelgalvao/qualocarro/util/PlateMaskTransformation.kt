package com.emanuelgalvao.qualocarro.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.toUpperCase

class PlateMaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text.toUpperCase())
    }
}

fun maskFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 7) text.text.substring(0..6) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i==2) out += "-"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset +1
            return 8

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=4) return offset
            if (offset <=8) return offset -1
            return 7
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}