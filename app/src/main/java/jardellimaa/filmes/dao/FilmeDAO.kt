package jardellimaa.filmes.dao

import jardellimaa.filmes.model.Filme

class FilmeDAO {

    companion object {
        private val filmesListaDao: MutableList<Filme> = mutableListOf()
    }

    fun adicionarFilme(filme:Filme){
        filmesListaDao.add(filme)
    }

    fun retornarFilmes() : List<Filme>{
        return filmesListaDao
    }
    
    fun filtrarPorTitulo(string : String) : List<Filme>{
        return filmesListaDao.filter({ it.titulo.toLowerCase()
                .contains(string.toLowerCase()) })
    }
    
    fun retornarFilme(filme : Filme) : Filme {
        return filmesListaDao.find( { it == filme } ) ?: Filme()
    }

    fun consultarSeEstaNaLista(filme: Filme) : Boolean {
        return filme in filmesListaDao
    }

    fun removerFilme(filme: Filme){
        filmesListaDao.remove(filme)
    }

}
