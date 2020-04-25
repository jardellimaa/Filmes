package jardellimaa.filmes.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import jardellimaa.br.R
import jardellimaa.filmes.helper.FilmesActivityHelper

class FilmesActivity : AppCompatActivity() {

    val helper : FilmesActivityHelper by lazy { FilmesActivityHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes)
        helper.configuraAdapter()
        helper.obtemFilmes()
        helper.scroolInfinito()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filmes, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_favoritos -> {
                val goFilmesFavoritos = Intent(this@FilmesActivity, FilmesFavoritosActivity::class.java)
                startActivity(goFilmesFavoritos)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}