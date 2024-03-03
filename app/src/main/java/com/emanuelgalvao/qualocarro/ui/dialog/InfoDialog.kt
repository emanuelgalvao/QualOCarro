package com.emanuelgalvao.qualocarro.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.emanuelgalvao.qualocarro.databinding.DialogInfoBinding

class InfoDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DialogInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imageClose.setOnClickListener {
            dismiss()
        }
    }
}