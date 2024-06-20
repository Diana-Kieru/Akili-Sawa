package com.example.akilisawa.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.akilisawa.R
import com.example.akilisawa.adapter.RadioButtonPagerAdapter
import com.example.akilisawa.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the ViewPager with an adapter
        viewPager = binding.viewPager
        viewPager.adapter = RadioButtonPagerAdapter(this)

        // Change the selected radio button when a page is selected in the ViewPager
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.radioGroup.check(binding.radioGroup.getChildAt(position).id)
            }
        })

        // Change the current item in the ViewPager when a radio button is selected
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedIndex = binding.radioGroup.indexOfChild(binding.radioGroup.findViewById(checkedId))
            viewPager.currentItem = selectedIndex
        }
    }
}