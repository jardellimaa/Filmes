package jardellimaa.filmes.adapter

import android.content.Intent
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jardellimaa.br.R
import jardellimaa.filmes.activity.DetalhesFilmeActivity
import jardellimaa.filmes.activity.FilmesFavoritosActivity
import jardellimaa.filmes.model.Filme
import jardellimaa.filmes.utils.montarCaminhoPoster
import kotlinx.android.synthetic.main.favorito_list_item.view.*

class FavoritosAdapter(val filmesFavoritosActivity: FilmesFavoritosActivity) : RecyclerView.Adapter<FavoritosAdapter.FilmesFavoritosViewHolder>() {

    var filmes : MutableList<Filme> = mutableListOf()
        set (value : MutableList<Filme>){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesFavoritosViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.favorito_list_item, parent, false)
        return FilmesFavoritosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    override fun onBindViewHolder(holder: FilmesFavoritosViewHolder, position: Int) {
        val filme = filmes[position]
        holder.itemView.text_titulo_filme_favorito.text = filme.titulo
        holder.itemView.text_data_lancamento_favorito.text = filme.dataLancamento
        holder.itemView.text_descricao_filme_favorito.text = filme.descricao
        pegarPosterFavorito(filme, holder)
        pegarCliqueDetalhesFilme(holder, filme)
    }

    private fun pegarCliqueDetalhesFilme(
        holder: FilmesFavoritosViewHolder,
        filme: Filme
    ) {
        holder.itemView.setOnClickListener {
            val goDetalhesFilme = Intent(filmesFavoritosActivity, DetalhesFilmeActivity::class.java)
            goDetalhesFilme.putExtra("filme", filme)
            filmesFavoritosActivity.startActivity(goDetalhesFilme)
        }
    }

    private fun pegarPosterFavorito(
        filme: Filme,
        holder: FilmesFavoritosViewHolder
    ) {
        Picasso.get()
            .load(montarCaminhoPoster(filme.caminhoPoster ?: ""))
            .into(holder.itemView.image_filme_favorito)
    }

    open class FilmesFavoritosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}