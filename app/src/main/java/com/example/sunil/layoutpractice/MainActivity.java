package com.example.sunil.layoutpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Button b = (Button)findViewById(R.id.submit_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VerifyMobile.class);
                startActivity(intent);
            }
        });*/
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void setWelcomeBackLayout(String currentStep,String moreStepsToGo,boolean isFifthStep, int visibility){
        //-----------initializing views---------
        LinearLayout welcomeBackLayout = (LinearLayout)findViewById(R.id.welcome_back_layout);
        TextView currentStepTextView = (TextView)findViewById(R.id.current_step_tv);
        if (visibility == 1) {
            //------creating spannable string-------
            SpannableStringBuilder sBuilder = new SpannableStringBuilder();// Default TextView font. // Default TextView font.
            sBuilder.append("You're currently on step ");
            sBuilder.append(currentStep);
            sBuilder.append(" of ");
            sBuilder.append("5");
            if(isFifthStep){
                sBuilder.append(" in the account set up process. You're almost there!");
            }else{
                sBuilder.append(" in the account set up process. Only "+moreStepsToGo+" more to go!");
            }
            CalligraphyTypefaceSpan typefaceSpanMedium = new CalligraphyTypefaceSpan(TypefaceUtils.load(this.getAssets(), "HVD Fonts - BrandonText-Medium.otf"));
            CalligraphyTypefaceSpan typefaceSpanMedium2 = new CalligraphyTypefaceSpan(TypefaceUtils.load(this.getAssets(), "HVD Fonts - BrandonText-Medium.otf"));
            sBuilder.setSpan(typefaceSpanMedium2, 25, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            sBuilder.setSpan(typefaceSpanMedium, 30, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentStepTextView.setText(sBuilder, TextView.BufferType.SPANNABLE);
        } else {
            welcomeBackLayout.setVisibility(View.GONE);
        }
    }
}
