package jardellimaa.filmes.activity

import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import jardellimaa.br.R
import jardellimaa.filmes.helper.FilmesFavoritosActivityHelper
import kotlinx.android.synthetic.main.activity_filmes_favoritos.*

class FilmesFavoritosActivity : AppCompatActivity() {

    val helper : FilmesFavoritosActivityHelper by lazy{ FilmesFavoritosActivityHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes_favoritos)
        helper.configuraAdapter()
        helper.obtemFilmesFavoritos()
    }
}
