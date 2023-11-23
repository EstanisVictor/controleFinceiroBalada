package com.financas.Database

class EsquemaDB {
    companion object{
        val NOME_BD:String = "bd_balada"
        val VERSAO:Int = 1
    }

    object tabela_produto{
        val NOME_TABELA = "produto"
        val ID_ATT = "id"
        val NOME_ATT = "nome"
        val VALOR_ATT = "valor"
        val TIPO_ATT = "tipo"
        val DATA_CADASTRO_ATT = "data_cadastro"
        val CRIA_TABELA = "CREATE TABLE IF NOT EXISTS $NOME_TABELA " +
                "($ID_ATT INTEGER primary key autoincrement, " +
                "$NOME_ATT text, $VALOR_ATT double, $TIPO_ATT text, $DATA_CADASTRO_ATT text)"
    }
}