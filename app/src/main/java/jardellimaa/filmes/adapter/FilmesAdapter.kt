package jardellimaa.filmes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jardellimaa.br.R
import jardellimaa.filmes.activity.DetalhesFilmeActivity
import jardellimaa.filmes.activity.FilmesActivity
import jardellimaa.filmes.dao.FilmeDAO
import jardellimaa.filmes.model.Filme
import jardellimaa.filmes.utils.montarCaminhoPoster
import kotlinx.android.synthetic.main.filme_list_item.view.*

class FilmesAdapter(val filmesActivity: FilmesActivity) : RecyclerView.Adapter<FilmesAdapter.ListaFilmesViewHolder>() {

    var filmes : MutableList<Filme> = mutableListOf()
    var filmeDAO: FilmeDAO = FilmeDAO()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaFilmesViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.filme_list_item, parent, false)
        return ListaFilmesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    override fun onBindViewHolder(holder: ListaFilmesViewHolder, position: Int) {
        val filme = filmes[position]
        holder.itemView.text_titulo_filme.text = filme.titulo
        pegarPoster(filme, holder)
        marcarFavoritoOuNao(filme, holder)
        trocarVisibilidadeAoClicar(filme, holder)
        pegarCliqueDetalhesFilme(filme, holder)
    }

    private fun marcarFavoritoOuNao(
        filme: Filme,
        holder: ListaFilmesViewHolder
    ) {
        if (filmeDAO.consultarSeEstaNaLista(filme)) {
            holder.itemView.image_gostei_preenchido.visibility = ImageView.VISIBLE
            holder.itemView.image_gostei_nao_preenchido.visibility = ImageView.GONE
        } else {
            holder.itemView.image_gostei_preenchido.visibility = ImageView.GONE
            holder.itemView.image_gostei_nao_preenchido.visibility = ImageView.VISIBLE
        }
    }

    private fun trocarVisibilidadeAoClicar(filme: Filme, holder: ListaFilmesViewHolder) {
        holder.itemView.image_gostei_nao_preenchido.setOnClickListener {
            if (holder.itemView.image_gostei_nao_preenchido.visibility == ImageView.VISIBLE) {
                holder.itemView.image_gostei_nao_preenchido.visibility = ImageView.GONE
                holder.itemView.image_gostei_preenchido.visibility = ImageView.VISIBLE
                filmeDAO.adicionarFilme(filme)
            }
        }

        holder.itemView.image_gostei_preenchido.setOnClickListener {
            if (holder.itemView.image_gostei_preenchido.visibility == ImageView.VISIBLE) {
                holder.itemView.image_gostei_preenchido.visibility = ImageView.GONE
                holder.itemView.image_gostei_nao_preenchido.visibility = ImageView.VISIBLE
                filmeDAO.removerFilme(filme)
            }
        }
    }

    private fun pegarCliqueDetalhesFilme(
        filme: Filme,
        holder: ListaFilmesViewHolder
    ) {
        holder.itemView.setOnClickListener {
            val goDetalhesFilme = Intent(filmesActivity, DetalhesFilmeActivity::class.java)
            goDetalhesFilme.putExtra("filme", filme)
            filmesActivity.startActivity(goDetalhesFilme)
        }
    }

    private fun pegarPoster(
        filme: Filme,
        holder: ListaFilmesViewHolder
    ) {
        Picasso.get()
            .load(montarCaminhoPoster(filme.caminhoPoster ?: ""))
            .into(holder.itemView.image_poster_filme)
    }

    open class ListaFilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}