package com.emanuelgalvao.qualocarro.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.model.Vehicle
import kotlinx.android.synthetic.main.dialog_vehicle.*

class VehicleDialog(context: Context, private var bundle: Bundle) : Dialog(context), View.OnClickListener {

    private lateinit var vehicle: Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.dialog_vehicle)

        val seriazable = bundle.getSerializable("vehicle")
        vehicle = seriazable as Vehicle

        fillFields()

        image_close.setOnClickListener(this)
    }

    private fun fillFields() {

        text_brand_value.text = vehicle.marca
        text_year_value.text = "${vehicle.ano}/${vehicle.anoModelo}"
        text_color_value.text = vehicle.cor
        text_chassi_value.text = vehicle.chassi
        text_board_value.text = vehicle.placa
        text_city_value.text = "${vehicle.municipio}/${vehicle.uf}"
        text_situation_value.text = vehicle.situacao
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.image_close) {
            dismiss()
        }
    }
}