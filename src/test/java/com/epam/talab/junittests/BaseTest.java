package com.epam.talab.junittests;

import com.epam.talab.config.DriverProvider;
import org.junit.jupiter.api.AfterEach;


public abstract class BaseTest {


    @AfterEach
    public void closeBrowser() {
        DriverProvider.releaseDriver();
    }
}
