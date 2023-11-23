package com.financas.Repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.financas.Database.EsquemaDB
import com.financas.Database.MySQLiteDB
import com.financas.Model.Produto

class ProdutoRepo(var context: Context) {
    lateinit var database:MySQLiteDB
    lateinit var conection: SQLiteDatabase

    init {
        this.database = MySQLiteDB(this.context)
        this.conection = this.database.writableDatabase
    }

    fun saveProducts(produto: Produto):Boolean{
        val value: ContentValues = ContentValues()

        value.put(EsquemaDB.tabela_produto.NOME_ATT, produto.nome)
        value.put(EsquemaDB.tabela_produto.VALOR_ATT, produto.valor)
        value.put(EsquemaDB.tabela_produto.TIPO_ATT, produto.tipo)
        value.put(EsquemaDB.tabela_produto.DATA_CADASTRO_ATT, produto.dataCadastro.toString())

        return this.conection.insert(EsquemaDB.tabela_produto.NOME_TABELA, null, value) > 0
    }

    fun getProductByType(type:String):ArrayList<Produto>{
        val tuplasBanco:Cursor = this.conection.query(
            EsquemaDB.tabela_produto.NOME_TABELA, arrayOf(EsquemaDB.tabela_produto.NOME_ATT,
                EsquemaDB.tabela_produto.VALOR_ATT,
                EsquemaDB.tabela_produto.TIPO_ATT,
                EsquemaDB.tabela_produto.DATA_CADASTRO_ATT),
            "tipo=?", arrayOf(type), null, null, null)

        val list = ArrayList<Produto>()

        while(tuplasBanco.moveToNext()){
            list.add(Produto(tuplasBanco.getString(0),
                tuplasBanco.getDouble(1),
                tuplasBanco.getString(2),
                tuplasBanco.getLong(3)))
        }

        return list
    }
}