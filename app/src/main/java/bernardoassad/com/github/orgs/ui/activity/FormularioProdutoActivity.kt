/**
 * FormularioProdutoActivity é a atividade que permite os usuários adicionarem novos produtos.
 * Os usuários podem preencher campos, nesse caso definidos por nome, descrição, valor e fornecedor do produto
 * e ainda conseguem salvar esses campos. Além disso, também tem uma validação dos dados. Utilizando
 * a biblioteca Toast para ter as notificações.
 */


package bernardoassad.com.github.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bernardoassad.com.github.orgs.R
import bernardoassad.com.github.orgs.dao.ProdutosDao
import bernardoassad.com.github.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    /**
     * O método onCreate é chamado quando a atividade é iniciada. Ele inicializa os componentes
     * da interface do usuário e configura o botão de salvar para adicionar um novo produto. O
     * parametro savedInstanceState é o estado da instância anterior da atividade.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)

        // Configura o botão de salvar para adicionar um novo produto quando clicado
        botaoSalvar.setOnClickListener {
            if (validaFormulario()) {
                val campoNome = findViewById<EditText>(R.id.nome)
                val campoDescricao = findViewById<EditText>(R.id.descricao)
                val campoValor = findViewById<EditText>(R.id.valor)
                val campoFornecedor = findViewById<EditText>(R.id.fornecedor)

                /*
                Converte o valor em texto para um objeto BigDecimal, isso ocorre porque
                tipo de dado do campo de entrada de texto em Android é String. Quando temos
                o texto do campo de entrada usando o método text.toString(), estamos explicitamente
                convertendo o objeto de texto em uma string. Nesse caso, o usuário insere um número,
                mas o método text.toString() ainda converte esse número em uma string.
                Em suma, estamos pegando um objeto de entrada de texto, convertendo em string para
                recebermos o valor dele, e abaixo vira BigDecimal
                */

                val nome = campoNome.text.toString()
                val descricao = campoDescricao.text.toString()
                val valorEmTexto = campoValor.text.toString()
                val fornecedor = campoFornecedor.text.toString()

                /* Abaixo, estamos transformando o valor que transformamos em String para
                Big Decimal
                */
                val valor = if (valorEmTexto.isBlank()) {
                    BigDecimal.ZERO
                } else {
                    BigDecimal(valorEmTexto)
                }

                // Criando a saída do produto cadastrado
                val produtoNovo = Produto(
                    nome = "Nome: $nome",
                    descricao = "Descrição: $descricao",
                    valor = valor,
                    fornecedor = "Fornecedor: $fornecedor"
                )

                // Log para garantir se o objeto Produto está sendo criado corretamente
                Log.i("FormularioProduto", "onCreate: $produtoNovo")
                // Instanciando a classe ProdutosDAO que gerencia os dados da aplicação
                val dao = ProdutosDao()
                // Adicionando o produto, por meio do método adiciona
                dao.adiciona(produtoNovo)
                // Log para garantir se o produto foi adicionado corretamente
                Log.i("FormularioProduto", "onCreate: ${dao.buscaTodos()}")

                exibeMensagemDeConfirmacao()
                // Finish é usada para fechar a atividade atual e voltar para a atividade anterior
                // na pilha de atividades do aplicativo. Quando a função finish() é chamada, a
                // atividade atual é destruída e o controle é devolvido para a atividade anterior.
                finish()
            }
        }
    }

    /*
    Esse método é chamado validaFormulario e retorna um booleano. Ele é usado para validar os
    campos do formulário de cadastro de produto. Ele procura os campos de nome, descrição, valor
     e fornecedor e verifica se eles estão vazios. Se algum deles estiver vazio, ele exibe uma
     mensagem de erro usando a classe Toast (classe de notificações) e retorna false. Se todos os
     campos estiverem preenchidos, ele retorna true. Isso é útil para verificar se o formulário
     foi preenchido corretamente antes de tentar salvar os dados no banco de dados.
     */
    private fun validaFormulario(): Boolean {
        val campoNome = findViewById<EditText>(R.id.nome)
        val campoDescricao = findViewById<EditText>(R.id.descricao)
        val campoValor = findViewById<EditText>(R.id.valor)
        val campoFornecedor = findViewById<EditText>(R.id.fornecedor)

        val nome = campoNome.text.toString()
        val descricao = campoDescricao.text.toString()
        val valorEmTexto = campoValor.text.toString()
        val fornecedor = campoFornecedor.text.toString()

        if (nome.isEmpty() || descricao.isEmpty() || valorEmTexto.isEmpty() || fornecedor.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    // Método simples para exibir que o produto foi cadastrado com sucesso
    private fun exibeMensagemDeConfirmacao() {
        Toast.makeText(this, "Produto salvo com sucesso.", Toast.LENGTH_SHORT).show()
    }

}