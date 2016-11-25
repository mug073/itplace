package com.simbirsoft.autotests.itplacesimbirsoftcom.abstractPages;

import com.simbirsoft.autotests.SuitTest;
import com.simbirsoft.autotests.simbirsofthelpers.EmailTemplate;
import com.simbirsoft.autotests.simbirsofthelpers.HelperManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.simbirsoft.autotests.DriverInit.driver;
import static com.simbirsoft.autotests.DriverInit.waitTime;


public class ItplaceSimbirsoftPage {

    private boolean Ok;
    private WebDriver driver;
    HelperManager manager = new HelperManager();

    public ItplaceSimbirsoftPage(WebDriver _driver) {
        driver = _driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(xpath = "//*[@id='name']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//*[@id='familyName']")
    private WebElement inputSecondName;

    private static final String phoneLocation = "phone";
    @FindBy(xpath = "//*[@id='phone']")
    private WebElement inputTelephone;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement inputCity;

    @FindBy(xpath = "//*[@id='message']")
    private WebElement inputMessage;

    @FindBy(xpath = ".//*[@id='contactformid']//div[2]/div[2]//label")
    private List<WebElement> selectCheckbox;

    @FindBy(xpath = "//button[contains(@class,'programs__sign')]")
    private List<WebElement> programmButtons;

    //</editor-fold>

    @FindBy(xpath = "//div[contains(@class,'itplace-intro')]//button")
    private WebElement enroll;

    @FindBy(xpath = "//*[@id='contactformid']//button")
    private WebElement sendButton;

    //    рамка валидации почты
    @FindBy(xpath = ".//*[@id='captcha']")
    private WebElement redBoarder;

    @Step("Open page itplace.simbirsoft.com")
    public ItplaceSimbirsoftPage openItPlaceMainPage() {
        try {
            driver.get(SuitTest.getItplaceMainPage());
        } catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/ page is NOT opened");
        }
        return this;
    }

    @Step("Open page itplace.simbirsoft.com")
    public ItplaceSimbirsoftPage openItPlaceMainPageWithCaptcha() {
        try {
            driver.get(SuitTest.getItplaceMainPageWithCaptha());
        } catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/ page is NOT opened");
        }
        return this;
    }

    //Заполнение тестовых данных
    @Step("Filling the form with test data")
    public EmailTemplate inputFields () {
        return new ItPlaceFollowForm()
                //.setName("Vinokurov")
                .setFirstName("Vinokurov")
                .setSecondName("Test")
                .setEmail("test")
                .setPhone("1234567890")
                .setCity("Ulyanovsk");
    }

    //случайный выбор кнопки из массива
    @Step("Select random enroll button")
    public String selectRndEnrolButton(){
        List<WebElement> buttons = programmButtons;
        int l = buttons.size() - 1;
        //System.out.println("Число найденных элементов: " + l);
        Random rndbtn = new Random();
        int i=rndbtn.nextInt(l+1);
        WebElement rndProgramm= buttons.get(i);
        rndProgramm.click();
        //try {Thread.sleep(100);} catch (Exception ex) {ex.printStackTrace();}
        String enrollCourse = rndProgramm.getAttribute("data-course");

     return enrollCourse;
    }

    //выбор всех checkbox
    @Step("All checkbox select")
    public void selectAllCheckbox(){
        List<WebElement> els = selectCheckbox;
        //System.out.println("Число найденных элементов: " + els.size());
        for ( WebElement checkBox : els ) {
            if (!checkBox.isSelected() ) {
                checkBox.click();
                //System.out.println(checkBox.toString());
                //try {Thread.sleep(100);} catch (Exception ex) {ex.printStackTrace();}
           }
        }
    }

    //SSS-300:Проверка формы с captcha
    @Step("Check form complete half fields")
    public ItplaceSimbirsoftPage checkFormCompleteHalfFields() {
        Ok = false;
        try {
            driver.get(SuitTest.getItplaceMainPageWithCaptha());
            //for(String winHandle : driver.getWindowHandles()){driver.switchTo().window(winHandle);}
            completeHalfFields();
            System.out.println("Checking Complete Form with Captcha is working");
            sendButton.click();
            if (redBoarder.getAttribute("class").equals("full-width error"))
            {Ok = true;}
        }catch (Exception e) {System.out.println("FAILURE: http://itplace.simbirsoft.com/ Test form JOIN NOW is failure");}
        Assert.assertTrue("ERROR: http://itplace.simbirsoft.com/ Form JOIN NOW is NOT sent", Ok);
        manager.getWaitHelper().threadSleep(5000);
        return this;
    }

    //SSS-400:Проверка cлучайного выбора записи
    @Step("Check form with random endroll")
    public ItplaceSimbirsoftPage checkRndEnroll() {
        Ok = false;
        try {
            driver.get(SuitTest.getItplaceMainPage());
            for(String winHandle : driver.getWindowHandles()){driver.switchTo().window(winHandle);}
            manager.getWaitHelper().waitForWebElement(enroll, waitTime);
            String rndCourse=selectRndEnrolButton();
            System.out.println("Checking Complete Form with enroll is working");
            System.out.println("Выбранный случайный курс: "+rndCourse);
            if (driver.findElement(By.id(rndCourse)).isSelected())
            {Ok = true;}
        }catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/ Test form JOIN NOW is failure");
        }
        Assert.assertTrue("ERROR: http://itplace.simbirsoft.com/ Form JOIN NOW is NOT sent", Ok);
        manager.getWaitHelper().threadSleep(5000);
        return this;
    }

    @Step("Input all fields without captcha")
    public void completeHalfFields () {
        try {
            ItPlaceFollowForm followForm = (ItPlaceFollowForm) inputFields();
            followForm.sendKey(inputFirstName,followForm.getFirstNameTemplate());
            followForm.sendKey(inputSecondName,followForm.getSecondNameTemplate());
            followForm.sendPhoneKeys(inputTelephone, followForm.getPhoneTemplate());
            followForm.sendKey(inputEmail,followForm.getEmailTemplate());
            followForm.sendKey(inputCity,followForm.getCityTemplate());
            followForm.sendKey(inputMessage,followForm.getMessageTemplate());
            selectAllCheckbox();
        } catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/ It is impossible to fill in a form");
        }
    }
}

