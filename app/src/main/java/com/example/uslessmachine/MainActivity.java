package com.example.uslessmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Switch switchUsless;
    private Button buttonSelfDestruct;
    private Button buttonDoSomething;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wiredWidgets();
        
        setListeners();
    }

    private void wiredWidgets() {
        switchUsless = findViewById(R.id.switch_main_usless);
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        buttonDoSomething = findViewById(R.id.button_main_busy);

    }

    private void setListeners() {
        switchUsless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                new CountDownTimer(2000, 1000) {
                    @Override

                    //cancels the timer if the switch is turned off before the countdown is finished
                    public void onTick(long l) {
                        if(!switchUsless.isChecked()) {
                            cancel();
                        }
                        }


                    @Override
                    public void onFinish() {
                        switchUsless.setChecked(false);
                    }
                }.start();
//                if(isChecked) {
//                    Toast.makeText(MainActivity.this, "SWITCH GO ON", Toast.LENGTH_SHORT);
//                }else {
//                    Toast.makeText(MainActivity.this, "SWITCH GO OFF", Toast.LENGTH_SHORT);
//                }
                }

        });
    }

}

