package com.manage1_event.event.eventlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manage1_event.event.database.EventTable
import com.manage1_event.event.databinding.CardEventBinding

class EventAdapter(private val allEvents : List<EventTable>, private val listener : EventAdapter.ButtonClicked) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.EventViewHolder {
        val binding = CardEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = EventAdapter.EventViewHolder(binding)
        binding.moreBtn.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onButtonClicked(allEvents[position])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        holder.bindData(allEvents[position])
    }

    override fun getItemCount() = allEvents.size


    class EventViewHolder(private var eventRowBinding : CardEventBinding) : RecyclerView.ViewHolder(eventRowBinding.root) {

        fun bindData(event : EventTable) {
            eventRowBinding.nameTv.text = event.name
            eventRowBinding.dateTv.text = event.date
            eventRowBinding.noTv.text = event.seatsLeft.toString()
        }

        fun getBinding() = eventRowBinding

    }

    interface ButtonClicked{
        fun onButtonClicked(event : EventTable)
    }

}