package bramdeconinck.com.cocktailr_android.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

import bramdeconinck.com.cocktailr_android.R
import kotlinx.android.synthetic.main.fragment_suggestion.*

class SuggestionFragment : Fragment() {

    private var madePicture: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_suggestion, container, false)
    }

    override fun onStart() {
        super.onStart()

        loadSpinner()
        startListeners()
    }

    private fun loadSpinner() {
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

    private fun startListeners() {
        imgbtn_suggestion_camera.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 123)
        }

        btn_suggestion_add.setOnClickListener {
            if (madePicture && !tv_suggestion_name.text.isNullOrBlank() && !tv_suggestion_description.text.isNullOrBlank()) {
                Toast.makeText(context, "Thank you for your suggestion!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val bitmap = data!!.extras!!.get("data") as Bitmap
            iv_suggestion_image.setImageBitmap(bitmap)
            madePicture = true
        }
    }

}
