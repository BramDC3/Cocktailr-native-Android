package bramdeconinck.com.cocktailr_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_cocktail_detail.*

class CocktailDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(bramdeconinck.com.cocktailr_android.R.layout.fragment_cocktail_detail, container, false)
    }

    /*
    override fun onStart() {
        super.onStart()

        val params = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

        val tv1 = TextView(this.context)
        tv1.text = "Tequila"
        tv1.background = ContextCompat.getDrawable(context!!, bramdeconinck.com.cocktailr_android.R.drawable.grid_border)
        tv1.setPadding(32, 6, 6, 6)
        tv1.layoutParams = params

        val tv2 = TextView(this.context)
        tv2.text = "1 1/2 oz"
        tv2.setPadding(32, 6, 6, 6)
        tv2.layoutParams = params

        val tr = TableRow(this.context)
        tr.background = ContextCompat.getDrawable(context!!, bramdeconinck.com.cocktailr_android.R.drawable.grid_border)

        tr.addView(tv1)
        tr.addView(tv2)

        tl_cocktail_detail_ingredients.addView(tr)
    }
    */

}
