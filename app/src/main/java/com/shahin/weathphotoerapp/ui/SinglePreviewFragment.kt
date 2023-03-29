package com.shahin.weathphotoerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.shahin.weathphotoerapp.common.ShareUtils
import com.shahin.weathphotoerapp.databinding.FragmentSinglePreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SinglePreviewFragment : Fragment() {

    lateinit var binding: FragmentSinglePreviewBinding
    val args: SinglePreviewFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSinglePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        binding.ivShare.setOnClickListener {
            ShareUtils.shareTextImage(binding.clShareable, requireContext())
        }
    }

    private fun bindData() {
        binding.apply {
            iv.setImageURI(args.weatherItem.photoPath?.toUri())
            tvtCity.text = args.weatherItem?.cityName
            tvCondition.text = args.weatherItem?.description
            tvtTemp.text = args.weatherItem?.temp.toString()
            iv.setImageURI(args.weatherItem?.photoPath?.toUri())
        }
    }

}