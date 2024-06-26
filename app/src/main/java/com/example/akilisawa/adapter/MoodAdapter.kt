package com.example.akilisawa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.akilisawa.R

data class MoodItem(
    val moodImage: Int
)

class MoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val moodImageView: ImageView = view.findViewById(R.id.moodIcon)
}

class MoodAdapter(private val moods: List<MoodItem>) :
    RecyclerView.Adapter<MoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val mood = moods[position]
        holder.moodImageView.setImageResource(mood.moodImage)
    }

    override fun getItemCount() = moods.size
}