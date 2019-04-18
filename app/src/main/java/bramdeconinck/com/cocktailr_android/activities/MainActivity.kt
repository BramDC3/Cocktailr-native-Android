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
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository
import bramdeconinck.com.cocktailr_android.repositories.CocktailRepository.getCocktailsByIngredient
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
        getCocktailsByIngredient("Tequila")
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun setupNavigation() {
        navController = findNavController(R.id.main_nav_host_fragment)

        setSupportActionBar(main_toolbar)
        setupActionBarWithNavController(navController)
        main_bottom_navigation_view.setupWithNavController(navController)
    }

}
