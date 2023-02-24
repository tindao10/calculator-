package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String history="";
    private Button button_history;
    TextView resultTv, solutionTV;
    MaterialButton buttonC, buttonBrackOpen, getButtonBrackClose;
    MaterialButton buttonDivde, buttonMultiply, buttonPlus, buttonSubtraction, buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC, buttonDot, button_historyTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTV=findViewById(R.id.solution_tv);

        button_history= (Button) findViewById(R.id.button_history);
        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });


        assignId(buttonC,R.id.buttion_c);
        assignId(buttonBrackOpen,R.id.buttion_open_bracket);
        assignId(getButtonBrackClose,R.id.buttion_close_bracket);
        assignId(buttonDivde,R.id.buttion_divide);
        assignId(buttonMultiply,R.id.buttion_multiply);
        assignId(buttonPlus,R.id.buttion_plus);
        assignId(buttonSubtraction,R.id.buttion_subtraction);
        assignId(buttonEquals,R.id.buttion_equals);
        assignId(button0,R.id.buttion_0);
        assignId(button1,R.id.buttion_1);
        assignId(button2,R.id.buttion_2);
        assignId(button3,R.id.buttion_3);
        assignId(button4,R.id.buttion_4);
        assignId(button5,R.id.buttion_5);
        assignId(button6,R.id.buttion_6);
        assignId(button7,R.id.buttion_7);
        assignId(button8,R.id.buttion_8);
        assignId(button9,R.id.buttion_9);
        assignId(buttonAC,R.id.buttion_AC);
        assignId(buttonDot,R.id.buttion_dot);

    }
    public void openHistory(){
        Intent intent=new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

     void assignId(MaterialButton btn, int id) {
        btn=findViewById(id);
        btn.setOnClickListener(this);
     }



    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate=solutionTV.getText().toString();

        button_historyTV=findViewById(R.id.button_history);
        Intent intent =new Intent(this,MainActivity2.class);


        if(buttonText.equals("=")) {
            history = buttonText.equals("=") ? resultTv.getText().toString().trim() : "=" + resultTv.getText().toString().trim();
            history = (dataToCalculate + buttonText + history).toString();
            intent.putExtra("keyResult",history);

        }

        button_historyTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        }
        );


        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTV.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate=dataToCalculate+buttonText;
        }

        solutionTV.setText(dataToCalculate);

        String finalResult= getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finaRerult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finaRerult.endsWith(".0")){
                finaRerult=finaRerult.replace(".0","");
            }
            return finaRerult;
        }catch (Exception e) {
            return "Err";
        }
    }

}