package jardellimaa.filmes.model

import java.io.Serializable

data class Filme(val id: Int = 0,
                 val titulo: String = "",
                 val caminhoPoster: String? = "",
                 val caminhoFundo: String? = "",
                 val dataLancamento: String = "",
                 val descricao: String = "",
                 val generos: List<Int> = listOf()) : Serializable