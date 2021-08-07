package com.example.weatherapp.ui.weather

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.search.SearchDialog
import com.example.weatherapp.utils.GpsStatus
import com.example.weatherapp.utils.GpsListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    lateinit var searchDialog: SearchDialog
    private val disposable = CompositeDisposable()
    private val viewModel: WeatherVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        searchDialog = SearchDialog(
            { it ->
                viewModel.byCityName(it, disposable)
            })
        {   //onSearchClickShowTextView
            binding.tvDaysTitle.visibility = View.VISIBLE
            binding.tvHourTitle.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWeatherBinding.inflate(inflater)
        binding.tvDaysTitle.visibility = View.GONE
        binding.tvHourTitle.visibility = View.GONE
        binding.locationList.visibility = View.GONE

        binding.apply {
            locationList.adapter = viewModel.byLocationAdapter
            hourList.adapter = viewModel.hourAdapter
            hourList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            daysList.adapter = viewModel.dayAdapter
            daysList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_refresh) {
            subscribeToGpsListener()
            locationRequestOnClick()
        }
        if (id == R.id.action_search) {
            searchDialog.show(requireActivity().supportFragmentManager, searchDialog.tag)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeToGpsListener() {
        GpsListener(requireActivity().applicationContext).observe(viewLifecycleOwner) { status ->
            when (status) {
                is GpsStatus.Disabled -> {
                    binding.locationList.visibility = View.GONE
                    binding.gpsStatusTextView.visibility = View.VISIBLE
                    binding.gpsStatusTextView.apply {
                        text = getString(status.message)
                        setTextColor(Color.RED)
                    }
                }
                is GpsStatus.Enabled -> {
                    binding.gpsStatusTextView.visibility = View.GONE
                    binding.locationList.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun locationRequestOnClick() {
        (activity as MainActivity).requestCurrentLocation({ location ->
            viewModel.byLocation(location, disposable)
        },
            {
                Toast.makeText(requireActivity(), "Turn on location", Toast.LENGTH_LONG).show();
            })
    }


    override fun onStop() {
        disposable.clear()
        super.onStop()
    }
}
