package bramdeconinck.com.cocktailr_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.fragments.CocktailListFragment
import bramdeconinck.com.cocktailr_android.models.Cocktail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cocktail_list_content.view.*

class CocktailAdapter(private val fragment: CocktailListFragment, private val cocktails: List<Cocktail>): RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { c ->
            // Hier nog cocktail meegeven (Safe Args?)
            fragment.findNavController().navigate(R.id.toCocktailDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Cocktail = cocktails[position]

        Glide.with(fragment)
            .load(item.imageUrl)
            .into(holder.imageView)

        holder.titleView.text = item.name
        holder.idView.text = "ID: ${item.id}"

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = cocktails.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.iv_cocktail_image
        val titleView: TextView = view.tv_cocktail_title
        val idView: TextView = view.tv_cocktail_id
    }

}