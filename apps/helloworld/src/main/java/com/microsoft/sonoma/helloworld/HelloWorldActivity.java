package com.microsoft.sonoma.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.microsoft.sonoma.analytics.Analytics;
import com.microsoft.sonoma.core.Sonoma;
import com.microsoft.sonoma.core.utils.UUIDUtils;
import com.microsoft.sonoma.crashes.Crashes;

public class HelloWorldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sonoma.setLogLevel(Log.VERBOSE);
        Sonoma.start(getApplication(), UUIDUtils.randomUUID().toString(), Analytics.class, Crashes.class);
    }

    @SuppressWarnings({"ConstantConditions", "ConstantIfStatement"})
    @Override
    protected void onDestroy() {

        /* Trigger a super not called exception for testing crash reporting. */
        if (false)
            super.onDestroy();
    }
}
