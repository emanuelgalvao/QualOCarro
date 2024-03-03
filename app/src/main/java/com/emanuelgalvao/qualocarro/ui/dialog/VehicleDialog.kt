package com.emanuelgalvao.qualocarro.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.databinding.DialogVehicleBinding
import com.emanuelgalvao.qualocarro.model.Vehicle

class VehicleDialog(context: Context, private var bundle: Bundle) : Dialog(context), View.OnClickListener {

    private lateinit var vehicle: Vehicle
    private lateinit var binding: DialogVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DialogVehicleBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val seriazable = bundle.getSerializable("vehicle")
        vehicle = seriazable as Vehicle

        fillFields()

        binding.imageClose.setOnClickListener(this)
    }

    private fun fillFields() {

        binding.textBrandValue.text = vehicle.marca
        binding.textYearValue.text = "${vehicle.ano}/${vehicle.anoModelo}"
        binding.textColorValue.text = vehicle.cor
        binding.textChassiValue.text = vehicle.chassi
        binding.textBoardValue.text = vehicle.placa
        binding.textCityValue.text = "${vehicle.municipio}/${vehicle.uf}"
        binding.textSituationValue.text = vehicle.situacao
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.image_close) {
            dismiss()
        }
    }
}