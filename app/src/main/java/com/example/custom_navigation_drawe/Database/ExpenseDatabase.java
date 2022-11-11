package com.example.custom_navigation_drawe.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.custom_navigation_drawe.Model.ExpenseData;

@Database(entities = {ExpenseData.class}, version = 1,exportSchema = false)
public abstract class ExpenseDatabase extends RoomDatabase {

    //Create database instance
    private static volatile ExpenseDatabase INSTANCE;
    private static String DATABASE_NAME = "ExpenseDatabase";

    public synchronized static ExpenseDatabase getInstance( Context context) {
        if (INSTANCE == null) {
            //Initialize Database
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , ExpenseDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;

    }
    //Create Dao
    public abstract ExpenseDao expenseDao();

}
