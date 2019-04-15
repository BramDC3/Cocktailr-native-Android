package bramdeconinck.com.cocktailr_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.adapters.CocktailAdapter
import bramdeconinck.com.cocktailr_android.models.Cocktail
import kotlinx.android.synthetic.main.fragment_cocktail_list.*

class CocktailListFragment : Fragment() {

    private lateinit var cocktailAdapter: CocktailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cocktail_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        val cocktails: List<Cocktail> = listOf(
            Cocktail(
                id = "16333",
                name = "Adam Bomb",
                imageUrl = "https://www.thecocktaildb.com/images/media/drink/tpxurs1454513016.jpg"
            ),
            Cocktail(
                id = "17229",
                name = "Adios Amigos Cocktail",
                imageUrl = "https://www.thecocktaildb.com/images/media/drink/8nk2mp1504819893.jpg"
            ),
            Cocktail(
                id = "14364",
                name = "Aztec Punch",
                imageUrl = "https://www.thecocktaildb.com/images/media/drink/uqwuyp1454514591.jpg"
            )
        )

        cocktailAdapter = CocktailAdapter(this, cocktails)

        rv_cocktail_list_cocktails.adapter = cocktailAdapter
    }

}
