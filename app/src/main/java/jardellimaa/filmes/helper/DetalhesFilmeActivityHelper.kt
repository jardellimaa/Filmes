package jardellimaa.filmes.helper

import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import jardellimaa.br.R
import jardellimaa.filmes.activity.DetalhesFilmeActivity
import jardellimaa.filmes.model.Filme
import jardellimaa.filmes.response.Genero
import jardellimaa.filmes.response.Generos
import jardellimaa.filmes.retrofit.Api
import jardellimaa.filmes.utils.montarCaminhoFundo
import jardellimaa.filmes.utils.montarCaminhoPoster
import kotlinx.android.synthetic.main.activity_detalhes_filme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*
import java.text.SimpleDateFormat as SimpleDateFormat

class DetalhesFilmeActivityHelper(val detalhesFilmeActivity: DetalhesFilmeActivity) {

    fun montarDescricao(filme: Filme) {
        Picasso.get()
            .load(montarCaminhoFundo(filme.caminhoFundo ?: filme.caminhoPoster ?: ""))
            .fit()
            .into(detalhesFilmeActivity.image_fundo_filme)
        detalhesFilmeActivity.text_destalhes_titulo_filme.text = filme.titulo
        detalhesFilmeActivity.text_data_lancamento_2.text = formatarData(filme.dataLancamento)
        detalhesFilmeActivity.text_descricao.text = filme.descricao
        detalhesFilmeActivity.title = filme.titulo
        preencherGenero(filme)
    }

    fun preencherGenero(filme: Filme) {
        Api.instance.generoService.listarGeneros(Api.instance.API_KEY).enqueue(
            object : Callback<Generos> {
                override fun onResponse(call: Call<Generos>, response: Response<Generos>)  {
                    if (response.isSuccessful) {
                        var generos = response.body()?.genres ?: listOf()
                        detalhesFilmeActivity.text_generos.text = filtrarGenero(filme, generos)
                    } else {
                        mostraErro()
                    }
                }

                override fun onFailure(call: Call<Generos>, t: Throwable) {
                    mostraErro()
                }
            }
        )
    }

    fun mostraErro(){
        Toast.makeText(detalhesFilmeActivity, "Erro ao carregar os generos", Toast.LENGTH_SHORT).show()
    }

    private fun filtrarGenero(filme: Filme, generos: List<Genero>): String {
        var string = ""
        for (x in filme.generos.indices) {
            for (j in generos.indices) {
                if (filme.generos[x] == generos[j].id) {
                    string = string + generos[j].name + ", "
                }
            }
        }
        return try {
            string.substring(0, string.length-2)
        } catch (e: Exception){
            ""
        }
    }

    private fun formatarData(data: String) : String{
        var simpleDateFormatEua = SimpleDateFormat("yyyy-MM-dd")
        var dataFormatada = simpleDateFormatEua.parse(data)
        var simpleDateFormatBr = SimpleDateFormat("dd/MM/yyyy")
        return simpleDateFormatBr.format(dataFormatada)
    }

}