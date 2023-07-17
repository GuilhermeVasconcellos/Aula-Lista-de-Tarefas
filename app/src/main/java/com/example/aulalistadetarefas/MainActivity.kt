package com.example.aulalistadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aulalistadetarefas.TarefaAdapter
import com.example.aulalistadetarefas.Tarefa
import com.example.aulalistadetarefas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    private lateinit var editTitulo: EditText
//    private lateinit var editData: EditText
//    private lateinit var radioGroupTipo: RadioGroup
//    private lateinit var radioTrabalho: RadioButton
//    private lateinit var radioPessoal: RadioButton
//    private lateinit var btnAdicionar: Button

//    private lateinit var rvTarefas: RecyclerView
    private lateinit var tarefaAdapter: TarefaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

//        editTitulo = findViewById(R.id.edit_titulo)
//        editData = findViewById(R.id.edit_data)
//        radioGroupTipo = findViewById(R.id.radio_group)
//        radioTrabalho = findViewById(R.id.radio_trabalho)
//        radioPessoal = findViewById(R.id.radio_pessoal)
//        btnAdicionar = findViewById(R.id.btn_adicionar)
//        rvTarefas = findViewById(R.id.rv_tarefas)

        // configurar RecyclerView
        // Adapter
        tarefaAdapter = TarefaAdapter {
            tarefaAdapter.excluirTarefa( it )
        }

        binding.rvTarefas.adapter = tarefaAdapter

        // LayoutManager
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

        binding.btnAdicionar.setOnClickListener{
            salvarTarefa()
        }

        limparFormulario()

    }

//    private fun exibirTarefas(){
//        var texto = ""
//        listaTarefas.forEach {
//            texto += "${it.titulo} - ${it.data} (${it.tipo}) \n"
//        }
//        textTarefas.text = texto
//    }




    // salvar uma tarefa
    private fun salvarTarefa(){
        if(validarCampos()){
            // MutableList (Tarefa)
            with(binding) {
                val titulo = editTitulo.text.toString()
                val data = editData.text.toString()
                val tipo = if(rbTrabalho.isChecked) "Trabalho" else "Pessoal"
                val tarefa = Tarefa(titulo, data, tipo)

                tarefaAdapter.adicionarTarefa(tarefa)
//            listaTarefas.add(tarefa)
//            exibirTarefas()
                limparFormulario()
            }

        }
    }

    private fun validarCampos(): Boolean{

        if(binding.editTitulo.text.isEmpty()){
            exibirMensagem("Preencha com um título para a tarefa!")
            return false
        }

        if(binding.editData.text.isEmpty()){
            exibirMensagem("Preencha com uma data para a tarefa!")
            return false
        }

        if(!binding.rbTrabalho.isChecked && !binding.rbPessoal.isChecked){
            exibirMensagem("Marque o tipo da tarefa!")
            return false
        }
        return true
    }

    private fun exibirMensagem(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

    private fun limparFormulario(){
        with(binding) {
            editTitulo.setText("")
            editData.setText("")
            rgTipo.clearCheck()
        }

    }

}