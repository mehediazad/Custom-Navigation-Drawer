package com.example.custom_navigation_drawe;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.custom_navigation_drawe.Adapter.ExpenseAdapter;
import com.example.custom_navigation_drawe.Database.ExpenseDatabase;
import com.example.custom_navigation_drawe.Model.ExpenseData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Add_Expense_Fragment extends Fragment {

    TextView textView_Select_Time;
    int hours, minutes;

    DatePickerDialog.OnDateSetListener setListener;
    TextView textView_Select_Date;

    EditText editText_expense;
    EditText editText_ammount;
    Button button_submit;


    private List<ExpenseData> expenseDataList;
    private ExpenseDatabase expenseDatabase;
    private ExpenseAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__expense_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText_expense = view.findViewById(R.id.editText_expense);
        editText_ammount = view.findViewById(R.id.editText_ammount);
        button_submit = view.findViewById(R.id.button_submit);

        expenseDataList = new ArrayList<>();

        // initialize database
        expenseDatabase = ExpenseDatabase.getInstance(getActivity());


        // time
        textView_Select_Time = view.findViewById(R.id.textView_Select_Time);

        // date
        textView_Select_Date = view.findViewById(R.id.textView_Select_Date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // time
        textView_Select_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize  time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // initialize hours and minute
                                hours = hourOfDay;
                                minutes = minute;

                                // Store Hours And Minute in string
                                String time = hours + ":" + minutes;

                                // initialize 24 hours time formate
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    // initialize 24 hours time formate
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    // Set select time on text view
                                    textView_Select_Time.setText(f12Hours.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, 12, 0, false

                );
                // set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // display previous select time
                timePickerDialog.updateTime(hours, minutes);

                //show dialog
                timePickerDialog.show();

            }
        });

        // date
        textView_Select_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                textView_Select_Date.setText(date);
            }
        };

        // Submite Button
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeText = textView_Select_Time.getText().toString().trim();
                String dateText = textView_Select_Date.getText().toString().trim();
                String expenseText = editText_expense.getText().toString().trim();
                String ammountText = editText_ammount.getText().toString().trim();

                if (timeText.equals("")) {
                    // Whene Emty field
                    Toast.makeText(getActivity(), "required Time", Toast.LENGTH_SHORT).show();

                } else if (dateText.equals("")) {
                    // Whene Emty field
                    Toast.makeText(getActivity(), "required Date", Toast.LENGTH_SHORT).show();

                } else if (expenseText.equals("")) {
                    // Whene Emty field

                    Toast.makeText(getActivity(), "required Causes", Toast.LENGTH_SHORT).show();

                } else if (ammountText.equals("")) {
                    // Whene Emty field
                    Toast.makeText(getActivity(), "required Ammount", Toast.LENGTH_SHORT).show();
                } else {
                    ExpenseData expenseData = new ExpenseData(timeText, dateText, expenseText, Float.parseFloat(ammountText));
                    expenseDatabase.expenseDao().insert(expenseData);

                    textView_Select_Time.setText("");
                    textView_Select_Date.setText("");
                    editText_expense.setText("");
                    editText_ammount.setText("");
                    expenseDataList.clear();

                }
            }
        });

    }

}