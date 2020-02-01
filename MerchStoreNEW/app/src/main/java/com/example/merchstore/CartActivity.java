package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private DecimalFormat m_doubleFormat = new DecimalFormat("00.00");
    private  double m_cost = 00.00;
    private Bundle m_bundle;
    private int m_selectedItem = 1;
    private Button upButton, downButton, rmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        upButton = findViewById(R.id.upItemButton);
        downButton = findViewById(R.id.downItemButton);
        rmButton = findViewById(R.id.rmItemButton);

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
        setCart(m_selectedItem);
    }

    public void setCart(int selectedItem)
    {
        String str = "";
        String key = "";
        int count = m_bundle.getInt("COUNTBUNDLE");
        TextView cart = findViewById(R.id.cartText);
        if(count!=0) {
            for (int i = 1; i < count + 1; i++) {
                key = "Item" + (i);
                Item item = m_bundle.getParcelable(key);
                if (i == selectedItem) {
                    str += (i + ". " + item.toString() + "◄\n");
                }
            else
                {
                    str += (i + ". " + item.toString() + "\n");
                }
        }
            cart.setText(str);
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            rmButton.setEnabled(true);
        }
        else
        {
            cart.setText("Cart is Empty");
            m_selectedItem = 1;
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            rmButton.setEnabled(false);
        }
    }

    public void updatePrice()
    {
        m_cost = Math.abs(m_cost);
        TextView price = findViewById(R.id.cartPriceText);
        m_cost = Double.parseDouble(m_doubleFormat.format(m_cost));
        price.setText(String.format(m_format.format(m_cost)));
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }

    public void clearCart(View view) {
        m_bundle = new Bundle();
        m_bundle.putDouble("GRANDCOST", 0.00);
        m_bundle.putInt("COUNTBUNDLE",0);
        m_cost = m_bundle.getDouble("GRANDCOST", 00.00);
        setCart(0);
        updatePrice();
    }

    public void itemUp(View view) {
        if(m_selectedItem > 1)
        {
            m_selectedItem--;
            setCart(m_selectedItem);
        }
    }

    public void itemRm(View view) {
        ArrayList<Item> items = new ArrayList<Item>();
        int count = m_bundle.getInt("COUNTBUNDLE");
        String keyGet = "";
        String keySet = "";
        if(count!=0) {
            for (int i = 1; i < count + 1; i++) {
                keyGet = "Item" + (i);
                Item item = m_bundle.getParcelable(keyGet);
                items.add(item);
            }
            count--;
            m_cost = m_cost-items.get(m_selectedItem-1).getPrice();
            m_bundle = new Bundle();
            m_bundle.putDouble("GRANDCOST",m_cost);
            m_bundle.putInt("COUNTBUNDLE",count);
            items.remove(m_selectedItem-1);
            for (int i = 1; i < count+1; i++) {
                keySet = "Item" + (i);
                Item item = items.get(i-1);
                m_bundle.putParcelable(keySet,item);
            }
            if (m_selectedItem > count)
            {m_selectedItem = count;}
            setCart(m_selectedItem);
            updatePrice();
        }
    }

    public void itemDown(View view) {
        int count = m_bundle.getInt("COUNTBUNDLE");
        if(m_selectedItem < count)
        {
            m_selectedItem++;
            setCart(m_selectedItem);
        }
    }
}
