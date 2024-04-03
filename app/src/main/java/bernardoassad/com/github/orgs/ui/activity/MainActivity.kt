/**
    * MainActivity é a atividade principal da aplicação. Nesse caso, ela irá exibir uma lista de produtos e fornecer a feature para adicionar novos produtos.
**/

package bernardoassad.com.github.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import bernardoassad.com.github.orgs.R
import bernardoassad.com.github.orgs.dao.ProdutosDao
import bernardoassad.com.github.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {

/**
     * Quando a atividade é criada, ele inicia o método onCreate. Sua função é inicializar os componentes da interface do usuário. 
     * O parametro savedInstanceState é o estado da instância anterior da atividade, se disponível.
**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

/**
    * O método onResume é acionado quando a aplicação retoma. Basicamente, ele configura o RecyclerView com a lista de produtos e define o comportamento do FloatingActionButton.
**/

    override fun onResume() {
        super.onResume()
        // Inicializa 2 variáveis para poder utilizar o RecyclerView e o DAO para manipular os produtos
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = ProdutosDao()

        // Envia uma mensagem de log com os produtos encontrados no DAO
        Log.i("FormularioProduto", "onCreate: ${dao.buscaTodos()}")

        // Aqui, o código irá definir o adaptador do RecyclerView com a lista de produtos
        recyclerView.adapter = ListaProdutosAdapter(
            context = this,
            produtos = dao.buscaTodos()
        )

         // Configura o FloatingActionButton para abrir a atividade de formulário ao ser clicado
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}