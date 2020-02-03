package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CheckoutActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private DecimalFormat m_doubleFormat = new DecimalFormat("00.00");
    private  double m_cost = 00.00;
    private double m_tax = 00.00;
    private  double m_total = 00.00;
    private Bundle m_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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

    public void updatePrice()
    {
        m_bundle.putDouble("GRANDCOST" , m_cost);
        TextView subprice = findViewById(R.id.subCostText);
        TextView tax = findViewById(R.id.taxCostText);
        TextView total = findViewById(R.id.totalCostText);
        m_cost = Double.parseDouble(m_doubleFormat.format(m_cost));
        m_tax = Double.parseDouble(m_doubleFormat.format((m_cost*.2)));
        m_total = Double.parseDouble(m_doubleFormat.format((m_cost+m_tax)));
        subprice.setText(String.format(m_format.format(m_cost)));
        tax.setText(String.format(m_format.format(m_tax)));
        total.setText(String.format(m_format.format(m_total)));
    }

    public void Order(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Order Placed";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
