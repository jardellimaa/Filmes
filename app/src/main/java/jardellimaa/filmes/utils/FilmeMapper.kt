package jardellimaa.filmes.utils

import jardellimaa.filmes.model.Filme
import jardellimaa.filmes.response.FilmeResponse

fun deResponseParaModel(listaFilmeResponse: MutableList<FilmeResponse>) : MutableList<Filme>{
    var listaFilme : MutableList<Filme> = mutableListOf()

    for(filmeResponse : FilmeResponse in listaFilmeResponse){
        val filme : Filme = Filme(
            filmeResponse.id,
            filmeResponse.title,
            filmeResponse.poster_path,
            filmeResponse.backdrop_path,
            filmeResponse.release_date,
            filmeResponse.overview,
            filmeResponse.genre_ids
        )
        listaFilme.add(filme)
    }
    return listaFilme
}