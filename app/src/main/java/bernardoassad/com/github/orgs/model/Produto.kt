/**
 * Produto é uma classe de modelo que representa um produto. Ele contém informações como nome, descrição, valor e fornecedor do produto. Aqui é o model da Arquitetura MVC, então 
 * salva todos os atributos necessários para o Produto. Nesse caso, os campos citados anteriormente.
**/

package bernardoassad.com.github.orgs.model

/**
    * Construtor para criar um novo produto. Com os parametros:
    * nome - nome do produto.
    * descricao - descrição do produto.
    * valor - valor do produto.
    * fornecedor - fornecedor do produto.
**/

import java.math.BigDecimal

data class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val fornecedor: String
)
