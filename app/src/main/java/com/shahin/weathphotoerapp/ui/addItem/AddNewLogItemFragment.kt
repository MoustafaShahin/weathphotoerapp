package com.shahin.weathphotoerapp.ui.addItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.shahin.weathphotoerapp.databinding.FragmentAddNewLogItemBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class AddNewLogItemFragment : Fragment() {

    lateinit var binding:FragmentAddNewLogItemBinding
    private val args : AddNewLogItemFragmentArgs by navArgs()
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
/*        if (!loadedCurrentLocation() && hasLocationPermissions()) {
            viewModel.fetchCurrentLocation()
        }*/
        binding.iv.setImageURI(args.image.toUri())
    }

}