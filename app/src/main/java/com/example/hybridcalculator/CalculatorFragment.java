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

public class CalculatorFragment extends Fragment {
    private EditText display;
    private ImageButton backspace;
    private Button zero,one,two,three,four,five,six,seven,eight,nine,add,sub,multiply,divide,equals,point,clear, bracket, power;
    private CalculatorFragmentListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calculator_fragment, container, false);
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


        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("1");

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("1");

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("2");

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("3");

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("4");

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("5");

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("6");

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("7");

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("8");

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("9");

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("+");

            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("-");

            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("*");

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("/");

            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText(".");

            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String expression = display.getText().toString();
                Expression mathExpression = new Expression(expression);
                String result = String.valueOf(mathExpression.calculate());
                listener.sendResult(result);
                display.setText("");
            }
        });
        bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }

            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("^");

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText("");

            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             int cursorPos = display.getSelectionStart();
                                             int textLength = display.getText().length();

                                             if(cursorPos!=0 || textLength!=0){
                                                 SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
                                                 selection.replace(cursorPos-1, cursorPos, "");
                                                 display.setText(selection);
                                                 display.setSelection(cursorPos-1);
                                             }
                                         }
                                     }
        );
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
