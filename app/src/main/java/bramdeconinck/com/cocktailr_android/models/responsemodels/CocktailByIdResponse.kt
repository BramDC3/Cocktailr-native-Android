package bramdeconinck.com.cocktailr_android.models.responsemodels

data class CocktailByIdResponse(
    val drinks: List<CocktailByIdResponseDrink> = listOf()
)