package com.example.listadecompras.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadecompras.R;
import com.example.listadecompras.dao.ShoppingItemDao;
import com.example.listadecompras.model.ShoppingItem;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private List<ShoppingItem> lista;
    private ShoppingItemDao dao;

    public ShoppingAdapter(List<ShoppingItem> lista, ShoppingItemDao dao) {
        this.lista = lista;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingItem item = lista.get(position);
        holder.textViewNome.setText(item.getNome());
        holder.textViewCategoria.setText(item.getCategoria());
        holder.checkBox.setChecked(item.isComprado());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setComprado(isChecked);
            dao.update(item);
        });

        holder.btnExcluir.setOnClickListener(v -> {
            dao.delete(item);
            lista.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, lista.size());
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome, textViewCategoria;
        CheckBox checkBox;
        Button btnExcluir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewCategoria = itemView.findViewById(R.id.textViewCategoria);
            checkBox = itemView.findViewById(R.id.checkBox);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
