package com.example.custom_navigation_drawe.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "expenseData", indices = @Index(value = {"id"},unique = true))
public class ExpenseData implements Parcelable {

    // create id Column
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Create Expense Time
    @ColumnInfo(name = "time")
    private String time;

    // Create Expense date
    @ColumnInfo(name = "date")
    private String date;

    // Create Expense text
    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "ammount")
    private float ammount;

    public ExpenseData(String time, String date, String text, float ammount) {
        this.time = time;
        this.date = date;
        this.text = text;
        this.ammount = ammount;
    }

    protected ExpenseData(Parcel in) {
        id = in.readInt();
        time = in.readString();
        date = in.readString();
        text = in.readString();
        ammount = in.readFloat();
    }

    public static final Creator<ExpenseData> CREATOR = new Creator<ExpenseData>() {
        @Override
        public ExpenseData createFromParcel(Parcel in) {
            return new ExpenseData(in);
        }

        @Override
        public ExpenseData[] newArray(int size) {
            return new ExpenseData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(text);
        dest.writeFloat(ammount);
    }

    @Override
    public String toString() {
        return "ExpenseData{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", ammount=" + ammount +
                '}';
    }
}
