package com.emanuelgalvao.qualocarro.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MaskUtils {

    val BOARD_MASK = "###-####"

    fun mask(ediText: EditText): TextWatcher? {

        return object : TextWatcher {

            var isUpdating = false
            var old = ""

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val str: String = unmask(s.toString())

                var mask = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in BOARD_MASK.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        mask += m
                        continue
                    }
                    mask += try {
                        str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }

                isUpdating = true
                ediText.setText(mask.uppercase())
                ediText.setSelection(mask.length)
            }
        }
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "")
            .replace("[:]".toRegex(), "").replace("[)]".toRegex(), "")
    }
}