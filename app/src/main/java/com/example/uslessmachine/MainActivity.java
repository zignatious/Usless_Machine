package com.example.uslessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Switch switchUsless;
    private Button buttonSelfDestruct;
    private Button buttonDoSomething;
    private ConstraintLayout constraintLayout;
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
        constraintLayout = findViewById(R.id.constraint_layout_main);
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
//
//
//            }



                //set the onclick listener for the self destruct button
                //make a 10 second countdown timer
                // display how much time is left on the countdown on the button
                //when the timer is complete, call the finish() method to close
                //the activity
                //make the background blink red
                //make the background blink red increasingly faster as the time runs out

                }

        });
                  buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          new CountDownTimer(10000, 1000) {

                              public void onTick(long millisUntilFinished) {
                                  buttonSelfDestruct.setText("seconds remaining: " + millisUntilFinished / 1000);

                                  final ConstraintLayout layout =  findViewById(R.id.constraint_layout_main);
                                  final AnimationDrawable drawable = new AnimationDrawable();
                                  final Handler handler = new Handler();

                                  drawable.addFrame(new ColorDrawable(Color.RED), 400);
                                  drawable.addFrame(new ColorDrawable(Color.WHITE), 400);
                                  drawable.setOneShot(false);

                                  layout.setBackgroundDrawable(drawable);
                                  handler.postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          drawable.start();
                                      }
                                  }, 100);
                              }

                              public void onFinish() {
                                  buttonSelfDestruct.setText("BOOM!");

                                  finish();

                              }
                          }.start();
                      }
                  });




    }

}

