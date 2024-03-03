package com.emanuelgalvao.qualocarro.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.databinding.ActivityMainBinding
import com.emanuelgalvao.qualocarro.ui.dialog.InfoDialog
import com.emanuelgalvao.qualocarro.ui.dialog.VehicleDialog
import com.emanuelgalvao.qualocarro.util.MaskUtils
import com.emanuelgalvao.qualocarro.viewmodel.MainViewEvent
import com.emanuelgalvao.qualocarro.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() = viewModel.run {
        event.observe(this@MainActivity) { event ->
            when (event) {
                is MainViewEvent.Error -> {
                    val snackbar = Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(getColor(R.color.background_snackbar))
                    snackbar.show()
                    binding.run {
                        inputBoard.isEnabled = true
                        textSearch.isVisible = true
                        progressSearch.isVisible = false
                    }
                }
                is MainViewEvent.Success -> {
                    binding.inputBoard.text.clear()
                    val bundle = Bundle()
                    bundle.putSerializable("vehicle", event.data)
                    VehicleDialog(this@MainActivity, bundle).show()
                }
            }
        }
    }

    private fun setupListeners() = binding.run {
        inputBoard.addTextChangedListener(MaskUtils().mask(inputBoard))
        textSearch.setOnClickListener {
            handleSearchVehicleClick()
        }
        imageInfo.setOnClickListener {
            handleInfoClick()
        }
    }

    private fun handleSearchVehicleClick() = binding.run {
        inputBoard.isEnabled = false
        textSearch.isVisible = false
        progressSearch.isVisible = true
        viewModel.findVehicle(plate = MaskUtils().unmask(binding.inputBoard.text.trim().toString()))
    }

    private fun handleInfoClick() {
        InfoDialog(this).show()
    }
}