package jardellimaa.filmes.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import jardellimaa.br.R
import jardellimaa.filmes.dao.FilmeDAO
import jardellimaa.filmes.helper.DetalhesFilmeActivityHelper
import jardellimaa.filmes.model.Filme

class DetalhesFilmeActivity : AppCompatActivity() {

    private val helper: DetalhesFilmeActivityHelper by lazy { DetalhesFilmeActivityHelper(this) }
    private var filme : Filme = Filme()
    var filmeDAO: FilmeDAO = FilmeDAO()

    private lateinit var menuGosteiNaoPreenchido : MenuItem
    private lateinit var menuGosteiPreenchido : MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)
        filme = intent.getSerializableExtra("filme") as Filme
        helper.montarDescricao(filme)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes, menu)

        if(menu != null) {
            menuGosteiNaoPreenchido = menu.findItem(R.id.menu_gostei_nao_preenchido)
            menuGosteiPreenchido = menu.findItem(R.id.menu_gostei_preenchido)

            if(filmeDAO.consultarSeEstaNaLista(filme)){
                menuGosteiPreenchido.isVisible = true
                menuGosteiNaoPreenchido.isVisible = false
            } else {
                menuGosteiPreenchido.isVisible = false
                menuGosteiNaoPreenchido.isVisible = true
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_gostei_nao_preenchido -> {
                if (menuGosteiNaoPreenchido.isVisible){
                    menuGosteiNaoPreenchido.isVisible = false
                    menuGosteiPreenchido.isVisible = true
                    filmeDAO.adicionarFilme(filme)
                }
            }
            R.id.menu_gostei_preenchido -> {
                if (menuGosteiPreenchido.isVisible){
                    menuGosteiPreenchido.isVisible = false
                    menuGosteiNaoPreenchido.isVisible = true
                    filmeDAO.removerFilme(filme)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
