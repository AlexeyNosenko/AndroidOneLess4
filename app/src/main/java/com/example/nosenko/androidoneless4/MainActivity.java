package com.example.nosenko.androidoneless4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// запрет ввода текста
// поправить интерфейс, два стиля
// сохранять данные при перевороте
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private enum actions {SUM,
                          DIFFERENCE,
                          MULTIPLICATION,
                          DIVISION};

    private Button btnSum;
    private Button btnDifference;
    private Button btnMultiplication;
    private Button btnDivision;
    private Button btnEqually;

    final String MASK = "%.3f";

    actions action;

    private void Init(){
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    private String calculated(float a, float b, actions action){
        final String divisionZero = "NaN";
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

    @Override
    public void onClick(View v) {
        Log.d("onClick", "onClick");
        if (v.getId() == btnEqually.getId()){
            Log.d("onClick", "btnEqually");
            EditText editText = (EditText)findViewById(R.id.etValueA);
            float a = Float.parseFloat(editText.getText().toString());

            editText = (EditText)findViewById(R.id.etValueB);
            float b = Float.parseFloat(editText.getText().toString());

            TextView tvSolution = (TextView)findViewById(R.id.tvSolution);
            tvSolution.setText(calculated(a, b, action));
        }
        else{
            TextView tvAction = (TextView)findViewById(R.id.tvAction);
            Button btn = (Button)v;
            tvAction.setText(btn.getText());


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
        }
    }
}
