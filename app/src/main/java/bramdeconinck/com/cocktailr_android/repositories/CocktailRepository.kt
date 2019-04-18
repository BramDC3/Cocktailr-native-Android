package bramdeconinck.com.cocktailr_android.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import bramdeconinck.com.cocktailr_android.models.Cocktail
import bramdeconinck.com.cocktailr_android.models.responsemodels.*
import bramdeconinck.com.cocktailr_android.network.CocktailApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CocktailRepository {

    val ingredients = MutableLiveData<List<String>>()
    val selectedCocktail = MutableLiveData<Cocktail>()
    val cocktails = MutableLiveData<List<Cocktail>>()

    init {
        ingredients.value = mutableListOf()
        selectedCocktail.value = null
        cocktails.value = mutableListOf()
        getAllIngredients()
    }

    private fun getAllIngredients() {
        val apiService = CocktailApi.create()
        val call = apiService.getAllIngredients()

        call.enqueue(object : Callback<IngredientsResponse> {
            override fun onResponse(call: Call<IngredientsResponse>?, response: Response<IngredientsResponse>?) {
                if (response != null) {
                    val list: List<IngredientsResponseDrink> = response.body().drinks
                    ingredients.value = list.map { ird -> ird.strIngredient1 }
                }
            }

            override fun onFailure(call: Call<IngredientsResponse>?, t: Throwable?) {
                Log.e("Error", "Something went wrong while fetching ingredients.")
            }
        })
    }

    fun getCocktailById(cocktail: Cocktail) {
        val apiService = CocktailApi.create()
        val call = apiService.getCocktailById(cocktail.id)

        call.enqueue(object : Callback<CocktailByIdResponse> {
            override fun onResponse(call: Call<CocktailByIdResponse>?, response: Response<CocktailByIdResponse>?) {
                if (response != null) {
                    val item: CocktailByIdResponseDrink = response.body().drinks[0]
                    cocktail.category = item.strCategory
                    cocktail.instructions = item.strInstructions
                    cocktail.ingredients = getIngredients(item)
                    cocktail.measurements = getMeasurements(item)
                    selectedCocktail.value = cocktail
                }
            }

            override fun onFailure(call: Call<CocktailByIdResponse>?, t: Throwable?) {
                Log.e("Error", "Something went wrong while fetching cocktail by id.")
            }
        })
    }

    fun getCocktailsByIngredient(ingredient: String) {
        cocktails.value = mutableListOf()
        val apiService = CocktailApi.create()
        val call = apiService.getCocktailsByIngredient(ingredient)

        call.enqueue(object : Callback<CocktailsByIngredientResponse> {
            override fun onResponse(
                call: Call<CocktailsByIngredientResponse>?,
                response: Response<CocktailsByIngredientResponse>?
            ) {
                if (response != null) {
                    val list: List<CocktailsByIngredientResponseDrink> = response.body().drinks
                    cocktails.value = list.map { cbird ->
                        Cocktail(
                            id = cbird.idDrink,
                            name = cbird.strDrink,
                            imageUrl = cbird.strDrinkThumb
                        )
                    }
                }
            }

            override fun onFailure(call: Call<CocktailsByIngredientResponse>?, t: Throwable?) {
                Log.e("Error", "Something went wrong while fetching cocktails by ingredient.")
            }
        })
    }

    private fun getIngredients(item: CocktailByIdResponseDrink): List<String> {
        return listOf(
            item.strIngredient1,
            item.strIngredient2,
            item.strIngredient3,
            item.strIngredient4,
            item.strIngredient5,
            item.strIngredient6,
            item.strIngredient7,
            item.strIngredient8,
            item.strIngredient9,
            item.strIngredient10,
            item.strIngredient11,
            item.strIngredient12,
            item.strIngredient13,
            item.strIngredient14,
            item.strIngredient15
        ).filter { s -> s.isNotEmpty() && s != "\n" && s != " " }
    }

    private fun getMeasurements(item: CocktailByIdResponseDrink): List<String> {
        return listOf(
            item.strMeasure1,
            item.strMeasure2,
            item.strMeasure3,
            item.strMeasure4,
            item.strMeasure5,
            item.strMeasure6,
            item.strMeasure7,
            item.strMeasure8,
            item.strMeasure9,
            item.strMeasure10,
            item.strMeasure11,
            item.strMeasure12,
            item.strMeasure13,
            item.strMeasure14,
            item.strMeasure15
        ).filter { s -> s.isNotEmpty() && s != "\n" && s != " " }
    }

}