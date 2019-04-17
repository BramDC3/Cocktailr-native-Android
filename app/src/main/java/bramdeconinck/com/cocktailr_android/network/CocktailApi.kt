package bramdeconinck.com.cocktailr_android.network

import bramdeconinck.com.cocktailr_android.models.responsemodels.CocktailByIdResponse
import bramdeconinck.com.cocktailr_android.models.responsemodels.CocktailsByIngredientResponse
import bramdeconinck.com.cocktailr_android.models.responsemodels.IngredientsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("list.php?i=list")
    fun getAllIngredients(): Call<IngredientsResponse>

    @GET("lookup.php")
    fun getCocktailById(@Query("i") id: String?): Call<CocktailByIdResponse>

    @GET("filter.php")
    fun getCocktailsByIngredient(@Query("i") ing: String?): Call<CocktailsByIngredientResponse>

    companion object Factory {
        private const val BASE_URL: String = "https://www.thecocktaildb.com/api/json/v1/36578/"
        fun create(): CocktailApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CocktailApi::class.java)
        }
    }

}