package bramdeconinck.com.cocktailr_android.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.adapters.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter
    private val ingredients: List<String> = listOf(
        "Light rum",
        "Applejack",
        "Gin",
        "Dark rum",
        "Sweet Vermouth"
    )
    private lateinit var filteredIngredients: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onStart() {
        super.onStart()

        prepareRecyclerView()

        startListeners()
    }

    private fun prepareRecyclerView() {
        filteredIngredients = mutableListOf()
        filteredIngredients.addAll(ingredients)

        searchAdapter = SearchAdapter(this, filteredIngredients)

        rv_search_ingredients.adapter = searchAdapter
    }

    private fun startListeners() {
        et_search_filter.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                filteredIngredients.clear()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filterIngredients(s)
            }

            override fun afterTextChanged(s: Editable) {
                searchAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun filterIngredients(s: CharSequence) {
        if (s.isNotEmpty())
            filteredIngredients.addAll(ingredients.filter { i ->
                i.toLowerCase().contains(s.toString().toLowerCase())
            })
        else
            filteredIngredients.addAll(ingredients)
    }

}
