package com.mycompany.weather.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mycompany.weather.R
import com.mycompany.weather.databinding.ActivityMainBinding
import com.mycompany.weather.vm.MyViewModel
import com.mycompany.weather.vm.MyViewModelFactory
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels {
        MyViewModelFactory(
            applicationContext
        )
    }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initSpinner()
    }

    private fun initSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val strings = viewModel.getArrayCities().map { it.name }
        val arrayAdapter = ArrayAdapter(this, R.layout.spinner_item, strings)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_drop)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                pos: Int, id: Long
            ) {
                viewModel.changeCity(parent?.getItemAtPosition(pos).toString())
                binding.viewModel = viewModel
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }


    fun updateSpinner(view: View) {
        thread {
            viewModel.loadCityFromDatabase()
            runOnUiThread {
                initSpinner()
            }
        }
    }

    fun requestGet(view: View) {
        viewModel.requestGet()
    }
}

