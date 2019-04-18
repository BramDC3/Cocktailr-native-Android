package bramdeconinck.com.cocktailr_android.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.adapters.SearchAdapter
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository.ingredients
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter
    private var ingredients: List<String> = listOf()
    private lateinit var filteredIngredients: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onStart() {
        super.onStart()

        prepareRecyclerView()
        initObservers()
        startListeners()
    }

    private fun initObservers() {
        CocktailRepository.ingredients.observe(this, Observer {
            ingredients = CocktailRepository.ingredients.value!!
            filterIngredients(et_search_filter.text)
            searchAdapter.notifyDataSetChanged()
        })
    }

    private fun prepareRecyclerView() {
        ingredients = CocktailRepository.ingredients.value!!
        filteredIngredients = mutableListOf()
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
            filteredIngredients.addAll(ingredients!!.filter { i ->
                i.toLowerCase().contains(s.toString().toLowerCase())
            })
        else
            filteredIngredients.addAll(ingredients!!)
    }

}
