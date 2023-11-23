package com.financas.Controller

import android.content.Context
import com.financas.Model.Produto
import com.financas.Repository.ProdutoRepo

class ProdutoController(var context: Context) {
    lateinit var repository: ProdutoRepo

    init {
        this.repository = ProdutoRepo(context)
    }

    fun saveProducts(nome:String, valor:Double, tipo:String, data:String) : Boolean{

        if(nome != null && valor != null && tipo != null && data != null){
            val produto: Produto = Produto(nome, valor, tipo, data.toLong())
            return repository.saveProducts(produto)
        }
        return false
    }

    fun getProductByType(type:String):ArrayList<Produto>{

        if (type == null){
            return ArrayList()
        }
        return repository.getProductByType(type)
    }

}