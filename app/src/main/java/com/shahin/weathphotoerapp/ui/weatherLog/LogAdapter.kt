package com.shahin.weathphotoerapp.ui.weatherLog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.shahin.weathphotoerapp.databinding.ItemLogBinding
import com.shahin.weathphotoerapp.domain.model.WeatherItem

class LogAdapter(
    private val data: List<WeatherItem?>,
    private val onItemClicked: (WeatherItem) -> Unit
) : RecyclerView.Adapter<LogAdapter.LogItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LogItemViewHolder {

        val itemBinding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LogItemViewHolder, position: Int) {
        var item = data[position]
        holder.binding.apply {
            tvtCity.text = item?.cityName
            tvCondition.text = item?.description
            tvtTemp.text = item?.temp.toString()
            iv.setImageURI(item?.photoPath?.toUri())

        }
        holder.itemView.setOnClickListener {
            item?.let { it1 -> onItemClicked(it1) }
        }

    }

    class LogItemViewHolder(item: ItemLogBinding) : RecyclerView.ViewHolder(item.root) {
        val binding: ItemLogBinding = item
    }


    override fun getItemCount() = data.size

}
