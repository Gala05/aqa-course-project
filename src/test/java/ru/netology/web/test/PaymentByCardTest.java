package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.data.DataHelper.CardData.*;
import static ru.netology.web.page.CardPaymentPage.*;


public class PaymentByCardTest {

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
        var validCard = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(5, "MM"),
                generateDate(24, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(validCard);
        successfulMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("2. Payment by approved card. Checking for the current month and the current year")
    void shouldPaymentByApprovedCardWithCurrentMonthYear() {
        var validCard = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(0, "MM"),
                generateDate(0, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(validCard);
        successfulMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("3. Payment by decline card. Entering valid values")
    void shouldPaymentByDeclineCardWithValidValues() {
        var card = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(12, "MM"),
                generateDate(15, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        declineMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("4. Checking for an empty card number field")
    void shouldCheckingForAnEmptyCardNumberField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);

        monthField.setValue(generateDate(2, "MM"));
        yearField.setValue(generateDate(18, "YY"));
        holderField.setValue(generateName("en"));
        cvcField.setValue(generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderCardNumberField.should(appear);
    }

    @Test
    @DisplayName("5. Checking for an empty month field")
    void shouldCheckingForAnEmptyMonthField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(getDeclineNumbCard());

        yearField.setValue(generateDate(26, "YY"));
        holderField.setValue(generateName("en"));
        cvcField.setValue(generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("6. Checking for an empty year field")
    void shouldCheckingForAnEmptyYearField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(getDeclineNumbCard());
        monthField.setValue(generateDate(15, "MM"));

        holderField.setValue(generateName("en"));
        cvcField.setValue(generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderYearField.should(appear);
    }

    @Test
    @DisplayName("7. Checking for an empty holder field")
    void shouldCheckingForAnEmptyHolderField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(getDeclineNumbCard());
        monthField.setValue(generateDate(0, "MM"));
        yearField.setValue(generateDate(0, "YY"));

        cvcField.setValue(generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderHolderField.should(appear);
    }

    @Test
    @DisplayName("8. Checking for an empty CVC/CVV field")
    void shouldCheckingForAnEmptyCVCField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(getDeclineNumbCard());
        monthField.setValue(generateDate(0, "MM"));
        yearField.setValue(generateDate(0, "YY"));
        holderField.setValue(generateName("en"));

        buttonContinue.click();
        messageAboutRequiredFieldUnderCvcField.should(appear);
    }

    @Test
    @DisplayName("9. Checking for the previous month with approved card")
    void shouldCheckingForPreviousMonthWithApprovedCard() {
        var card = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(-1, "MM"),
                generateDate(0, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageAboutValidityPeriodUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("10. Checking for the invalid month with approved card")
    void shouldCheckingForInvalidMonthWithApprovedCard() {
        var card = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                getInvalidMonth(),
                generateDate(0, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageAboutValidityPeriodUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("11. Checking for the previous year with approved card")
    void shouldCheckingForPreviousYearWithApprovedCard() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(0, "MM"),
                generateDate(-12, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageAboutValidityPeriodUnderYearField.should(appear);
    }

    @Test
    @DisplayName("12. Checking for field Year over 5 years")
    void shouldCheckingForFieldYearOverFiveYears() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(0, "MM"),
                generateDate(72, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageAboutInvalidityPeriodUnderYearField.should(appear);
    }

    @Test
    @DisplayName("13. Checking for 1 digit for the Month field")
    void shouldCheckingForOneDigitForTheMonthField() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                getMonthWithOneDigit(),
                generateDate(3, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("14. Checking for Cyrillic in the Holder field")
    void shouldCheckingForCyrillicInHolderField() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateName("ru"), generateValidCVC(),
                generateDate(5, "MM"),
                generateDate(9, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageUnderHolderField.should(appear);
    }

    @Test
    @DisplayName("15. Checking for special characters in the Holder field")
    void shouldCheckingForSpecialCharactersInHolderField() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateNameWithSpecChar(), generateValidCVC(),
                generateDate(6, "MM"),
                generateDate(30, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageUnderHolderField.should(appear);
    }

    @Test
    @DisplayName("16. Checking for 1 digit for the CVC field")
    void shouldCheckingForOneDigitForTheCVCField() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateName("ru"), generateInvalidCVC(),
                generateDate(1, "MM"),
                generateDate(27, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageUnderCvcField.should(appear);
    }

    @Test
    @DisplayName("17. Checking for 15 digit for the card number field")
    void shouldCheckingFor15DigitForTheCardNumberField() {
        var card = getCardWithParam(getCardNumberWith15Symbols(),
                generateName("ru"), generateValidCVC(),
                generateDate(7, "MM"),
                generateDate(9, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageUnderCardNumberField.should(appear);
    }

    @Test
    @DisplayName("18. Checking for the field Month 00 with approved card")
    void shouldCheckingForMonth00() {
        var card = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                getMonth00(),
                generateDate(9, "YY"));
        mainPage.shouldBe(visible);
        buttonPay.click();
        fillingFieldsPaymentPage(card);
        messageAboutValidityPeriodUnderMonthField.should(appear);
    }
}