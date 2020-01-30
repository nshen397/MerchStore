package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PantActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant);
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
//        updatePrice();

    }

    public void goBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }

    public void addJean(View view) {
        m_cost += 54.99;
        m_bundle.putInt("JCOUNTBUNDLE" , m_bundle.getInt("JCOUNTBUNDLE") +1);
        updatePrice();
    }

    public void addChino(View view) {
        m_cost += 59.99;
        m_bundle.putInt("CCOUNTBUNDLE" , m_bundle.getInt("CCOUNTBUNDLE") +1);
        updatePrice();
    }

    public void updatePrice()
    {
        m_bundle.putDouble("GRANDCOST" , m_cost);
        TextView price = findViewById(R.id.pantPriceText);
        price.setText(String.format(m_format.format(m_cost)));
    }
}
