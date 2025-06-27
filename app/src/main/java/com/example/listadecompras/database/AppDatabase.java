package com.example.listadecompras.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.listadecompras.dao.ShoppingItemDao;
import com.example.listadecompras.model.ShoppingItem;

@Database(entities = {ShoppingItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoppingItemDao shoppingItemDao();
}
