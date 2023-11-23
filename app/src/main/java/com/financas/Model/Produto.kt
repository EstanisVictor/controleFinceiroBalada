package com.financas.Model

import java.time.LocalDate


data class Produto(var nome:String, var valor:Double, var tipo:String) {
    var dataCadastro:LocalDate? = null

    constructor(nome:String, valor:Double, tipo:String, dataCadastro:Long):this(nome, valor, tipo){
        this.dataCadastro = LocalDate.ofEpochDay(dataCadastro)
    }
}