package com.example.merchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private DecimalFormat m_format = new DecimalFormat("$00.00");
    private  double m_cost = 00.00;
    private TextView price;
    private Bundle m_bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = findViewById(R.id.mainCostText);

        Intent intent = getIntent();
        if(intent != null)
        {
            m_bundle = intent.getBundleExtra("MAINCARTBUNDLE");
            if(m_bundle!=null) {
                m_cost = m_bundle.getDouble("GRANDCOST", 00.00);
            }
        }

        price.setText(m_format.format(m_cost));

    }

    public void openShirt(View view) {
        Intent intent = new Intent(this, ShirtActivity.class);
                intent.putExtra("MAINCARTBUNDLE", m_bundle);
                startActivity(intent);
    }

    public void openPant(View view) {
        Intent intent = new Intent(this, PantActivity.class);
        intent.putExtra("MAINCARTBUNDLE", m_bundle);
        startActivity(intent);
    }
}
