package com.simbirsoft.autotests.itplacesimbirsoftcom.abstractPages;

import com.simbirsoft.autotests.simbirsofthelpers.EmailTemplate;
import org.openqa.selenium.WebElement;

/**
 * Created by user on 24.05.2016.
 */
public class ItPlaceFollowForm extends EmailTemplate {

    public void sendPhoneKeys(WebElement inputTelephone, String phone) {
        inputTelephone.sendKeys(phone);
    }

    //Для отправки пустых полей
    public void sendKey(WebElement element, String text) {
        if (text != null) element.sendKeys(text);
    }
}
