package bramdeconinck.com.cocktailr_android.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.adapters.CocktailAdapter
import bramdeconinck.com.cocktailr_android.models.Cocktail
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository
import kotlinx.android.synthetic.main.fragment_cocktail_list.*


class CocktailListFragment : Fragment() {

    private lateinit var cocktailAdapter: CocktailAdapter
    private lateinit var cocktails: MutableLiveData<List<Cocktail>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cocktail_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        prepareRecyclerView()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                this.findNavController().navigate(R.id.toSearch)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun prepareRecyclerView() {
        cocktails = CocktailRepository.cocktails
        cocktailAdapter = CocktailAdapter(this, cocktails)
        rv_cocktail_list_cocktails.adapter = cocktailAdapter
    }

    private fun initObservers() {
        CocktailRepository.cocktails.observe(this, Observer {
            cocktailAdapter.notifyDataSetChanged()
        })
    }

}
