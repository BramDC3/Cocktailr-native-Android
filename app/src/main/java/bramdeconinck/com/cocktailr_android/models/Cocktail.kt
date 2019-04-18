package bramdeconinck.com.cocktailr_android.models

data class Cocktail(
    val id: String,
    val name: String,
    var category: String = "",
    var instructions: String = "",
    val imageUrl: String,
    var ingredients: List<String> = listOf(),
    var measurements: List<String> = listOf()
)