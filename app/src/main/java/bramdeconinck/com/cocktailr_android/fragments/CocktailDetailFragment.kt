package bramdeconinck.com.cocktailr_android.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.models.Cocktail
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_cocktail_detail.*

class CocktailDetailFragment : Fragment() {

    private var cocktail: Cocktail? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        initObservers()
    }

    private fun fillDetailViews() {
        Glide.with(this).load(cocktail?.imageUrl).into(iv_cocktail_detail_image)
        tv_cocktail_detail_title.text = cocktail?.name ?: ""
        tv_cocktail_detail_category.text = "Category: ${cocktail?.category ?: ""}"
        tv_cocktail_detail_description.text = cocktail?.instructions ?: ""
        createTableRows()
    }

    private fun createTableRows() {
        tl_cocktail_detail_ingredients.removeAllViews()

        val params = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

        val tv1 = TextView(this.context)
        tv1.text = "INGREDIENT"
        tv1.background = ContextCompat.getDrawable(context!!, R.drawable.grid_border)
        tv1.setPadding(6, 6, 6, 6)
        tv1.layoutParams = params
        tv1.gravity = Gravity.CENTER
        tv1.setTextColor(ContextCompat.getColor(this.context!!, R.color.black))
        tv1.typeface = Typeface.DEFAULT_BOLD

        val tv2 = TextView(this.context)
        tv2.text = "AMOUNT"
        tv2.setPadding(6, 6, 6, 6)
        tv2.layoutParams = params
        tv2.gravity = Gravity.CENTER
        tv2.setTextColor(ContextCompat.getColor(this.context!!, R.color.black))
        tv2.typeface = Typeface.DEFAULT_BOLD

        val tr = TableRow(this.context)
        tr.background = ContextCompat.getDrawable(context!!, R.drawable.grid_border)

        tr.addView(tv1)
        tr.addView(tv2)

        tl_cocktail_detail_ingredients.addView(tr)

        if (!cocktail?.ingredients.isNullOrEmpty() && !cocktail?.measurements.isNullOrEmpty()) {
            cocktail!!.ingredients.forEachIndexed { index, ingredient ->
                val tv1 = TextView(this.context)
                tv1.text = ingredient
                tv1.background = ContextCompat.getDrawable(context!!, R.drawable.grid_border)
                tv1.setPadding(32, 6, 6, 6)
                tv1.layoutParams = params

                val tv2 = TextView(this.context)
                if (index >= cocktail!!.measurements.count()) {
                    tv2.text = ""
                } else {
                    tv2.text = cocktail!!.measurements[index]
                }
                tv2.setPadding(32, 6, 6, 6)
                tv2.layoutParams = params

                val tr = TableRow(this.context)
                tr.background = ContextCompat.getDrawable(context!!, R.drawable.grid_border)

                tr.addView(tv1)
                tr.addView(tv2)

                tl_cocktail_detail_ingredients.addView(tr)
            }
        }
    }

    private fun initObservers() {
        CocktailRepository.selectedCocktail.observe(this, Observer {
            cocktail = CocktailRepository.selectedCocktail.value
            fillDetailViews()
        })
    }

}
