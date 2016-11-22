package com.example.nosenko.androidoneless4;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private enum actions {SUM,
                          DIFFERENCE,
                          MULTIPLICATION,
                          DIVISION}


    private EditText etValueA = null;
    private EditText etValueB = null;
    private TextView tvSolution = null;
    private Button btnSum = null;
    private Button btnDifference = null;
    private Button btnMultiplication = null;
    private Button btnDivision = null;
    private Button btnEqually = null;

    private float value_a = 0;
    private float value_b = 0;
    private String solution = null;
    private actions action = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValueA = (EditText)findViewById(R.id.etValueA);
        etValueA.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etValueA.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                setValueA();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        etValueB = (EditText)findViewById(R.id.etValueB);
        etValueB.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etValueB.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                setValueB();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        tvSolution = (TextView)findViewById(R.id.tvSolution);

        btnSum = (Button) findViewById(R.id.btnSum);
        btnSum.setOnClickListener(this);

        btnDifference = (Button)findViewById(R.id.btnDifference);
        btnDifference.setOnClickListener(this);

        btnMultiplication = (Button)findViewById(R.id.btnMultiplication);
        btnMultiplication.setOnClickListener(this);

        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnDivision.setOnClickListener(this);

        btnEqually = (Button) findViewById(R.id.btnEqually);
        btnEqually.setOnClickListener(this);

        if (savedInstanceState != null){
            etValueA.setText(savedInstanceState.getString("value_a"));
            setValueA();

            etValueB.setText(savedInstanceState.getString("value_b"));
            setValueB();

            action = (actions) savedInstanceState.getSerializable("action");
            setActionText();

            solution = savedInstanceState.getString("solution");
            tvSolution.setText(solution);
        }
    }

    private void setValueA() {
        try {
            value_a = Float.parseFloat(etValueA.getText().toString());
        }catch (Exception e){
            value_a = 0;
        }
    }

    private void setValueB(){
        try{
            value_b = Float.parseFloat(etValueB.getText().toString());
        }catch (Exception e){
            value_b = 0;
        }
    }

    private String calculated(float a, float b, actions action){
        final String divisionZero = "NaN";
        final String MASK = "%.3f";
        switch (action){
            case SUM:
                return String.format(MASK,  (a + b));

            case DIFFERENCE:
                return String.format(MASK,  (a - b));

            case MULTIPLICATION:
                return String.format(MASK,  (a * b));

            case DIVISION:
                if (b == 0) {
                    return divisionZero;
                }
                else return String.format(MASK,  (a / b));
        }
        return "";
    }

    private void setActionText(){

        if (action == null) return;

        TextView tvAction = (TextView)findViewById(R.id.tvAction);
        switch (action){
            case SUM:
                tvAction.setText("+");
                break;
            case DIFFERENCE:
                tvAction.setText("-");
                break;

            case MULTIPLICATION:
                tvAction.setText("*");
                break;

            case DIVISION:
                tvAction.setText("/");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("onClick", "onClick");
        if (v.getId() == btnEqually.getId()){
            if (action != null) {
                Log.d("onClick", "btnEqually");
                solution = calculated(value_a, value_b, action);
                tvSolution.setText(solution);
            }
        }
        else{
            if (v.getId() == btnSum.getId()){
                Log.d("onClick", "btnSum");
                action = actions.SUM;
            }

            if (v.getId() == btnDifference.getId()){
                Log.d("onClick", "btnDifference");
                action = actions.DIFFERENCE;
            }

            if (v.getId() == btnMultiplication.getId()){
                Log.d("onClick", "btnMultiplication");
                action = actions.MULTIPLICATION;
            }

            if (v.getId() == btnDivision.getId()){
                Log.d("onClick", "btnDivision");
                action = actions.DIVISION;
            }
            setActionText();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("value_a", etValueA.getText().toString());
        savedInstanceState.putString("value_b", etValueB.getText().toString());
        savedInstanceState.putSerializable("action", action);
        savedInstanceState.putString("solution", solution);
    }
}
