package com.example.custom_navigation_drawe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.custom_navigation_drawe.Adapter.ExpenseAdapter;
import com.example.custom_navigation_drawe.Database.ExpenseDatabase;
import com.example.custom_navigation_drawe.Model.ExpenseData;

import java.util.ArrayList;
import java.util.List;

public class Expense_list_Fragment extends Fragment {

    private List<ExpenseData> expenseDataList;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private ExpenseDatabase expenseDatabase;

    ImageView imageView_btn_edit;
    ImageView imageView_btn_delete;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_list_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView_Id);
        expenseDataList = new ArrayList<>();

        // initialize database
        expenseDatabase = ExpenseDatabase.getInstance(getActivity());
        expenseDataList = expenseDatabase.expenseDao().getAllExpenseData();

        setAdapter();

    }

    private void setAdapter() {
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(getActivity(), expenseDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(expenseAdapter);

        //For adepter to fragment data pass
        expenseAdapter.setOnClick(new UpdateFragmentCommunication() {
            @Override
            public void respond(int position, ExpenseData expenseData) {
                Fragment fragment = new UpdateFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("DATA",expenseData);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frameLayout,fragment).commit();
            }
        });
    }


}