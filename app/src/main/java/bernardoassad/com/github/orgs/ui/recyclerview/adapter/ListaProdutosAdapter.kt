/**
    * ListaProdutosAdapter é um adaptador para exibir uma lista de produtos em um RecyclerView.
    * Este adaptador é responsável por criar e gerenciar o layout de item de produto e vincular os dados do produto aos itens.
**/

package bernardoassad.com.github.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bernardoassad.com.github.orgs.R
import bernardoassad.com.github.orgs.model.Produto

class ListaProdutosAdapter(

    private val context: Context,
    private val produtos: List<Produto>

     /**
     * Construtor para o adaptador ListaProdutosAdapter que usa como parametro o context, no caso contexto da aplicação. E também usa a Lista de
     * Produtos que será exibida.
     *
     * A classe ListaProdutosAdapter é usada para exibir uma lista de itens de produto em um RecyclerView. Ela gerencia a criação e reciclagem de itens
     * de visualização para cada item de produto na lista. O ViewHolder personalizado é usado para manter referências a cada view em um item de visualização,
     * o que facilita a atualização dos dados exibidos em cada item de visualização.
     *
     *
     * Basicamente, o RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() está pegando a lista de produtos e exibindo em lista, e o ViewHolder é uma
     * classe auxiliar que ajuda armazenando referências a cada view em um item de lista ou grid, o que torna mais eficiente a atualização dos
     * dados exibidos em cada item. Quando uma lista assim é exibida em Android é normal que itens sejam reciclados, o ViewHolder ajuda nisso.
     */

) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

     /**
     * ViewHolder para manter as referências de cada item de produto na lista.
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        
        /**
         * Método para vincular os dados de um produto a uma visualização de item de produto, utiliza o parametro produto que será exibido.
         */

        fun vincula(produto: Produto) {
            val nome = itemView.findViewById<TextView>(R.id.nome)
            nome.text = produto.nome

            val descricao = itemView.findViewById<TextView>(R.id.descricao)
            descricao.text = produto.descricao

            val valor = itemView.findViewById<TextView>(R.id.valor)
            valor.text = produto.valor.toPlainString()

            val fornecedor = itemView.findViewById<TextView>(R.id.fornecedor)
            fornecedor.text = produto.fornecedor
        }
    }

    /**
     * Método chamado quando o RecyclerView precisa de um novo ViewHolder para exibir um item de produto.
     * Ele infla a visualização do item de produto a partir do layout XML e retorna um novo ViewHolder.
     */

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListaProdutosAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)
        return ListaProdutosAdapter.ViewHolder(view)
    }

    /**
     * O getItemCount é chamado para determinar o número de itens na lista de produtos. Ele retorna o tamanho da lista de produtos.
     */
    override fun getItemCount(): Int = produtos.size

    /**
     * O onBindViewHolder é chamado quando o RecyclerView precisa vincular os dados de um produto a um ViewHolder. Ele obtém o produto na
     * posição especificada e chama o método vincula do ViewHolder para vincular os dados do produto à visualização do item de produto.
     */
    override fun onBindViewHolder(holder: ListaProdutosAdapter.ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }
}
