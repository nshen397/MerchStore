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

public class JacketActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private DecimalFormat m_doubleFormat = new DecimalFormat("00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;
    private Spinner sColor;
    private Spinner sSize;
    private Spinner hColor;
    private Spinner hSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jacket);

        sColor = findViewById(R.id.sColorSpinner);
        setsColorSpinner();
        hColor = findViewById(R.id.hColorSpinner);
        sethColorSpinner();
        sSize = findViewById(R.id.sSizeSpinner);
        hSize = findViewById(R.id.hSizeSpinner);
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

    public  void setsColorSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Black");
        arrayList.add("Grey");
        arrayList.add("White");
        arrayList.add("Red");
        arrayList.add("Blue");
        arrayList.add("Tan");
        arrayList.add("Maroon");
        arrayList.add("Cream");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sColor.setAdapter(arrayAdapter);
    }

    public  void sethColorSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Black");
        arrayList.add("Grey");
        arrayList.add("White");
        arrayList.add("Red");
        arrayList.add("Blue");
        arrayList.add("Tan");
        arrayList.add("Maroon");
        arrayList.add("Cream");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hColor.setAdapter(arrayAdapter);
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
        sSize.setAdapter(arrayAdapter);
        hSize.setAdapter(arrayAdapter);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }

    public void addSweat(View view) {
        m_cost += 44.99;
        String key =  "Item" + (m_bundle.getInt("COUNTBUNDLE")+1);
        m_bundle.putParcelable(key,new Item("Sweatshirt",sColor.getSelectedItem().toString(),sSize.getSelectedItem().toString(),44.99));
        m_bundle.putInt("COUNTBUNDLE", m_bundle.getInt("COUNTBUNDLE")+1);
        updatePrice();
    }

    public void addHoodie(View view) {
        m_cost += 49.99;
        String key =  "Item" + (m_bundle.getInt("COUNTBUNDLE")+1);
        m_bundle.putParcelable(key,new Item("Hoodie",hColor.getSelectedItem().toString(),hSize.getSelectedItem().toString(),49.99));
        m_bundle.putInt("COUNTBUNDLE", m_bundle.getInt("COUNTBUNDLE")+1);
        updatePrice();
    }

    public void updatePrice()
    {
        m_bundle.putDouble("GRANDCOST" , m_cost);
        TextView price = findViewById(R.id.jacketPriceText);
        m_cost = Double.parseDouble(m_doubleFormat.format(m_cost));
        price.setText(String.format(m_format.format(m_cost)));
    }
}
