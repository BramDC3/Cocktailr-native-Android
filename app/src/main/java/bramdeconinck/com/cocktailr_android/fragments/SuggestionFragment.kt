package bramdeconinck.com.cocktailr_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import bramdeconinck.com.cocktailr_android.R

class SuggestionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_suggestion, container, false)
    }

    override fun onStart() {
        super.onStart()

        val spinner: Spinner = view!!.findViewById(R.id.sp_suggestion_category)
        ArrayAdapter.createFromResource(
            this.context!!,
            R.array.cocktails_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

}
