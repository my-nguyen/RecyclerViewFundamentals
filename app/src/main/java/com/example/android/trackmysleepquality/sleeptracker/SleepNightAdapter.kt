package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ItemSleepNightBinding

// class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
// change class parent from RecyclerView.Adapter to ListAdapter
class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
    // ListAdapter keeps track of this list
    /*var nights =  listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/

    // ListAdapter implements this method
    // override fun getItemCount() = nights.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSleepNightBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // val item = nights[position]
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SleepNight) {
            binding.apply {
                val resources = itemView.context.resources
                // sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resources)
                qualityString.text= convertNumericQualityToString(item.sleepQuality, resources)
                qualityImage.setImageResource(when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                })
            }
        }
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        // If the items have the same nightId, they are the same item
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        // whether oldItem and newItem contain the same data; that is, whether they are equal.
        // the equality check will check all the fields, because SleepNight is a data class.
        // data classes automatically define equals and a few other methods.
        return oldItem == newItem
    }
}