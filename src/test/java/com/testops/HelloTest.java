package com.testops;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelloTest {
    @Test
    public void testSayHelloReturnsHello() {
        HelloService service = new HelloService();
        assertEquals("Hello", service.sayHello());
    }
}
