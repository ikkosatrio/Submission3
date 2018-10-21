package dicodingxkejar.ikko.com.submission3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dicodingxkejar.ikko.com.submission3.R.id.*
import dicodingxkejar.ikko.com.submission3.fragment.FavoriteTeamFragment
import dicodingxkejar.ikko.com.submission3.fragment.NextFragment
import dicodingxkejar.ikko.com.submission3.fragment.PastFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBottomNavigation.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                menuNext -> {
                    loadNextFragment(savedInstanceState)
                }
                menuPast -> {
                    loadPastFragment(savedInstanceState)
                }
                menuFavorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }

            }
            true
        })
        viewBottomNavigation.selectedItemId = menuPast
    }

    private fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.viewMainContainer, FavoriteTeamFragment(), FavoriteTeamFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadPastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.viewMainContainer, PastFragment(), PastFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.viewMainContainer, NextFragment(), NextFragment::class.simpleName)
                    .commit()
        }
    }
}
