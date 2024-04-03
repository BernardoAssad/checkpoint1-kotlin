/**
    * FormularioProdutoActivity é a atividade que permite os usuários adicionarem novos produtos. Os usuários podem preencher campos, nesse caso definidos por
    * nome, descrição, valor e fornecedor do produto e ainda conseguem salvar esses campos.
**/


package bernardoassad.com.github.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import bernardoassad.com.github.orgs.R
import bernardoassad.com.github.orgs.dao.ProdutosDao
import bernardoassad.com.github.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    /**
     * O método onCreate é chamado quando a atividade é iniciada. Ele inicializa os componentes da interface do usuário e configura o botão de salvar para adicionar um novo produto.
     * O parametro savedInstanceState é o estado da instância anterior da atividade.
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)

        botaoSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.nome)
            val campoDescricao = findViewById<EditText>(R.id.descricao)
            val campoValor = findViewById<EditText>(R.id.valor)
            val campoFornecedor = findViewById<EditText>(R.id.fornecedor)

            val nome = campoNome.text.toString()
            val descricao = campoDescricao.text.toString()
            val valorEmTexto = campoValor.text.toString()
            val fornecedor = campoFornecedor.text.toString()

            val valor = if (valorEmTexto.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorEmTexto)
            }

            val produtoNovo = Produto(
                nome = nome,
                descricao = descricao,
                valor = valor,
                fornecedor = fornecedor
            )

            Log.i("FormularioProduto", "onCreate: $produtoNovo")
            val dao = ProdutosDao()
            dao.adiciona(produtoNovo)
            Log.i("FormularioProduto", "onCreate: ${dao.buscaTodos()}")

            finish()
        }
    }
}