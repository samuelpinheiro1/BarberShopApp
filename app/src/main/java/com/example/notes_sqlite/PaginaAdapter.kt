package com.example.notes_sqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PaginaAdapter(private var paginas: List<Pagina>, context: Context) : RecyclerView.Adapter<PaginaAdapter.PaginaViewHolder>() {
    private val db: PaginaDataBaseHelper = PaginaDataBaseHelper(context)

    class PaginaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pagina_item, parent, false)
        return PaginaViewHolder(view)
    }

    override fun getItemCount(): Int = paginas.size

    override fun onBindViewHolder(holder: PaginaViewHolder, position: Int) {
        val note = paginas[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.pegarTodasPaginas())
            Toast.makeText(holder.itemView.context, "Página Apagada", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshData(newPagina: List<Pagina>){
        paginas = newPagina
        notifyDataSetChanged()
    }
}