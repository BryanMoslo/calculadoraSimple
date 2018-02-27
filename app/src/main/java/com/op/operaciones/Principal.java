package com.op.operaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    private EditText numberA, numberB;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        numberA = findViewById(R.id.txtNumberA);
        numberB = findViewById(R.id.txtNumberB);
        result = findViewById(R.id.result);

    }

    public void calculate(View v){
        double num1, num2, res;

        if(numberA.getText().toString().isEmpty()){
            numberA.requestFocus();
            numberA.setError(getString(R.string.error_number_one));
        }else if(numberB.getText().toString().isEmpty()){
            numberB.requestFocus();
            numberB.setError(getString(R.string.error_number_two));
        }else{
            num1 = Double.parseDouble(numberA.getText().toString());
            num2 = Double.parseDouble(numberB.getText().toString());

            res = Methods.add(num1, num2);
            result.setText("" + res);
        }
    }

    public void clear(View v){
        numberA.setText("");
        numberB.setText("");
        result.setText("");
        numberA.requestFocus();
    }
}
