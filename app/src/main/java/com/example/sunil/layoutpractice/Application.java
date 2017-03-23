package com.example.sunil.layoutpractice;

/**
 * Created by Sunil on 08-03-2017.
 */
import android.content.Context;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Sunil on 28-02-2017.
 */

public class  Application extends android.app.Application {
    private static Context mContext;

    public Application(){
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("HVD Fonts - BrandonText-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

