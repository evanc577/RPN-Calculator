package com.example.evan.rpncalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
    button9, buttonPoint, buttonAdd, buttonSub, buttonMul, buttonDiv, buttonEnter, buttonDrop;

    TextView displayY, displayZ, displayT;
    TextView displayX;

    Stack stack = new Stack(4);

    boolean pointUsed = false;  //is decimal point already used, prevents >1 decimal points
    boolean newNumber = false;  //overwrite current string in displayX (e.g. after enter)
    boolean newLine = false;    //create new line when entering digits (e.g. after + operation)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonEnter = findViewById(R.id.buttonEnter);
        buttonDrop = findViewById(R.id.buttonDrop);
        displayX = findViewById(R.id.stackX);
        displayY = findViewById(R.id.stackY);
        displayZ = findViewById(R.id.stackZ);
        displayT = findViewById(R.id.stackT);


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigit("9");
            }
        });

        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pointUsed) {
                    addDigit(".");
                    pointUsed = true;
                }
            }
        });

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stack.push(displayX.getText().toString());
                updateDisplay();
                newNumber = true;
            }
        });

        buttonDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stack.drop();
                updateDisplay();
            }
        });

        //OPERATIONS

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation("add");
                newLine = true;
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation("sub");
                newLine = true;
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation("mul");
                newLine = true;
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation("div");
                newLine = true;
            }
        });

    }

    private void operation(String oper) {
        stack.operation(oper);
        updateDisplay();
        newNumber = false;
    }

    private void addDigit(String str) {
        if (newNumber) {
            displayX.setText(str);
            newNumber = false;
        }
        else if (newLine) {
            stack.push("0");
            updateDisplay();
            displayX.setText(str);
            newLine = false;
        }
        else {
            displayX.setText(displayX.getText() + str);
        }
        updateStackX(displayX.getText().toString());
    }

    private void updateStackX(String str) {
        stack.updateStackX(str);
    }

    private void updateDisplay() {
        displayX.setText(stack.getString(0));
        displayY.setText(stack.getString(1));
        displayZ.setText(stack.getString(2));
        displayT.setText(stack.getString(3));
    }
}
