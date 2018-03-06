package com.op.operaciones;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    private EditText numberA, numberB;
    private TextView result;
    private Resources resources;
    private Spinner operations;
    private Button actionBtn;
    private String options[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        numberA = findViewById(R.id.txtNumberA);
        numberB = findViewById(R.id.txtNumberB);
        result = findViewById(R.id.result);
        operations = findViewById(R.id.cmbOperation);
        actionBtn = findViewById(R.id.sumBtn);
        resources = this.getResources();

        options = resources.getStringArray(R.array.operations_list);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
        operations.setAdapter(adapter);

        operations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        actionBtn.setText(resources.getString(R.string.sum));
                        break;
                    case 1:
                        actionBtn.setText(resources.getString(R.string.subtract));
                        break;
                    case 2:
                        actionBtn.setText(resources.getString(R.string.multiply));
                        break;
                    case 3:
                        actionBtn.setText(resources.getString(R.string.divide));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void calculate(View v){
        double num1, num2, res = 0;
        int selectedOption;
        result.setText("");
        if(validFields()){

            selectedOption = operations.getSelectedItemPosition();
            num1 = Double.parseDouble(numberA.getText().toString());
            num2 = Double.parseDouble(numberB.getText().toString());

            switch (selectedOption){
                case 0:
                    res = Methods.add(num1, num2);
                    break;
                case 1:
                    res = Methods.subtract(num1, num2);
                    break;
                case 2:
                    res = Methods.multiply(num1, num2);
                    break;
                case 3:
                    res = Methods.divide(num1, num2);
                    break;
            }

            result.setText("" + String.format("%.2f", res));
        }
    }

    public boolean validFields(){
        int option = operations.getSelectedItemPosition();

        if(numberA.getText().toString().isEmpty()){
            numberA.requestFocus();
            numberA.setError(resources.getString(R.string.error_number_one));
            return false;
        }

        if(numberB.getText().toString().isEmpty()) {
            numberB.requestFocus();
            numberB.setError(resources.getString(R.string.error_number_two));
            return false;
        }

        if(option == 3 && Double.parseDouble(numberB.getText().toString()) == 0){
            numberB.requestFocus();
            numberB.setError(resources.getString(R.string.invalid_divide_number));
            return false;
        }

        return true;
    }

    public void clear(View v){
        numberA.setText("");
        numberB.setText("");
        result.setText("");
        numberA.requestFocus();
        operations.setSelection(0);
    }
}
