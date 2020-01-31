package com.example.merchstore;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Item implements Parcelable
{
    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private String m_type;
    private String m_color;
    private String m_size;
    private double m_price;

    public Item(String type, String color, String size,double price)
    {
        m_type = type;
        m_color = color;
        m_size = size;
        m_price = price;
    }

    public Item()
    {}

    public String  getType()
    {return m_type;}

    public String getColor()
    {return m_color;}

    public  String getSize()
    {return m_size;}

    public double getPrice()
    {return m_price;}

    public String getParcelString()
    {return m_type + "," + m_color + "," + m_size + "," + m_price;}

    //Parable interface methods
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.getParcelString());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            String parcel = in.readString();
            String type = "";
            String color = "";
            String size = "";
            double price = 0.0;

            for(int i = 0; i<parcel.length()-1;i++)
            {}
            return new Item();
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public  String toString()
    {return m_color + " " + m_type + "Size: " + m_size + " (" + m_format.format(m_price) + ")";}
}
