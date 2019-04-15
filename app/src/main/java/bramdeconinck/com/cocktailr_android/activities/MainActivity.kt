package bramdeconinck.com.cocktailr_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bramdeconinck.com.cocktailr_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun setupNavigation() {

        navController = findNavController(R.id.main_nav_host_fragment)

        setSupportActionBar(main_toolbar)
        setupActionBarWithNavController(navController)
        main_bottom_navigation_view.setupWithNavController(navController)
    }

}
