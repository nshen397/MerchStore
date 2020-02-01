package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PantActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private DecimalFormat m_doubleFormat = new DecimalFormat("00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;
    private Spinner jColor;
    private Spinner jSize;
    private Spinner cColor;
    private Spinner cSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant);

        jColor = findViewById(R.id.jColorSpinner);
        setJColorSpinner();
        cColor = findViewById(R.id.cColorSpinner);
        setCColorSpinner();
        jSize = findViewById(R.id.jSizeSpinner);
        cSize = findViewById(R.id.cSizeSpinner);
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

    public  void setJColorSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Black");
        arrayList.add("Grey");
        arrayList.add("Denim");
        arrayList.add("Tan");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jColor.setAdapter(arrayAdapter);
    }

    public  void setCColorSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Black");
        arrayList.add("Grey");
        arrayList.add("Olive");
        arrayList.add("Tan");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cColor.setAdapter(arrayAdapter);
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
        jSize.setAdapter(arrayAdapter);
        cSize.setAdapter(arrayAdapter);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }

    public void addJean(View view) {
        m_cost += 54.99;
        String key =  "Item" + (m_bundle.getInt("COUNTBUNDLE")+1);
        m_bundle.putParcelable(key,new Item("Jean",jColor.getSelectedItem().toString(),jSize.getSelectedItem().toString(),54.99));
        m_bundle.putInt("COUNTBUNDLE", m_bundle.getInt("COUNTBUNDLE")+1);
        updatePrice();
    }

    public void addChino(View view) {
        m_cost += 59.99;
        String key =  "Item" + (m_bundle.getInt("COUNTBUNDLE")+1);
        m_bundle.putParcelable(key,new Item("Chinos",jColor.getSelectedItem().toString(),jSize.getSelectedItem().toString(),59.99));
        m_bundle.putInt("COUNTBUNDLE", m_bundle.getInt("COUNTBUNDLE")+1);
        updatePrice();
    }

    public void updatePrice()
    {
        m_bundle.putDouble("GRANDCOST" , m_cost);
        TextView price = findViewById(R.id.pantPriceText);
        m_cost = Double.parseDouble(m_doubleFormat.format(m_cost));
        price.setText(String.format(m_format.format(m_cost)));
    }
}
