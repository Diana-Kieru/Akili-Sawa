package com.example.akilisawa.auth

import RadioButtonPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.akilisawa.R
import com.example.akilisawa.databinding.ActivityAuthBinding

// Data class definition
data class PageData(val imageResId: Int, val title: String, val description: String)

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the ViewPager with an adapter
        val data = listOf(
            PageData(R.drawable.splash_1_illustration, "Welcome to Sawa App", "Begin your journey to comprehensive mental health with Sawa App. Connect with  qualified specialists ready to support you on your path to wellness."),
            PageData(R.drawable.splash_2_illustration, "Tailored Mental Health Services", "We understand that every individual's journey to mental wellness is unique. Get personalized support from professionals who align with your needs and goals."),
            PageData(R.drawable.splash_3_illustration, "Empowering Connections", "With Sawa App, help is just a tap away. Access help via text, video calls, or in-person sessions whenever and wherever you need it."),
            PageData(R.drawable.splash_4_illustration, "Your Privacy, Our Priority", "Rest assured that your privacy and confidentiality are our top priorities. Focus on your journey to wellness with peace of mind.")
        )

        binding.viewPager.adapter = RadioButtonPagerAdapter(data)

        // Set up the RadioButtons to work with the ViewPager
        val radioButtons = listOf(
            binding.radioButton1,
            binding.radioButton2,
            binding.radioButton3,
            binding.radioButton4
        )

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                radioButtons.forEachIndexed { index, radioButton ->
                    radioButton.isChecked = index == position
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        // Set up click listeners for the buttons
        binding.getStartedButton.setOnClickListener {
            // Handle get started button click
        }

        binding.logInButton.setOnClickListener {
            // Handle log in button click
        }
    }
}