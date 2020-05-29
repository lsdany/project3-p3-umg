package com.ld.project3p3umg.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class BrowserServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void searchResource() {
        BrowserService browser = new BrowserService(null);
//        browser.parseServer("http://www.server.com/datos/archivo.txt");
    }


    @Test
    void searchResourceWithoutHttp() {
        BrowserService browser = new BrowserService(null);
//        browser.parseServer("www.server.com/datos/archivo.txt");
    }

    @Test
    void searchResources() {
    }

    @Test
    void validTypeResource(){
        String server = "http://www.server.com/datos/archivo.txt";
        String regex = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(server);
        assertTrue(matcher.matches());
    }

    @Test
    void validUrl(){
        String server = "https://www.server.com/datos/archivo.txt";
        String regex = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(server);
        assertTrue(matcher.matches());
    }

    @Test
    void validUrl2(){
        String server = "www.server.com/datos/archivo.txt";
        String regex = "^[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(server);
        assertTrue(matcher.matches());
    }

    @Test
    void validUrl3(){
        String server = "http://www.server.com/datos/archivo.txt";
        String regex = "^[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(server);
        assertFalse(matcher.matches());
    }
}