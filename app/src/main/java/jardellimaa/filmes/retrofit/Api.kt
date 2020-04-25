package jardellimaa.filmes.retrofit

import jardellimaa.filmes.service.FilmeService
import jardellimaa.filmes.service.GeneroService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class Api private constructor() {

    val API_KEY : String = "2962a19129e4e6947c0a17f3c94faf86"
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private object HOLDER {
        val INSTANCE = Api()
    }

    companion object {
        val instance: Api by lazy { HOLDER.INSTANCE }
    }

    val filmeService: FilmeService get() = retrofit.create(FilmeService::class.java)
    val generoService: GeneroService get() = retrofit.create(GeneroService::class.java)

}