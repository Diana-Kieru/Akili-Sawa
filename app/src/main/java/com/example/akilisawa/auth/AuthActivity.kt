package com.example.akilisawa.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.akilisawa.R
import com.example.akilisawa.adapter.RadioButtonPagerAdapter
import com.example.akilisawa.databinding.ActivityAuthBinding
import com.google.android.material.tabs.TabLayoutMediator

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

        // Set up the TabLayout to work with the ViewPager
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }
}