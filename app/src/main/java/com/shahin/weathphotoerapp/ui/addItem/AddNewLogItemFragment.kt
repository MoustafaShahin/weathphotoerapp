package com.shahin.weathphotoerapp.ui.addItem

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shahin.weathphotoerapp.common.checkGps
import com.shahin.weathphotoerapp.common.checkLocationPermissions
import com.shahin.weathphotoerapp.common.resource.Resource
import com.shahin.weathphotoerapp.databinding.FragmentAddNewLogItemBinding
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import dagger.hilt.android.AndroidEntryPoint

const val LOCATION_PERMISSION_REQUEST_CODE = 202
const val LOCATION_FINE_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
const val LOCATION_COARSE_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION

@AndroidEntryPoint

class AddNewLogItemFragment : Fragment() {

    lateinit var binding: FragmentAddNewLogItemBinding
    private val viewModel: AddNewLogItemViewModel by viewModels()

    private val args: AddNewLogItemFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNewLogItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!loadedCurrentLocation() && hasLocationPermissions()) {
            viewModel.getCurrentLocation()
        }


        binding.iv.setImageURI(args.image.toUri())
        binding.btnSave.setOnClickListener {
            if (binding.tvtCity.text.isNullOrEmpty()) {
                binding.tvtCity.error = "Required"
                return@setOnClickListener
            }
            if (binding.tvCondition.text.isNullOrEmpty()) {
                binding.tvCondition.error = "Required"
                return@setOnClickListener
            }
            if (binding.tvtTemp.text.isNullOrEmpty()) {
                binding.tvtTemp.error = "Required"
                return@setOnClickListener
            }

            viewModel.saveItemToLog(
                WeatherItem(
                    cityName = binding.tvtCity.text.toString(),
                    temp = binding.tvtTemp.text.toString().toDouble(),
                    photoPath = args.image,
                    description = binding.tvCondition.text.toString()
                )
            )
        }
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launchWhenResumed {
            viewModel.currentWeatherDataStateFlow.collect { state ->
                when (state) {

                    is Resource.Loading -> {
                        binding.btnSave.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.SUCCESS -> {
                        binding.btnSave.visibility = View.VISIBLE

                        binding.progressBar.visibility = View.GONE
                        setupData(state.data)
                    }
                    is Resource.ERROR -> {
                        binding.btnSave.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            state.error?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {}
                }
            }

        }
        lifecycleScope.launchWhenResumed {
            viewModel.saveState.collect { saved ->
                if (saved) {
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun setupData(data: WeatherItem?) {
        binding.apply {
            tvCondition.text = data?.description
            tvtCity.text = data?.cityName
            tvtTemp.text = data?.temp.toString()
        }
    }


    private fun loadedCurrentLocation(): Boolean {
        return viewModel.currentLocation.value != null
    }

    private fun hasLocationPermissions(): Boolean {
        return requireActivity().checkGps() && requireActivity().checkLocationPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permissionDenied = true
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            for (permission in grantResults) {
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    permissionDenied = false
                    break
                }
            }
        }

        if (!permissionDenied) {
            viewModel.getCurrentLocation()
        }
    }
}