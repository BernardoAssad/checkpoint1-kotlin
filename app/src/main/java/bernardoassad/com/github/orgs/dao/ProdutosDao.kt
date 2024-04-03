/**
    * ProdutosDao é a classe responsável por gerenciar as operações com os dados relacionadas aos produtos.
    * Essa classe irá fornecer os métodos para adicionar produtos e recuperar todos os produtos armazenados, como se fosse os getters e setters do código.
**/

package bernardoassad.com.github.orgs.dao

import bernardoassad.com.github.orgs.model.Produto

class ProdutosDao {

    /**
         Essa função adiciona um produto na lista de produtos, o parametro Produto é o produto que será adicionado (set)
     **/
    fun adiciona(produto: Produto) {
        Companion.produtos.add(produto)
    }

    /**
        * Retorna uma lista de todos os produtos armazenados, onde o parametro List<Produto> é uma lista com todos os produtos (algo como um get mas com todos, um getAll?)
     **/
    fun buscaTodos(): List<Produto> {
        return Companion.produtos.toList()
    }

    /**
     * Aqui é um Companion object para armazenar a lista dos produtos.
     **/
    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}