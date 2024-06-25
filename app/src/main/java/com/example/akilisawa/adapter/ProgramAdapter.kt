package com.example.akilisawa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.akilisawa.R


data class ProgramItem(
    val icon: Int,
    val title: String,
    val subtitle: String,
    val backgroundColor: Int
)
class ProgramAdapter(private val programs: List<ProgramItem>) :
    RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    class ProgramViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.programCardView)
        val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val subtitleTextView: TextView = view.findViewById(R.id.subtitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_program, parent, false)
        return ProgramViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val program = programs[position]
        holder.cardView.setCardBackgroundColor(program.backgroundColor)
        holder.iconImageView.setImageResource(program.icon)
        holder.titleTextView.text = program.title
        holder.subtitleTextView.text = program.subtitle
    }

    override fun getItemCount() = programs.size
}