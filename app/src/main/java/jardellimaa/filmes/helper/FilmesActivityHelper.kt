package jardellimaa.filmes.helper

import android.util.Log
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jardellimaa.filmes.activity.FilmesActivity
import jardellimaa.filmes.adapter.FilmesAdapter
import jardellimaa.filmes.utils.deResponseParaModel
import jardellimaa.filmes.response.FilmeResult
import jardellimaa.filmes.retrofit.Api
import kotlinx.android.synthetic.main.activity_filmes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesActivityHelper (val filmesActivity: FilmesActivity) {
    val filmesAdapter : FilmesAdapter by lazy { FilmesAdapter(filmesActivity) }
    val manager : GridLayoutManager by lazy { GridLayoutManager(filmesActivity, 2) }
    var isScrolling = false
    var currentItens = 0
    var totalItens = 0
    var scrollOutItens = 0
    var page = 1

    fun obtemFilmes() {
        filmesActivity.filmes_progress.visibility = ProgressBar.VISIBLE
        Api.instance.filmeService.listarFilmesPopulares(Api.instance.API_KEY, page)
            .enqueue(
                object : Callback<FilmeResult> {
                    override fun onResponse(call: Call<FilmeResult>, response: Response<FilmeResult>) {
                        if (response.isSuccessful) {
                            filmesAdapter.filmes.addAll(deResponseParaModel(
                                response.body()?.results ?: mutableListOf()))
                            filmesAdapter.notifyDataSetChanged()
                            filmesActivity.filmes_progress.visibility = ProgressBar.GONE
                                Log.i("Teste", "Teste")
                        } else {
                            //filmesActivity.filmes_progress.visibility = ProgressBar.GONE
                            mostraErro()
                        }
                    }

                    override fun onFailure(call: Call<FilmeResult>, t: Throwable) {
                        //filmesActivity.filmes_progress.visibility = ProgressBar.GONE
                        mostraErro()
                    }
                }
            )
    }

    fun scroolInfinito (){
        filmesActivity.recycler_filmes.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItens = manager.childCount
                totalItens = manager.itemCount
                scrollOutItens = manager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItens + scrollOutItens == totalItens)){
                    isScrolling = false
                    page++
                    obtemFilmes()
                }
            }
        })
    }

    fun mostraErro(){
        Toast.makeText(filmesActivity, "Erro ao carregar os filmes", Toast.LENGTH_SHORT).show()
    }

    fun configuraAdapter() {
        filmesActivity.recycler_filmes.layoutManager = manager
        filmesActivity.recycler_filmes.adapter = filmesAdapter
    }

}