package com.example.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.listadecompras.adapter.ShoppingAdapter;
import com.example.listadecompras.dao.ShoppingItemDao;
import com.example.listadecompras.database.AppDatabase;
import com.example.listadecompras.model.ShoppingItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private ShoppingItemDao dao;
    private Button btnCategoriaProdutoLimpeza, btnCategoriaCarne, btnCategoriaHortifrut, btnCategoriaNaoPereciveis;
    private List<ShoppingItem> listaItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "shopping_db")
                .allowMainThreadQueries()
                .build();

        dao = db.shoppingItemDao();


        btnCategoriaProdutoLimpeza = findViewById(R.id.btnCategoriaProdutoLimpeza);
        btnCategoriaCarne = findViewById(R.id.btnCategoriaCarne);
        btnCategoriaHortifrut = findViewById(R.id.btnCategoriaHortifrut);
        btnCategoriaNaoPereciveis = findViewById(R.id.btnCategoriaNaoPereciveis);

        // Botões categorias - abrem CategoriaActivity
        btnCategoriaProdutoLimpeza.setOnClickListener(v -> abrirCategoria("Produto de Limpeza"));
        btnCategoriaCarne.setOnClickListener(v -> abrirCategoria("Carne"));
        btnCategoriaHortifrut.setOnClickListener(v -> abrirCategoria("Hortifrut"));
        btnCategoriaNaoPereciveis.setOnClickListener(v -> abrirCategoria("Não Perecíveis"));

    }

    private void abrirCategoria(String categoria) {
        Intent intent = new Intent(MainActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", categoria);
        startActivity(intent);
    }

}
