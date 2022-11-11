package com.example.custom_navigation_drawe.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.custom_navigation_drawe.Model.ExpenseData;

import java.util.List;

@Dao
public interface ExpenseDao {

    //Insert Query
    @Insert(onConflict = REPLACE)
    void insert(ExpenseData expenseData);

    // Delete Query
    @Delete
    void deleteExpense(ExpenseData expenseData);

    // Delete All Query
//    @Delete
//    void reset(List<ExpenseData> expenseData);


    // Update Query
    @Query("UPDATE expenseData SET time = :sTime, date = :sDate, text = :sText, ammount = :sAmmount WHERE ID = :sID")
    void update(int sID, String sTime, String sDate, String sText, String sAmmount);

//    @Update
//    void updateExpense(ExpenseData expenseData);


    // Get All Data Query
    @Query("SELECT * FROM expenseData")
    List<ExpenseData> getAllExpenseData();

}
