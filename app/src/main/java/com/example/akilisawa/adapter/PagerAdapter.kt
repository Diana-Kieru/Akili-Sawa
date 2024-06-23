import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.akilisawa.R
import com.example.akilisawa.auth.PageData

class RadioButtonPagerAdapter(private val pages: List<PageData>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_radio_button, container, false)

        val illustrationImageView = view.findViewById<ImageView>(R.id.illustrationImageView)
        val welcomeTextView = view.findViewById<TextView>(R.id.welcomeTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)

        // Set data to views
        illustrationImageView.setImageResource(pages[position].imageResId)
        welcomeTextView.text = pages[position].title
        descriptionTextView.text = pages[position].description

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = pages.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
}