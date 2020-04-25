package jardellimaa.filmes.service

import jardellimaa.filmes.response.Generos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneroService {

    @GET("genre/movie/list")
    fun listarGeneros(
        @Query("api_key") chaveApi : String,
        @Query("language") idioma : String = "pt-BR"
    ) : Call<Generos>
}