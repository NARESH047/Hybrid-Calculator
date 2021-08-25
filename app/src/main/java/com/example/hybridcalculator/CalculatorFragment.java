package com.example.hybridcalculator;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.mariuszgromada.math.mxparser.Expression;

public class CalculatorFragment extends Fragment implements View.OnClickListener{
    private EditText display;
    private ImageButton backspace;
    private Button zero,one,two,three,four,five,six,seven,eight,nine,add,sub,multiply,divide,equals,point,clear, bracket, power;
    private CalculatorFragmentListener listener;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculator_fragment, container, false);

        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        display = view.findViewById(R.id.inputText);
        display.setShowSoftInputOnFocus(false);
        zero = view.findViewById(R.id.zeroButton);
        one = view.findViewById(R.id.oneButton);
        two = view.findViewById(R.id.twoButton);
        three = view.findViewById(R.id.threeButton);
        four = view.findViewById(R.id.fourButton);
        five = view.findViewById(R.id.fiveButton);
        six = view.findViewById(R.id.sixButton);
        seven = view.findViewById(R.id.sevenButton);
        eight = view.findViewById(R.id.eightButton);
        nine = view.findViewById(R.id.nineButton);
        add = view.findViewById(R.id.addButton);
        sub = view.findViewById(R.id.subtractButton);
        multiply = view.findViewById(R.id.multiplyBUtton);
        divide = view.findViewById(R.id.divisionButton);
        equals = view.findViewById(R.id.equalsButton);
        point = view.findViewById(R.id.pointButton);
        clear = view.findViewById(R.id.clearButton);
        bracket = view.findViewById(R.id.bracketButton);
        power = view.findViewById(R.id.powerButton);
        backspace = view.findViewById(R.id.backspaceButton);


        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        point.setOnClickListener(this);
        equals.setOnClickListener(this);
        bracket.setOnClickListener(this);
        power.setOnClickListener(this);
        clear.setOnClickListener(this);
        backspace.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zeroButton:
                updateText("0");
                break;
            case R.id.oneButton:
                updateText("1");
                break;
            case R.id.twoButton:
                display.setText("2");
                break;
            case R.id.threeButton:
                updateText("3");
                break;
            case R.id.fourButton:
                updateText("4");
                break;
            case R.id.fiveButton:
                updateText("5");
                break;
            case R.id.sixButton:
                updateText("6");
                break;
            case R.id.sevenButton:
                updateText("7");
                break;
            case R.id.eightButton:
                updateText("8");
                break;
            case R.id.nineButton:
                updateText("9");
                break;
            case R.id.addButton:
                updateText("+");
                break;
            case R.id.subtractButton:
               updateText("-");
                break;
            case R.id.multiplyBUtton:
                updateText("*");
                break;
            case R.id.divisionButton:
                updateText("/");
                break;
            case R.id.pointButton:
                updateText(".");
                break;
            case R.id.powerButton:
                updateText("^");
                break;
            case R.id.bracketButton:
                int curserPos = display.getSelectionStart();
                int openBracket = 0 , closedBracket = 0;
                int textLen = display.getText().length();

                for (int i = 0; i<curserPos; i++){
                    if(display.getText().toString().substring(i, i++).equals("(")){
                        openBracket++;
                    }
                }
                for (int i = 0; i<curserPos; i++){
                    if(display.getText().toString().substring(i, i++).equals(")")){
                        closedBracket++;
                    }
                }

                if(openBracket == closedBracket || display.getText().toString().substring(textLen-1, textLen).equals("(")){
                    updateText("(");
                    display.setSelection(curserPos+1);
                }

                if( closedBracket < openBracket  || !display.getText().toString().substring(textLen-1, textLen).equals(")")){
                    updateText(")");
                    display.setSelection(curserPos+1);
                };
                break;
            case R.id.clearButton:
                display.setText("");
                break;
            case R.id.backspaceButton:
                int cursorPos = display.getSelectionStart();
                int textLength = display.getText().length();

                if(cursorPos!=0 || textLength!=0){
                    SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
                    selection.replace(cursorPos-1, cursorPos, "");
                    display.setText(selection);
                    display.setSelection(cursorPos-1);
                }
                break;
            case R.id.equalsButton:
                String expression = display.getText().toString();
                Expression mathExpression = new Expression(expression);
                String result = String.valueOf(mathExpression.calculate());
                listener.sendResult(result);
                display.setText("");
                break;
            default:
                break;

        }

    }



    private void updateText(String stringTOAdd){
        String currentString = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String left = currentString.substring(0, cursorPos);
        String right = currentString.substring(cursorPos);
        if(display.getText().toString().equals("") || display.getText().toString() == null){
            display.setText(String.format(stringTOAdd));
            display.setSelection(cursorPos + 1);
        } else{
            display.setText(String.format("%s%s%s", left, stringTOAdd, right));            display.setSelection(cursorPos + 1);
            display.setSelection(cursorPos + 1);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (CalculatorFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



    public interface CalculatorFragmentListener {
        void sendResult(String result);
    }

}
