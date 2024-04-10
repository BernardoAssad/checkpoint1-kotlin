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
        O método onCreate é chamado quando a atividade é criada pela primeira vez ou quando ela é restaurada a
        partir de um estado salvo anteriormente.

        O parâmetro savedInstanceState é um objeto Bundle opcional que pode conter dados salvos anteriormente
        sobre o estado da atividade. Se a atividade foi destruída e recriada devido a uma mudança de configuração
        do dispositivo (como uma mudança de orientação), esse objeto Bundle contém os dados salvos do estado anterior
        da atividade. A atividade pode usar esses dados para restaurar seu estado anterior e continuar a execução
        como se nada tivesse mudado.

        Se a atividade não foi destruída e recriada, o parâmetro savedInstanceState será nulo. Nesse caso, a atividade
        pode ignorar esse parâmetro e continuar a ser criada normalmente.

        A chamada super.onCreate(savedInstanceState) é necessária para garantir que a atividade seja criada corretamente.
        Ela chama o método onCreate da superclasse AppCompatActivity e passa o parâmetro savedInstanceState para ele.

        O AppCompatActivity é uma superclasse para atividades que desejam usar os recursos de design do Material Design.
        Nesse caso, o layout padrão da aplicação, é dele.
**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

/**
    * O método onResume é acionado quando a aplicação retoma. Basicamente, ele configura o RecyclerView com a lista de produtos e define o comportamento
 * do FloatingActionButton.
 *
 * RecyclerView é um widget de lista que permite exibir dados em lista ou grade. É amplamente utilizado em listas grandes de forma eficiente, pois ele recicla
 * os itens da lista à medida que eles são deslocados para fora da tela, evitando a criação desnecessária de novos itens.
**/

    override fun onResume() {
        super.onResume()
        // Inicializa 2 variáveis para poder utilizar o RecyclerView e o DAO para manipular os produtos
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = ProdutosDao()

        // Envia uma mensagem de log com os produtos encontrados no DAO
        Log.i("FormularioProduto", "onCreate: ${dao.buscaTodos()}")

        // Aqui, o código irá definir o adaptador do RecyclerView com a lista de produtos
        // O adaptador é responsável por fornecer os itens a serem exibidos no RecyclerView e é passado como um parâmetro para o RecyclerView.adapter
        recyclerView.adapter = ListaProdutosAdapter(
            context = this,
            produtos = dao.buscaTodos()
        )

         // Configura o FloatingActionButton para abrir a atividade de formulário ao ser clicado
        // Obtem o botão pelo id definido no xml
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        // Adiciona um 'ouvinte' para ver quando vai ser chamado
        fab.setOnClickListener {
            // Declara a intenção de abrir o formulario
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            // Inicia a atividade, nesse caso o formulario
            startActivity(intent)
        }
    }
}