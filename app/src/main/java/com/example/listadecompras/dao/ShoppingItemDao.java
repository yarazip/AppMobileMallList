package com.example.listadecompras.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listadecompras.model.ShoppingItem;

import java.util.List;

@Dao
public interface ShoppingItemDao {

    @Insert
    void insert(ShoppingItem item);

    @Update
    void update(ShoppingItem item);

    @Delete
    void delete(ShoppingItem item);

    @Query("SELECT * FROM shopping_items")
    List<ShoppingItem> getAll();

    @Query("SELECT * FROM shopping_items WHERE categoria = :categoria")
    List<ShoppingItem> getByCategoria(String categoria);
}
