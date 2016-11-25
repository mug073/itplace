package com.simbirsoft.autotests;

import com.simbirsoft.autotests.itplacesimbirsoftcom.ItplaceCourseFormTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Vakazov.R on 24.03.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItplaceCourseFormTests.class//,
})
public class SuitTest {
    private static final String ITPLACE_MAIN_PAGE = "http://itplace.simbirsoft.com/?mode=test";
    private static final String ITPLACE_MAIN_PAGE_WITH_CAPTHA = "http://itplace.simbirsoft.com";

    public static String getItplaceMainPage() {return ITPLACE_MAIN_PAGE;}
    public static String getItplaceMainPageWithCaptha() {return ITPLACE_MAIN_PAGE_WITH_CAPTHA;}
}
