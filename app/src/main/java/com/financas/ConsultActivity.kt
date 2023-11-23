package com.financas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.financas.Controller.ProdutoController
import com.financas.databinding.ActivityConsultaBinding

class ConsultActivity : AppCompatActivity() {
    lateinit var binding: ActivityConsultaBinding
    lateinit var produtoController: ProdutoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        produtoController = ProdutoController(baseContext)

        carregarProdutos()
    }

    fun carregarProdutos(){
        val entradas = produtoController.getProductByType("Entrada")
        val saidas = produtoController.getProductByType("Saída")

        var somaEntrada: Double = 0.0
        var somaSaida: Double = 0.0

        if(entradas != null){

            for (produto in entradas){
                somaEntrada += produto.valor
            }
            binding.valorCaixaLabelTxt.text = ("R$ $somaEntrada")
        }else{
            binding.valorCaixaLabelTxt.text = ("R$ 0.0")
        }

        if(saidas != null){

            for (produto in saidas){
                somaSaida += produto.valor
            }
            binding.valorGastoLabelTxt.text = ("R$ $somaSaida")
        }else{
            binding.valorGastoLabelTxt.text = ("R$ 0.0")
        }

        binding.valorTotalLabelTxt.text = ("R$ ${somaEntrada - somaSaida}")
    }
}