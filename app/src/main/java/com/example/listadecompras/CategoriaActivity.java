package com.example.listadecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadecompras.adapter.ShoppingAdapter;
import com.example.listadecompras.dao.ShoppingItemDao;
import com.example.listadecompras.database.AppDatabase;
import com.example.listadecompras.model.ShoppingItem;

import java.util.List;

public class CategoriaActivity extends AppCompatActivity {

    private AppDatabase db;
    private ShoppingItemDao dao;
    private RecyclerView recyclerView;
    private ShoppingAdapter adapter;
    private List<ShoppingItem> listaItens;
    private String categoria;
    private TextView textViewCategoria;
    private Button btnAdicionarItemCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categoria = getIntent().getStringExtra("categoria");
        if (categoria == null) {
            Toast.makeText(this, "Categoria inválida", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        textViewCategoria = findViewById(R.id.textViewCategoria);
        btnAdicionarItemCategoria = findViewById(R.id.btnAdicionarItemCategoria);
        textViewCategoria.setText("Categoria: " + categoria);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "shopping_db")
                .allowMainThreadQueries()
                .build();

        dao = db.shoppingItemDao();
        recyclerView = findViewById(R.id.recyclerView);

        carregarLista();

        btnAdicionarItemCategoria.setOnClickListener(v -> abrirDialogAdicionar());
    }

    private void carregarLista() {
        listaItens = dao.getByCategoria(categoria);
        adapter = new ShoppingAdapter(listaItens, dao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void abrirDialogAdicionar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_adicionar, null);

        EditText editNome = view.findViewById(R.id.editNome);
        // Oculta o Spinner, já que a categoria já está definida
        view.findViewById(R.id.spinnerCategoria).setVisibility(View.GONE);

        builder.setView(view);
        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String nome = editNome.getText().toString().trim();
            if (nome.isEmpty()) {
                Toast.makeText(this, "Informe o nome do produto", Toast.LENGTH_SHORT).show();
                return;
            }

            ShoppingItem item = new ShoppingItem(nome, categoria, false);
            dao.insert(item);
            carregarLista();
        });

        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }
}
