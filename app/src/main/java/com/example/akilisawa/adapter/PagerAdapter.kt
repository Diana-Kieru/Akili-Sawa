package com.example.akilisawa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.viewpager.widget.PagerAdapter
import com.example.akilisawa.R
import com.example.akilisawa.auth.AuthActivity

class RadioButtonPagerAdapter(private val radioButtonData: List<AuthActivity.RadioButtonData>) : PagerAdapter() {

    override fun getCount(): Int = radioButtonData.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_radio_button, container, false)
        val radioButton = view.findViewById<RadioButton>(R.id.radioButton)
        radioButton.isChecked = position == 0 // Set the first radio button as checked initially

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}