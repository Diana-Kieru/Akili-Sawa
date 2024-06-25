package com.example.akilisawa.explore

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akilisawa.R
import com.example.akilisawa.adapter.ProgramAdapter
import com.example.akilisawa.adapter.ProgramItem

class HomepageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView
        val programRecyclerView: RecyclerView = view.findViewById(R.id.programRecyclerView)
        programRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val programs = listOf(
            ProgramItem(R.drawable.mood___sad, "Psychologists", "View professionals", Color.parseColor("#E6E6FA")),
            ProgramItem(R.drawable.mood___calm, "Psychiatrists", "View professionals", Color.parseColor("#E0FFFF")),
            ProgramItem(R.drawable.mood___happy, "Counselors", "View professionals", Color.parseColor("#FFD700")),
            ProgramItem(R.drawable.mood___fear, "Therapists", "View professionals", Color.parseColor("#FFA07A")),
            ProgramItem(R.drawable.mood___angry, "Social Workers", "View professionals", Color.parseColor("#FFC0CB"))
        )

        val adapter = ProgramAdapter(programs)
        programRecyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomepageFragment()
    }
}