package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ShirtActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);

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
        m_bundle.putInt("TCOUNTBUNDLE" , m_bundle.getInt("TCOUNTBUNDLE") +1);
        updatePrice();
    }

    public void addLong(View view) {
        m_cost += 19.99;
        m_bundle.putInt("LCOUNTBUNDLE" , m_bundle.getInt("LCOUNTBUNDLE") +1);
        updatePrice();
    }

    public void updatePrice()
    {
        m_bundle.putDouble("GRANDCOST" , m_cost);
        TextView price = findViewById(R.id.shirtPriceText);
        price.setText(String.format(m_format.format(m_cost)));
    }
}
