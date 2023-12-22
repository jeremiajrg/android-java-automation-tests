package appiumtest;

import base.BasePageObject;
import data.TestingData;
import drivers.AndroidDriverInit;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.util.Random;


public class Payment extends BasePageObject {
    TestingData testingData = new TestingData();
    Random random = new Random();

    @Test
    public void userSuccesPaidWithCorrectCredentials(){
        //call driver method to create session (mandatory step to run automation)
        initialize();

        // user go to sale menu
        By BUTTON_MENU_SALE = MobileBy.id("menuSale");
        click(BUTTON_MENU_SALE);

        //verify user on sale menu
        By LABEL_SALE_MENU = MobileBy.id("txtTitle");
        AndroidElement findSaleMenu = driver.findElement(LABEL_SALE_MENU);
        Assertions.assertEquals("Selamat Datang di Menu Payment", findSaleMenu.getText());

        // user input nominal payment on input nominal
        By INPUT_NOMINAL = MobileBy.id("nominal");
        input(INPUT_NOMINAL, "2000000");

        // parsing string value nominal payment to integer
        String payment = find(INPUT_NOMINAL).getText();
        Integer intObject = Integer.valueOf(payment);
        int finalPayment = intObject.intValue();

        // set amount final
        testingData.setAmountPayment(finalPayment);

        // user input card atm on input card
        By INPUT_CARD = MobileBy.id("carduser");
        input(INPUT_CARD, testingData.getCardAtm());
        By INPUT_PIN = MobileBy.id("pin");
        input(INPUT_PIN, testingData.getPinAtm());

        // user verifikasi total payment is corrected
        By FINAL_AMOUNT = MobileBy.id("totalPayment");
        String amount = find(FINAL_AMOUNT).getText().replace("Rp.", "");
        String amounts = amount.replaceAll("\\s","");
        Integer integerObject = Integer.valueOf(amounts);
        int finalAmount = integerObject.intValue();
        System.out.println(finalAmount);
        Assertions.assertEquals(finalAmount, testingData.getAmountPayment());

        // user confirm to do payment
        By BUTTON_PAID = MobileBy.id("btnNext");
        click(BUTTON_PAID);

        // assertion hasil pengujian
        By LABEL_SUCCESS_MESSAGE = MobileBy.id("messageSucces");
        AndroidElement findSuccessMessage = driver.findElement(LABEL_SUCCESS_MESSAGE);
        Assertions.assertEquals("Transaksi Berhasil", findSuccessMessage.getText());

        //quit
        AndroidDriverInit.quit();
    }

    @Test
    public void userFailedPaidWithIncorrectCredentials(){
        //call driver method to create session (mandatory step to run automation)
        initialize();
        // user go to sale menu
        By BUTTON_MENU_SALE = MobileBy.id("menuSale");
        click(BUTTON_MENU_SALE);
        //verify user on sale menu
        By LABEL_SALE_MENU = MobileBy.id("txtTitle");
        AndroidElement findSaleMenu = driver.findElement(LABEL_SALE_MENU);
        Assertions.assertEquals("Selamat Datang di Menu Payment", findSaleMenu.getText());

        // user input nominal payment on input nominal
        By INPUT_NOMINAL = MobileBy.id("nominal");
        input(INPUT_NOMINAL, "2500000");

        // parsing string value nominal payment to integer
        String payment2 = find(INPUT_NOMINAL).getText();
        Integer intObject2 = Integer.valueOf(payment2);
        int finalPayment2 = intObject2.intValue();

        // set amount final
        testingData.setAmountPayment(finalPayment2);

        // user input card atm on input card
        By INPUT_CARD = MobileBy.id("carduser");
        input(INPUT_CARD, testingData.getCardAtm());

        // user input pin atm on input pin
        By INPUT_PIN = MobileBy.id("pin");
        input(INPUT_PIN, testingData.getPinAtm() + random);

        // user verifikasi total payment is corrected
        By FINAL_AMOUNT = MobileBy.id("totalPayment");
        String amount2 = find(FINAL_AMOUNT).getText().replace("Rp.", "");
        String amounts2 = amount2.replaceAll("\\s","");
        Integer integerObject = Integer.valueOf(amounts2);
        int finalAmount2 = integerObject.intValue();
        System.out.println(finalAmount2);
        Assertions.assertEquals(finalAmount2, testingData.getAmountPayment());

        // user confirm to do payment
        By BUTTON_PAID = MobileBy.id("btnNext");
        click(BUTTON_PAID);

        // assertion hasil pengujian
        By LABEL_FAILED_MESSAGE = MobileBy.id("messageFail");
        AndroidElement findSuccessMessage = driver.findElement(LABEL_FAILED_MESSAGE);
        Assertions.assertEquals("Transaksi Gagal", findSuccessMessage.getText());

        //quit
        AndroidDriverInit.quit();
    }
}
