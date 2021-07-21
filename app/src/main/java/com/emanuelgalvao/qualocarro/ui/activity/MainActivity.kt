package com.emanuelgalvao.qualocarro.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.ui.dialog.InfoDialog
import com.emanuelgalvao.qualocarro.ui.dialog.VehicleDialog
import com.emanuelgalvao.qualocarro.util.MaskUtils
import com.emanuelgalvao.qualocarro.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observers()

        input_board.addTextChangedListener(MaskUtils().mask(input_board))
        text_search.setOnClickListener(this)
        image_info.setOnClickListener(this)
    }

    private fun observers() {
        mViewModel.validation.observe(this, {
            if (!it.isSucess()) {
                val snackbar = Snackbar.make(root, it.getMessage(), Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(getColor(R.color.background_snackbar))
                snackbar.show()
            }
            input_board.isEnabled = true
            text_search.isVisible = true
            progress_search.isVisible = false
        })

        mViewModel.vehicle.observe(this, {

            input_board.text.clear()
            val bundle = Bundle()
            bundle.putSerializable("vehicle", it)
            val vehicleDialog = VehicleDialog(this, bundle)
            vehicleDialog.show()
        })
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.text_search) {

            input_board.isEnabled = false
            text_search.isVisible = false
            progress_search.isVisible = true
            val board = MaskUtils().unmask(input_board.text.trim().toString())
            mViewModel.findVehicle(board)

        } else if (v?.id == R.id.image_info) {

            val infoDialog = InfoDialog(this)
            infoDialog.show()

        }
    }
}