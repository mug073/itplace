package com.simbirsoft.autotests.simbirsofthelpers;

import org.openqa.selenium.WebDriver;

/**
 * Created by user on 05.05.2016.
 */
public class HelperManager {

    private static WaitHelper waitHelper;
    private static EmailTemplate emailTemplate;

    public WaitHelper getWaitHelper() {
        if (waitHelper == null) {
            waitHelper = new WaitHelper();
        }
        return waitHelper;
    }



}
