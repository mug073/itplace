package com.simbirsoft.autotests.simbirsofthelpers;

import com.simbirsoft.autotests.DriverInit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper extends DriverInit {
    //Ожидание появления вебэлемента element в течении count секунд
    public void waitForWebElement(WebElement element, int count) {
        WebDriverWait wait = new WebDriverWait(driver,count);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void threadSleep(int count) {
        try {
            Thread.sleep(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
