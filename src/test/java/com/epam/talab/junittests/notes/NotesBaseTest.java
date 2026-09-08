package com.epam.talab.junittests.notes;

import com.epam.talab.config.DriverProvider;
import org.junit.jupiter.api.AfterEach;

public abstract class NotesBaseTest {
    @AfterEach
    public void closeBrowser() {
        DriverProvider.releaseDriver();
    }
}
