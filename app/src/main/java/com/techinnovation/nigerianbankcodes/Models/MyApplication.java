package com.techinnovation.nigerianbankcodes.Models;


import com.ads.control.admob.Admob;
import com.ads.control.admob.AppOpenManager;
import com.ads.control.ads.AperoAd;
import com.ads.control.application.AdsMultiDexApplication;
import com.ads.control.config.AdjustConfig;
import com.ads.control.config.AperoAdConfig;
import com.facebook.FacebookSdk;
import com.techinnovation.nigerianbankcodes.Activites.SplashScreen;
import com.techinnovation.nigerianbankcodes.R;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends AdsMultiDexApplication {
    private final String APPSFLYER_TOKEN = "2PUNpdyDTkedZTgeKkWCyB";
    private final String ADJUST_TOKEN = "cc4jvudppczk";
    private final String EVENT_PURCHASE_ADJUST = "gzel1k";
    private final String EVENT_AD_IMPRESSION_ADJUST = "gzel1k";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        AppOpenManager.getInstance().disableAppResumeWithActivity(SplashScreen.class);
        Admob.getInstance().setNumToShowAds(0);
        initAds();
    }

    private void initAds() {
        String environment = false ? AperoAdConfig.ENVIRONMENT_DEVELOP : AperoAdConfig.ENVIRONMENT_PRODUCTION;
        AperoAdConfig aperoAdConfig = new AperoAdConfig(this, AperoAdConfig.PROVIDER_ADMOB, environment);
        FacebookSdk.setClientToken(getApplicationContext().getString(R.string.facebook_app_id));
        // Optional: setup Adjust event
        AdjustConfig adjustConfig = new AdjustConfig(ADJUST_TOKEN);
        adjustConfig.setEventAdImpression(EVENT_AD_IMPRESSION_ADJUST);
        adjustConfig.setEventNamePurchase(EVENT_PURCHASE_ADJUST);
        aperoAdConfig.setAdjustConfig(adjustConfig);

        // Optional: enable ads resume
        aperoAdConfig.setIdAdResume(getResources().getString(R.string.appopen));

        // Optional: setup list device test - recommended to use
        List<String> listTestDevice = new ArrayList<>();
        listTestDevice.add("");
        aperoAdConfig.setListDeviceTest(listTestDevice);
        AperoAd.getInstance().init(this, aperoAdConfig, false);

        // Auto disable ad resume after the user clicks ads and back to the app
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        // If true -> onNextAction() is called right after Ad Interstitial showed
        Admob.getInstance().setOpenActivityAfterShowInterAds(false);
    }

    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }
}
