package com.example.custom_navigation_drawe.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.custom_navigation_drawe.Database.ExpenseDatabase;
import com.example.custom_navigation_drawe.MainActivity;
import com.example.custom_navigation_drawe.Model.ExpenseData;
import com.example.custom_navigation_drawe.R;
import com.example.custom_navigation_drawe.UpdateFragment;
import com.example.custom_navigation_drawe.UpdateFragmentCommunication;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private Context context;
    private List<ExpenseData> expenseDataList;
    private ExpenseDatabase expenseDatabase;
    private UpdateFragmentCommunication updateFragmentCommunication;


    public ExpenseAdapter(Context context, List<ExpenseData> expenseDataList) {
        this.context = context;
        this.expenseDataList = expenseDataList;
        expenseDatabase = ExpenseDatabase.getInstance(context);
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView txt_View_time;
        TextView txt_View_date;
        TextView txt_View_cause;
        TextView txt_View_ammount;
        ImageView imageView_menu;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_View_time = itemView.findViewById(R.id.txt_View_time);
            txt_View_date = itemView.findViewById(R.id.txt_View_date);
            txt_View_cause = itemView.findViewById(R.id.txt_View_cause);
            txt_View_ammount = itemView.findViewById(R.id.txt_View_ammount);

            imageView_menu = itemView.findViewById(R.id.imageView_menu);
        }
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_expense_list, parent, false);
        return new ExpenseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ExpenseData expenseData = expenseDataList.get(position);

        holder.txt_View_time.setText("Time: "+expenseData.getTime());
        holder.txt_View_date.setText("Date: "+expenseData.getDate());
        holder.txt_View_cause.setText("Causes: "+expenseData.getText());
        holder.txt_View_ammount.setText("Ammount: "+String.valueOf(expenseData.getAmmount()));



        holder.imageView_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popmemu
                showpopup(v, expenseData, position);
            }
        });


    }

    public void showpopup(View view, ExpenseData expenseData, int position) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_options);
        popupMenu.setOnMenuItemClickListener(item -> {

            int id = item.getItemId();

            switch (id) {
                case R.id.delete:
                    deleteClicked(expenseData, position);

                    break;

                case R.id.update:
                    updateFragmentCommunication.respond(position,expenseData);

                    break;
            }
            return false;
        });
        popupMenu.show();
    }


    public void deleteClicked(ExpenseData expenseData, int position) {
        expenseDatabase.expenseDao().deleteExpense(expenseData);
        expenseDataList.remove(position);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return expenseDataList.size();
    }

    public void setOnClick(UpdateFragmentCommunication updateFragmentCommunication){
        this.updateFragmentCommunication = updateFragmentCommunication;
    }

}
