package com.example.hybridcalculator;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CalculatorFragment.CalculatorFragmentListener {
    public CalculatorFragment calculatorFragment;
    public ResultFragment resultFragment;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorFragment = new CalculatorFragment();
        resultFragment = new ResultFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.calculator_container,
                calculatorFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.result_container,
                resultFragment).commit();
}

    @Override
    public void sendResult(String result) {
        resultFragment.updateResult(result);
    }
}