package com.financas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.financas.Controller.ProdutoController
import com.financas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding:ActivityMainBinding
    lateinit var produtoController: ProdutoController
    var tipo:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        produtoController = ProdutoController(baseContext)

        configurarEventos()
    }

    private fun configurarEventos(){
        binding.cadastroBtn.setOnClickListener(this)
        binding.resumoBtn.setOnClickListener(this)

        binding.radioEntrada.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked){
                tipo = "Entrada"
            }
        }

        binding.radioSaida.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked){
                tipo = "Saída"
            }
        }

    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.cadastroBtn.id -> executarCadastro()
            binding.resumoBtn.id -> transicaoTela()
        }
    }

    private fun transicaoTela(){
        val transicaoResumo: Intent = Intent(baseContext, ConsultActivity::class.java)
        startActivity(transicaoResumo)
    }

    private fun executarCadastro(){
        if(produtoController.saveProducts(binding.nomeTxt.text.toString(),
                binding.valorTxt.text.toString().toDouble(),
                tipo,
                binding.dataTxt.text.toString())){
            //sucesso no cadastro
            Toast.makeText(baseContext, "Cadastro feito com sucesso", Toast.LENGTH_LONG).show()
            binding.nomeTxt.setText("")
            binding.valorTxt.setText("")
            binding.dataTxt.setText("")
            binding.radioEntrada.isChecked = false
            binding.radioSaida.isChecked = false
        }else{
            //erro no cadastro
            Toast.makeText(baseContext, "erro no cadastro", Toast.LENGTH_LONG).show()
        }
    }

}