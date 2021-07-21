package com.emanuelgalvao.qualocarro.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.emanuelgalvao.qualocarro.R
import kotlinx.android.synthetic.main.dialog_vehicle.*

class InfoDialog(context: Context) : Dialog(context), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.dialog_info)

        image_close.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.image_close) {
            dismiss()
        }
    }
}