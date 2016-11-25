package com.simbirsoft.autotests.itplacesimbirsoftcom;

import com.simbirsoft.autotests.DriverInit;
import com.simbirsoft.autotests.itplacesimbirsoftcom.abstractPages.ItplaceSimbirsoftPage;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by user on 22.07.2016.
 */
public class ItplaceCourseFormTests extends DriverInit{
    ItplaceSimbirsoftPage itplacePage = new ItplaceSimbirsoftPage(driver);

    @Features("Test form2")
    @Stories("itplace.simbirsoft.com")
    @Title("Test form without captcha")
    @Test
    public void case08() {
        itplacePage
                .openItPlaceMainPageWithCaptcha()
                .checkFormCompleteHalfFields();
        System.out.println("Case is passed. http://itplace.simbirsoft.com/ Form without captcha worked");
    }

    @Features("Test form3")
    @Stories("itplace.simbirsoft.com")
    @Title("Test form with random ENROLL")
    @Test
    public void case09() {
        itplacePage
                .openItPlaceMainPage()
                .checkRndEnroll();
        System.out.println("Case is passed. http://itplace.simbirsoft.com/ Form with random enroll");
    }
}
