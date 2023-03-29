package com.shahin.weathphotoerapp.ui.weatherLog

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shahin.weathphotoerapp.common.ImageUtils.MyCameraUtility
import com.shahin.weathphotoerapp.databinding.FragmentWeatherLogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class WeatherLogFragment : Fragment() {

    lateinit var binding: FragmentWeatherLogBinding
    private val viewModel: WeatherLogViewModel by viewModels()

    private var mStorageGranted = false
    private lateinit var image: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherLogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            floatingActionButton.setOnClickListener {
                MyCameraUtility.showImageOptionsBottomSheet(requireActivity(), childFragmentManager)


            }
        }

        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.weatherItemsStateFlow.collect { items ->
                if (items.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "No Data yet", Toast.LENGTH_SHORT).show()
                } else {
                    binding.rvLog.adapter = LogAdapter(items.toList()) {
                        findNavController().navigate(
                            WeatherLogFragmentDirections.actionWeatherLogFragmentToSinglePreviewFragment(
                                it
                            )
                        )
                    }
                }
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MyCameraUtility.GALLERY_REQUEST) {
            if (data != null && data.data != null) {
                image = data.data!!.toString()
                goToAddWeather()
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == MyCameraUtility.CAMERA_REQUEST) {
            if (!MyCameraUtility.currentPhotoPath.equals("")) {
                image = Uri.fromFile(File(MyCameraUtility.currentPhotoPath)).toString()
                goToAddWeather()
            } else {
                Toast.makeText(context, "bad  path", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToAddWeather() {
        findNavController().navigate(
            WeatherLogFragmentDirections.actionWeatherLogFragmentToAddNewLogItemFragment(
                image
            )
        )
    }

}