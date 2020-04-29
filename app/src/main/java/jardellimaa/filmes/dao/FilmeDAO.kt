package jardellimaa.filmes.dao

import jardellimaa.filmes.model.Filme

class FilmeDAO {

    companion object {
        private val filmesListaDao: MutableList<Filme> = mutableListOf()
    }

    fun adicionarFilme(filme:Filme){
        filmesListaDao.add(filme)
    }

    fun retornarFilmes() : MutableList<Filme>{
        return filmesListaDao
    }

    fun listaEstaVazia() : Boolean{
        return filmesListaDao.size < 1
    }

    fun consultarSeEstaNaLista(filme: Filme) : Boolean {
        return filme in filmesListaDao
    }

    fun removerFilme(filme: Filme){
        filmesListaDao.remove(filme)
    }

}
