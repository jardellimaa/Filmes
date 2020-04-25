package jardellimaa.filmes.response

class FilmeResponse (val id: Int,
                     val title : String,
                     val poster_path : String,
                     val backdrop_path : String,
                     val release_date : String,
                     val overview : String,
                     val genre_ids : List<Int>)