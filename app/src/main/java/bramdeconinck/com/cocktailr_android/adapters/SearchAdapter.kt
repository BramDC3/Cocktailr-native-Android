package bramdeconinck.com.cocktailr_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.fragments.SearchFragment
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository
import kotlinx.android.synthetic.main.search_list_content.view.*

class SearchAdapter(private val fragment: SearchFragment, private val ingredients: List<String>): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { i ->
            CocktailRepository.getCocktailsByIngredient(i.tag as String)
            fragment.findNavController().navigateUp()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = ingredients[position]

        holder.titleView.text = item

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = ingredients.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.tv_search_title
    }

}
