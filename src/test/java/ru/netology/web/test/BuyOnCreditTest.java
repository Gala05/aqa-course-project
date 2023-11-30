package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.web.data.DataHelper.CardData;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.page.CardPaymentPage.*;


public class BuyOnCreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("1. Payment by approved card. Entering valid values")
    void shouldPaymentByApprovedCardWithValidValues() {
        //open("http://localhost:8080");
        //Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "200x900";
        var validCard = DataHelper.CardData.getCard();
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        cardNumberField.setValue(validCard.getNumber());
        monthField.setValue(validCard.getMonth());
        monthField.setValue(DataHelper.CardData.generateDate(0, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(5,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        successfulMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("2. Payment by approved card. Сhecking for the current month and the current year")
    void shouldPaymentByApprovedCardWithCurrentMonthYear() {

    }

    @Test
    @DisplayName("3. Payment by decline card. Entering valid values")
    void shouldPaymentByDeclineCardWithValidValues() {

    }

    @Test
    @DisplayName("4. Checking for an empty card number field")
    void shouldCheckingForAnEmptyCardNumberField() {

    }

    @Test
    @DisplayName("5. Checking for an empty month field")
    void shouldCheckingForAnEmptyMonthField() {

    }

    @Test
    @DisplayName("6. Checking for an empty year field")
    void shouldCheckingForAnEmptyYearField() {

    }

    @Test
    @DisplayName("7. Checking for an empty holder field")
    void shouldCheckingForAnEmptyHolderField() {

    }

    @Test
    @DisplayName("8. Checking for an empty CVC/CVV field")
    void shouldCheckingForAnEmptyCVCField() {

    }

    @Test
    @DisplayName("9. Checking for the previous month with approved card")
    void shouldCheckingForPreviousMonthWithApprovedCard() {

    }

    @Test
    @DisplayName("10. Checking for the invalid month with approved card")
    void shouldCheckingForInvalidMonthWithApprovedCard() {

    }

    @Test
    @DisplayName("11. Checking for the previous year with approved card")
    void shouldCheckingForPreviousYearWithApprovedCard() {

    }

    @Test
    @DisplayName("12. Checking for field Year over 5 years")
    void shouldCheckingForFieldYearOverFiveYears() {

    }

    @Test
    @DisplayName("13. Checking for 1 digit for the Month field")
    void shouldCheckingForOneDigitForTheMonthField() {

    }

    @Test
    @DisplayName("14. Checking for Cyrillic in the Holder field")
    void shouldCheckingForCyrillicInHolderField() {

    }

    @Test
    @DisplayName("15. Checking for 1 digit for the CVC field")
    void shouldCheckingForOneDigitForTheCVCField() {

    }

    @Test
    @DisplayName("16. Checking for 15 digit for the card number field")
    void shouldCheckingFor15DigitForTheCardNumberField() {

    }
}