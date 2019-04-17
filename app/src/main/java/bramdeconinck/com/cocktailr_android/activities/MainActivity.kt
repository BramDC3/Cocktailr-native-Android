package bramdeconinck.com.cocktailr_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bramdeconinck.com.cocktailr_android.R
import bramdeconinck.com.cocktailr_android.models.responsemodels.*
import bramdeconinck.com.cocktailr_android.network.CocktailApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
        // getAllIngredients()
        //getCocktailById("11007")
        getCocktailsByIngredient("Gin")
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun setupNavigation() {

        navController = findNavController(R.id.main_nav_host_fragment)

        setSupportActionBar(main_toolbar)
        setupActionBarWithNavController(navController)
        main_bottom_navigation_view.setupWithNavController(navController)
    }

    fun getAllIngredients() {
        val apiService = CocktailApi.create()
        val call = apiService.getAllIngredients()

        call.enqueue(object: Callback<IngredientsResponse> {
            override fun onResponse(call: Call<IngredientsResponse>?, response: Response<IngredientsResponse>?) {
                if (response != null) {
                    val list: List<IngredientsResponseDrink> = response.body().drinks
                    for (item: IngredientsResponseDrink in list.iterator()) {
                        Log.e("test", item.strIngredient1)
                    }
                }
            }

            override fun onFailure(call: Call<IngredientsResponse>?, t: Throwable?) {
                Log.e("Error", "Something went wrong.")
            }
        })
    }

    fun getCocktailById(id: String) {
        val apiService = CocktailApi.create()
        val call = apiService.getCocktailById(id)

        call.enqueue(object: Callback<CocktailByIdResponse> {
            override fun onResponse(call: Call<CocktailByIdResponse>?, response: Response<CocktailByIdResponse>?) {
                if (response != null) {
                    val list: List<CocktailByIdResponseDrink> = response.body().drinks
                    for (item: CocktailByIdResponseDrink in list.iterator()) {
                        Log.e("test", item.strDrink)
                    }
                }
            }

            override fun onFailure(call: Call<CocktailByIdResponse>?, t: Throwable?) {
                Log.e("Error", "Something went wrong.")
            }
        })
    }

    fun getCocktailsByIngredient(ingredient: String) {
        val apiService = CocktailApi.create()
        val call = apiService.getCocktailsByIngredient(ingredient)

        call.enqueue(object: Callback<CocktailsByIngredientResponse> {
            override fun onResponse(call: Call<CocktailsByIngredientResponse>?, response: Response<CocktailsByIngredientResponse>?) {
                if (response != null) {
                    val list: List<CocktailsByIngredientResponseDrink> = response.body().drinks
                    for (item: CocktailsByIngredientResponseDrink in list.iterator()) {
                        Log.e("test", item.strDrink)
                    }
                }
            }

            override fun onFailure(call: Call<CocktailsByIngredientResponse>?, t: Throwable?) {
                Log.e("Error", "Something went wrong.")
            }
        })
    }

}
