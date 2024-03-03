package com.emanuelgalvao.qualocarro.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.databinding.ActivityMainBinding
import com.emanuelgalvao.qualocarro.ui.dialog.InfoDialog
import com.emanuelgalvao.qualocarro.ui.dialog.VehicleDialog
import com.emanuelgalvao.qualocarro.util.MaskUtils
import com.emanuelgalvao.qualocarro.viewmodel.MainViewEvent
import com.emanuelgalvao.qualocarro.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is MainViewEvent.Error -> {
                    val snackbar = Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(getColor(R.color.background_snackbar))
                    snackbar.show()
                    binding.inputBoard.isEnabled = true
                    binding.textSearch.isVisible = true
                    binding.progressSearch.isVisible = false
                }
                is MainViewEvent.Success -> {
                    binding.inputBoard.text.clear()
                    val bundle = Bundle()
                    bundle.putSerializable("vehicle", event.data)
                    val vehicleDialog = VehicleDialog(this, bundle)
                    vehicleDialog.show()
                }
            }
        }
    }

    private fun setupListeners() = binding.run {
        binding.inputBoard.addTextChangedListener(MaskUtils().mask(binding.inputBoard))
        binding.textSearch.setOnClickListener {
            handleSearchVehicleClick()
        }
        binding.imageInfo.setOnClickListener {
            handleInfoClick()
        }
    }

    private fun handleSearchVehicleClick() {
        binding.inputBoard.isEnabled = false
        binding.textSearch.isVisible = false
        binding.progressSearch.isVisible = true
        val board = binding.inputBoard.text.trim().toString()
        viewModel.findVehicle(board)
    }

    private fun handleInfoClick() {
        val infoDialog = InfoDialog(this)
        infoDialog.show()
    }
}