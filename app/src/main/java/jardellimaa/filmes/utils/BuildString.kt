package jardellimaa.filmes.utils

const val CAMINHO_PADRAO : String = "https://image.tmdb.org/t/p/w500"
const val CAMINHO_FUNDO : String = "https://image.tmdb.org/t/p/original"

fun montarCaminhoPoster(caminhoPoster : String) : String {
    return CAMINHO_PADRAO + caminhoPoster
}

fun montarCaminhoFundo(caminhoFundo : String) : String {
    return CAMINHO_FUNDO + caminhoFundo
}