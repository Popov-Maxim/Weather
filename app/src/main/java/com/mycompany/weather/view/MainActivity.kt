package com.mycompany.weather.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mycompany.weather.R
import com.mycompany.weather.databinding.ActivityMainBinding
import com.mycompany.weather.model.City
import com.mycompany.weather.vm.MyViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveCities()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.setModel(loadCities())


        initSpinner()
    }

    private fun initSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        var strings: Array<String>

        strings = viewModel.getArrayCities().let {
            Array(it?.size ?: 0) { i -> it!![i].name }
        }
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


    private fun loadCities(): Array<City> {
        val sPref = getPreferences(MODE_PRIVATE)
        val size = sPref.getInt("_size", 0)
        return Array(size) { i ->
            sPref.getString(City::name.name + i, "name")?.let {
                City(
                    it,
                    sPref.getFloat(City::lat.name + i, 0F).toDouble(),
                    sPref.getFloat(City::lon.name + i, 0F).toDouble()
                )
            }!!
        }

    }

    private fun saveCities() {
        val sPref = getPreferences(MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPref.edit()
        ed.putInt("_size", 3);

        ed.putString(City::name.name + 0, "Минск")
        ed.putFloat(City::lat.name + 0, 53.902287F)
        ed.putFloat(City::lon.name + 0, 27.561824F)

        ed.putString(City::name.name + 1, "Гродно")
        ed.putFloat(City::lat.name + 1, 53.677764F)
        ed.putFloat(City::lon.name + 1, 23.829300F)

        ed.putString(City::name.name + 2, "Витебск")
        ed.putFloat(City::lat.name + 2, 55.184204F)
        ed.putFloat(City::lon.name + 2, 30.202767F)

        ed.apply()
    }
}

