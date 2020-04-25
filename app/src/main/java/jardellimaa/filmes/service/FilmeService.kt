package jardellimaa.filmes.service

import jardellimaa.filmes.response.FilmeResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeService {

    @GET("movie/popular")
    fun listarFilmesPopulares(
        @Query("api_key") chaveApi : String,
        @Query("page") page : Int = 1,
        @Query("language") idioma : String = "pt-BR"
    ) : Call<FilmeResult>

}