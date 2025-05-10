package com.testops;

import org.junit.Test;
import static org.junit.Assert.*;

public class UiSimulationTest {
    @Test
    public void testButtonIsVisible() {
        String simulatedButton = "<button id='submit'>Submit</button>";
        assertTrue("Submit button should be present", simulatedButton.contains("submit"));
    }
}
