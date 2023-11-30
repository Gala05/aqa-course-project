package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
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
        var validCard = DataHelper.CardData.getCard();
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(validCard.getNumber());
        monthField.setValue(validCard.getMonth());
        yearField.setValue(validCard.getYear());
        holderField.setValue(validCard.getHolder());
        cvcField.setValue(validCard.getCvc());
        buttonContinue.click();
        successfulMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("2. Payment by approved card. Checking for the current month and the current year")
    void shouldPaymentByApprovedCardWithCurrentMonthYear() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(0, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(0,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        successfulMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("3. Payment by decline card. Entering valid values")
    void shouldPaymentByDeclineCardWithValidValues() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getDeclineNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(12, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(15,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        declineMessage.should(appear, Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("4. Checking for an empty card number field")
    void shouldCheckingForAnEmptyCardNumberField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);

        monthField.setValue(DataHelper.CardData.generateDate(2, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(18,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderCardNumberField.should(appear);
    }

    @Test
    @DisplayName("5. Checking for an empty month field")
    void shouldCheckingForAnEmptyMonthField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getDeclineNumbCard());

        yearField.setValue(DataHelper.CardData.generateDate(26,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("6. Checking for an empty year field")
    void shouldCheckingForAnEmptyYearField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getDeclineNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(15, "MM"));

        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderYearField.should(appear);
    }

    @Test
    @DisplayName("7. Checking for an empty holder field")
    void shouldCheckingForAnEmptyHolderField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getDeclineNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(0, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(0,"YY"));

        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutRequiredFieldUnderHolderField.should(appear);
    }

    @Test
    @DisplayName("8. Checking for an empty CVC/CVV field")
    void shouldCheckingForAnEmptyCVCField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getDeclineNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(0, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(0,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));

        buttonContinue.click();
        messageAboutRequiredFieldUnderCvcField.should(appear);
    }

    @Test
    @DisplayName("9. Checking for the previous month with approved card")
    void shouldCheckingForPreviousMonthWithApprovedCard() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(-1, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(0,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutValidityPeriodUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("10. Checking for the invalid month with approved card")
    void shouldCheckingForInvalidMonthWithApprovedCard() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.getInvalidMonth());
        yearField.setValue(DataHelper.CardData.generateDate(0,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutValidityPeriodUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("11. Checking for the previous year with approved card")
    void shouldCheckingForPreviousYearWithApprovedCard() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(0, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(-12,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutValidityPeriodUnderYearField.should(appear);
    }

    @Test
    @DisplayName("12. Checking for field Year over 5 years")
    void shouldCheckingForFieldYearOverFiveYears() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(0, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(72,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageAboutInvalidityPeriodUnderYearField.should(appear);
    }

    @Test
    @DisplayName("13. Checking for 1 digit for the Month field")
    void shouldCheckingForOneDigitForTheMonthField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getDeclineNumbCard());
        monthField.setValue(DataHelper.CardData.getMonthWithOneDigit());
        yearField.setValue(DataHelper.CardData.generateDate(3,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageUnderMonthField.should(appear);
    }

    @Test
    @DisplayName("14. Checking for Cyrillic in the Holder field")
    void shouldCheckingForCyrillicInHolderField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(5, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(9,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("ru"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageUnderHolderField.should(appear);
    }

    @Test
    @DisplayName("15. Checking for special characters in the Holder field")
    void shouldCheckingForSpecialCharactersInHolderField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(5, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(9,"YY"));
        holderField.setValue(DataHelper.CardData.generateNameWithSpecChar());
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageUnderHolderField.should(appear);
    }

    @Test
    @DisplayName("16. Checking for 1 digit for the CVC field")
    void shouldCheckingForOneDigitForTheCVCField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getApprovedNumbCard());
        monthField.setValue(DataHelper.CardData.generateDate(6, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(34,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateInvalidCVC());
        buttonContinue.click();
        messageUnderCvcField.should(appear);
    }

    @Test
    @DisplayName("17. Checking for 15 digit for the card number field")
    void shouldCheckingFor15DigitForTheCardNumberField() {
        mainPage.shouldBe(visible);
        buttonPay.click();
        cardPayment.shouldBe(visible);
        cardNumberField.setValue(DataHelper.CardData.getCardNumberWith15Symbols());
        monthField.setValue(DataHelper.CardData.generateDate(7, "MM"));
        yearField.setValue(DataHelper.CardData.generateDate(14,"YY"));
        holderField.setValue(DataHelper.CardData.generateName("en"));
        cvcField.setValue(DataHelper.CardData.generateValidCVC());
        buttonContinue.click();
        messageUnderCardNumberField.should(appear);
    }
}