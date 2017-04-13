package com.example.gjwlg.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private StringBuffer sb;

    public static final int PLUS = 1;           //加法
    public static final int MINUS = 2;          //减法
    public static final int MUTIPLE = 3;        //乘法
    public static final int DIVIDE = 4;         //除法

    public int calState;
    public double firstArg;
    public double result;
    public boolean isFirstArg = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = (TextView) this.findViewById(R.id.tv_display);
        sb = new StringBuffer();
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clear:
                sb.setLength(0);
                tvDisplay.setText("0");
                break;
            case R.id.tv_backspace:
                if (sb.length()-1>=0){
                    sb.delete(sb.length()-1,sb.length());
                    tvDisplay.setText(sb.toString().trim());
                }
                break;
            case R.id.tv_dot:
                if (!tvDisplay.getText().toString().contains(".")) {
                    if (tvDisplay.getText().toString().startsWith("0")) {
                        sb.append("0".trim());
                    }
                    sb.append(".".trim());
                    tvDisplay.setText(sb.toString().trim());
                }
                break;
            case R.id.tv_plus:
                initCal(PLUS);
                break;
            case R.id.tv_minus:
                initCal(MINUS);
                break;
            case R.id.tv_multiple:
                initCal(MUTIPLE);
                break;
            case R.id.tv_divide:
                initCal(DIVIDE);
                break;
            case R.id.tv_equal:
                String strEqual = String.valueOf(result);
//                Toast.makeText(MainActivity.this, strEqual, Toast.LENGTH_SHORT).show();
                sb.setLength(0);
                sb.append(strEqual.trim());
                tvDisplay.setText(sb.toString().trim());
                break;
            case R.id.num0:
                inputNum("0");
                break;
            case R.id.num1:
                inputNum("1");
                break;
            case R.id.num2:
                inputNum("2");
                break;
            case R.id.num3:
                inputNum("3");
                break;
            case R.id.num4:
                inputNum("4");
                break;
            case R.id.num5:
                inputNum("5");
                break;
            case R.id.num6:
                inputNum("6");
                break;
            case R.id.num7:
                inputNum("7");
                break;
            case R.id.num8:
                inputNum("8");
                break;
            case R.id.num9:
                inputNum("9");
                break;
        }
    }

    public void inputNum(String num) {
        if (isFirstArg) {
            sb.append(num.trim());
            tvDisplay.setText(sb.toString().trim());
        } else {
            sb.append(num.trim());
            tvDisplay.setText(sb.toString().trim());
            double secondArg = Double.parseDouble(tvDisplay.getText().toString());
            switch (calState) {
                case 1:
                    result = CalUtil.add(firstArg, secondArg);
                    break;
                case 2:
                    result = CalUtil.sub(firstArg, secondArg);
                    break;
                case 3:
                    result = CalUtil.mul(firstArg, secondArg);
                    break;
                case 4:
                    result = CalUtil.div(firstArg, secondArg);
                    break;
            }
        }
    }

    public void initCal(int state) {
        if (!tvDisplay.getText().toString().equals("")) {
            calState = state;
            firstArg = Double.parseDouble(tvDisplay.getText().toString());
            isFirstArg = false;
            sb.setLength(0);
        } else {
            sb.setLength(0);
            tvDisplay.setText("0");
            calState = state;
            firstArg = Double.parseDouble(tvDisplay.getText().toString());
            isFirstArg = false;
        }
    }

}
