package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

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
        setCart();
    }

    public void setCart()
    {
        String str = "";
        TextView cart = findViewById(R.id.cartText);
        if(m_bundle.getInt("COUNTBUNDLE")!=0) {
            for (int i = 0; i < m_bundle.getInt("COUNTBUNDLE"); i++) {
                Item item = m_bundle.getParcelable("Item" + i);
                str += i + 1 + ". " + item.toString() + "\n";
            }
            cart.setText(str);
        }
        else
        {
            cart.setText("Cart is Empty");
        }
    }

    public void updatePrice()
    {
        TextView price = findViewById(R.id.cartPriceText);
        price.setText(String.format(m_format.format(m_cost)));
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }
}
