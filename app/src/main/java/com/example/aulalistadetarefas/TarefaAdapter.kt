package com.example.aulalistadetarefas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aulalistadetarefas.Tarefa
import com.example.aulalistadetarefas.databinding.ItemTarefaBinding

class TarefaAdapter(

    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private val listaTarefas = mutableListOf<Tarefa>(
        Tarefa("Ir ao mercado", "10/10/2022", "Pessoal")
    )

    inner class TarefaViewHolder(
//        itemView: View
        val binding: ItemTarefaBinding
    ): RecyclerView.ViewHolder(
//        itemView
        binding.root
    ) {

//        private val textTitulo: TextView
//        private val textTipo: TextView
//        private val textData: TextView
//        private val btnExcluir: Button
        init {
//            textTitulo = itemView.findViewById(R.id.text_titulo)
//            textTipo = itemView.findViewById(R.id.text_tipo)
//            textData = itemView.findViewById(R.id.text_data)
//            btnExcluir = itemView.findViewById(R.id.btn_excluir)
        }

        fun bind(tarefa: Tarefa, position: Int, onDeleteClick: (Int) -> Unit){
            with(binding) {
                tvTitulo.text = tarefa.titulo
                tvTipo.text = tarefa.tipo
                tvData.text = tarefa.data

                btnExcluir.setOnClickListener{
                    onDeleteClick(position)
                }
            }

        }
    }

    // cria o object view do xml da item da lista (item_tarefa.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {

        /*val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        */
        val itemView = ItemTarefaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TarefaViewHolder(itemView)
    }

    // conecta itens com a interface
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listaTarefas[position]
        holder.bind(tarefa, position, onDeleteClick)

    }

    // retorna quantidade de itens da lista
    override fun getItemCount(): Int {
        return listaTarefas.size
    }

    fun adicionarTarefa(tarefa: Tarefa){
        listaTarefas.add(tarefa)
        notifyItemInserted(listaTarefas.size - 1)
    }

    fun excluirTarefa(posicao: Int){
        listaTarefas.removeAt(posicao)
        notifyItemRemoved(posicao)
    }
}