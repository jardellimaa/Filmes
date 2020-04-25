package jardellimaa.filmes.helper

import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import jardellimaa.filmes.activity.FilmesFavoritosActivity
import jardellimaa.filmes.adapter.FavoritosAdapter
import jardellimaa.filmes.dao.FilmeDAO
import jardellimaa.filmes.response.FilmeResult
import jardellimaa.filmes.retrofit.Api
import jardellimaa.filmes.utils.deResponseParaModel
import kotlinx.android.synthetic.main.activity_filmes_favoritos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesFavoritosActivityHelper(val filmesFavoritosActivity: FilmesFavoritosActivity) {

    val favoritosAdapter : FavoritosAdapter by lazy { FavoritosAdapter(filmesFavoritosActivity) }
    val filmeDAO : FilmeDAO = FilmeDAO()

    fun configuraAdapter(){
        filmesFavoritosActivity.recycler_filmes_favoritos.layoutManager = LinearLayoutManager(filmesFavoritosActivity)
        filmesFavoritosActivity.recycler_filmes_favoritos.adapter = favoritosAdapter
    }

    fun obtemFilmesFavoritos() {
        filmesFavoritosActivity.filmes_progress_favoritos.visibility = ProgressBar.VISIBLE

        if(filmeDAO.listaEstaVazia()) {
            mostraErro()
            filmesFavoritosActivity.filmes_progress_favoritos.visibility = ProgressBar.GONE
        } else {
            favoritosAdapter.filmes = filmeDAO.retornarFilmes()
            filmesFavoritosActivity.filmes_progress_favoritos.visibility = ProgressBar.GONE
        }

    }

    fun mostraErro(){
        Toast.makeText(filmesFavoritosActivity, "Você não possui nenhum favorito", Toast.LENGTH_SHORT).show()
    }

}