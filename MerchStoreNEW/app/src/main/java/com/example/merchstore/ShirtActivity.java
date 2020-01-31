package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShirtActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;
    private Spinner tColor;
    private Spinner tSize;
    private Spinner lColor;
    private Spinner lSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);

        tColor = findViewById(R.id.tColorSpinner);
        setTColorSpinner();
        lColor = findViewById(R.id.lColorSpinner);
        setLColorSpinner();
        tSize = findViewById(R.id.tSizeSpinner);
        lSize = findViewById(R.id.lSizeSpinner);
        setSizeSpinners();

        Intent intent = getIntent();
        if(intent != null)
        {
            m_bundle = intent.getBundleExtra("MAINCARTBUNDLE");
            if(m_bundle!=null) {
                m_cost = m_bundle.getDouble("GRANDCOST", 00.00);
            }
            else
            {
                m_bundle = new Bundle();
            }
        }
        updatePrice();

    }

    public void goBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }

    public void addTee(View view) {
        m_cost += 14.99;
        int key =  m_bundle.getInt("COUNTBUNDLE")+1;
        m_bundle.putParcelable("Item" + m_bundle.getInt(key+""),new Item("Tee",tColor.getSelectedItem().toString(),tSize.getSelectedItem().toString(),59.99));
        m_bundle.putInt("COUNTBUNDLE", m_bundle.getInt("COUNTBUNDLE")+1);
        updatePrice();
    }

    public void addLong(View view) {
        m_cost += 19.99;
        int key =  m_bundle.getInt("COUNTBUNDLE")+1;
        m_bundle.putParcelable("Item" + m_bundle.getInt(key+""),new Item("Long",lColor.getSelectedItem().toString(),lSize.getSelectedItem().toString(),59.99));
        m_bundle.putInt("COUNTBUNDLE", m_bundle.getInt("COUNTBUNDLE")+1);
        updatePrice();
    }

    public  void setTColorSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Black");
        arrayList.add("Grey");
        arrayList.add("White");
        arrayList.add("Red");
        arrayList.add("Blue");
        arrayList.add("Maroon");
        arrayList.add("Olive");
        arrayList.add("Cream");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tColor.setAdapter(arrayAdapter);
    }

    public  void setLColorSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Black");
        arrayList.add("Grey");
        arrayList.add("White");
        arrayList.add("Red");
        arrayList.add("Blue");
        arrayList.add("Maroon");
        arrayList.add("Olive");
        arrayList.add("Cream");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lColor.setAdapter(arrayAdapter);
    }

    public  void setSizeSpinners()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("XS");
        arrayList.add("S");
        arrayList.add("M");
        arrayList.add("L");
        arrayList.add("XL");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tSize.setAdapter(arrayAdapter);
        lSize.setAdapter(arrayAdapter);
    }

    public void updatePrice()
    {
        m_bundle.putDouble("GRANDCOST" , m_cost);
        TextView price = findViewById(R.id.shirtPriceText);
        price.setText(String.format(m_format.format(m_cost)));
    }
}
