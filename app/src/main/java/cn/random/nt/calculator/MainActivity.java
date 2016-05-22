package cn.random.nt.calculator;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Stack;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton clean;
    private ImageButton backspace;
    private ImageButton divide;
    private ImageButton multiply;
    private ImageButton subtract;
    private ImageButton add;
    private ImageButton equal;
    private Button num_1;
    private Button num_2;
    private Button num_3;
    private Button num_4;
    private Button num_5;
    private Button num_6;
    private Button num_7;
    private Button num_8;
    private Button num_9;
    private Button num_0;
    private Button point;
    private TextView result;
    private TextView equation;

    private boolean flag_point = true;
    private StringBuffer formula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        clean = (ImageButton) findViewById(R.id.clean);
        backspace = (ImageButton) findViewById(R.id.backspace);
        divide = (ImageButton) findViewById(R.id.divide);
        multiply = (ImageButton) findViewById(R.id.multiply);
        subtract = (ImageButton) findViewById(R.id.subtract);
        add = (ImageButton) findViewById(R.id.add);
        equal = (ImageButton) findViewById(R.id.equal);
        num_0 = (Button) findViewById(R.id.number_0);
        num_1 = (Button) findViewById(R.id.number_1);
        num_2 = (Button) findViewById(R.id.number_2);
        num_3 = (Button) findViewById(R.id.number_3);
        num_4 = (Button) findViewById(R.id.number_4);
        num_5 = (Button) findViewById(R.id.number_5);
        num_6 = (Button) findViewById(R.id.number_6);
        num_7 = (Button) findViewById(R.id.number_7);
        num_8 = (Button) findViewById(R.id.number_8);
        num_9 = (Button) findViewById(R.id.number_9);
        point = (Button) findViewById(R.id.number_point);
        result = (TextView) findViewById(R.id.result);
        equation = (TextView) findViewById(R.id.equation);
        formula = new StringBuffer();

        clean.setOnClickListener(this);
        backspace.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        subtract.setOnClickListener(this);
        add.setOnClickListener(this);
        equal.setOnClickListener(this);
        num_0.setOnClickListener(this);
        num_1.setOnClickListener(this);
        num_2.setOnClickListener(this);
        num_3.setOnClickListener(this);
        num_4.setOnClickListener(this);
        num_5.setOnClickListener(this);
        num_6.setOnClickListener(this);
        num_7.setOnClickListener(this);
        num_8.setOnClickListener(this);
        num_9.setOnClickListener(this);
        point.setOnClickListener(this);
        result.setSingleLine();
        result.setText("0");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.number_0:
                formula.append("0");
                result.setText(formula);
                break;
            case R.id.number_1:
                formula.append("1");
                result.setText(formula);
                break;
            case R.id.number_2:
                formula.append("2");
                result.setText(formula);
                break;
            case R.id.number_3:
                formula.append("3");
                result.setText(formula);
                break;
            case R.id.number_4:
                formula.append("4");
                result.setText(formula);
                break;
            case R.id.number_5:
                formula.append("5");
                result.setText(formula);
                break;
            case R.id.number_6:
                formula.append("6");
                result.setText(formula);
                break;
            case R.id.number_7:
                formula.append("7");
                result.setText(formula);
                break;
            case R.id.number_8:
                formula.append("8");
                result.setText(formula);
                break;
            case R.id.number_9:
                formula.append("9");
                result.setText(formula);
                break;
            case R.id.number_point:
                if (flag_point) {
                    try {
                        double i = Double.valueOf(formula.substring(formula.length() - 1));
                    } catch (Exception e) {
                        break;
                    }
                    formula.append(".");
                    result.setText(formula);
                    flag_point = false;
                }
                break;

            case R.id.clean:
                formula.setLength(0);
                result.setText("0");
                equation.setText("");
                flag_point = true;
                break;

            case R.id.backspace:
                try {
                    if (formula.substring(formula.length() - 1).equals(".")) {
                        flag_point = true;
                    }
                    formula.setLength(formula.length() - 1);
                    if (formula.length() == 0) {
                        result.setText("0");
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
                result.setText(formula);
                break;

            case R.id.add:
                try {
                    double i = Double.valueOf(formula.substring(formula.length() - 1));
                    flag_point = true;
                } catch (Exception e) {
                    break;
                }
                formula.append("+");
                result.setText(formula);
                break;
            case R.id.subtract:
                try {
                    double i = Double.valueOf(formula.substring(formula.length() - 1));
                    flag_point = true;
                } catch (Exception e) {
                    break;
                }
                formula.append("-");
                result.setText(formula);
                break;
            case R.id.multiply:
                try {
                    double i = Double.valueOf(formula.substring(formula.length() - 1));
                    flag_point = true;
                } catch (Exception e) {
                    break;
                }
                formula.append("*");
                result.setText(formula);
                break;
            case R.id.divide:
                try {
                    double i = Double.valueOf(formula.substring(formula.length() - 1));
                    flag_point = true;
                } catch (Exception e) {
                    break;
                }
                formula.append("/");
                result.setText(formula);
                break;
            case R.id.equal:
                try {
                    double i = Double.valueOf(formula.substring(formula.length() - 1));
                    flag_point = true;
                } catch (Exception e) {
                    break;
                }
                formula.append("=");
                equation.setText(formula);
                Result(formula.toString());
                formula.setLength(0);
                formula.append(result.getText());
                break;
        }
    }

    private void Result(String str) {
        DecimalFormat df = new DecimalFormat("#.00");
        int len = str.length() - 1;
        char a;
        Stack<Double> numStack = new Stack<Double>();
        Stack<Character> opStack = new Stack<Character>();
        for (int i = str.length() - 2; i > 0; i--) {
            a = str.charAt(i);
            switch (a) {
                case '+':
                    numStack.push(Double.valueOf(df.format(Double.valueOf(str.substring(i + 1, len)))));
                    try {
                        if (opStack.peek() == '*' || opStack.peek() == '/') {
                            double x = numStack.pop();
                            double y = numStack.pop();
                            if (opStack.peek() == '*') {
                                opStack.pop();
                                numStack.push(x * y);
                            } else if (opStack.peek() == '/') {
                                opStack.pop();
                                numStack.push(x / y);
                            }
                        }
                    } catch (Exception e) {

                    }
                    opStack.push('+');
                    len = i;
                    break;
                case '-':
                    numStack.push(Double.valueOf(df.format(Double.valueOf(str.substring(i + 1, len)))));
                    try {
                        if (opStack.peek() == '*' || opStack.peek() == '/') {
                            double x = numStack.pop();
                            double y = numStack.pop();
                            if (opStack.peek() == '*') {
                                opStack.pop();
                                numStack.push(x * y);
                            } else if (opStack.peek() == '/') {
                                opStack.pop();
                                numStack.push(x / y);
                            }
                        }
                    } catch (Exception e) {

                    }
                    opStack.push('-');
                    len = i;
                    break;
                case '*':
                    numStack.push(Double.valueOf(df.format(Double.valueOf(str.substring(i + 1, len)))));
                    opStack.push('*');
                    len = i;
                    break;
                case '/':
                    numStack.push(Double.valueOf(df.format(Double.valueOf(str.substring(i + 1, len)))));
                    opStack.push('/');
                    len = i;
                    break;
                default:
                    break;
            }
        }
        numStack.push(Double.valueOf(df.format(Double.valueOf(str.substring(0, len)))));

        while (numStack.size() != 1) {
            double x = numStack.pop();
            double y = numStack.pop();
            char c = opStack.pop();
            switch (c) {
                case '+':
                    numStack.push(Double.valueOf(df.format(x + y)));
                    break;
                case '-':
                    numStack.push(Double.valueOf(df.format(x - y)));
                    break;
                case '*':
                    numStack.push(Double.valueOf(df.format(x * y)));
                    break;
                case '/':
                    numStack.push(Double.valueOf(df.format(x / y)));
                    break;
                default:
                    break;
            }
        }
        if (numStack.peek() % 1 == 0) {
            result.setText(String.valueOf(numStack.pop().intValue()));
            flag_point = true;
        } else {
            result.setText(numStack.pop().toString());
            flag_point = false;
        }
    }

}
