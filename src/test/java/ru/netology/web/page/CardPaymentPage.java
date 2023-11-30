package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardPaymentPage {
    public static final SelenideElement mainPage = $x("//h2[contains(text(), 'Путешествие дня')]");
    public static final SelenideElement buttonPay = $(".button__text").shouldHave(exactText("Купить"));
    public static final SelenideElement cardPayment = $x("//h3[contains(text(), 'Оплата по карте')]");
    public static final SelenideElement cardNumberField = $x(".//span[text()='Номер карты'] /..//input[contains(@class, " +
            "'input__control')]");
    public static final SelenideElement monthField = $x(".//span[text()='Месяц'] /..//input[contains(@class, " +
            "'input__control')]");
    public static final SelenideElement yearField = $x(".//span[text()='Год'] /..//input[contains(@class, 'input__control')" +
            "]");
    public static final SelenideElement holderField = $x(".//span[text()='Владелец'] /..//input[contains(@class, " +
            "'input__control')]");
    public static final SelenideElement cvcField = $x(".//span[text()='CVC/CVV'] /..//input[contains(@class, " +
            "'input__control')]");
    public static final SelenideElement buttonContinue = $x("//span[text()='Продолжить']//ancestor::button");
    public static final SelenideElement messageUnderCardNumberField = $x("//span[text()='Номер карты'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Неверный формат']");
    public static final SelenideElement messageAboutRequiredFieldUnderCardNumberField = $x("//span[text()='Номер " +
            "карты'] /..//input[contains(@class, 'input__control')] /.. /..//span[text()='Поле обязательно для заполнения']");
    public static final SelenideElement messageUnderMonthField = $x("//span[text()='Месяц'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Неверный формат']");
    public static final SelenideElement messageAboutRequiredFieldUnderMonthField = $x("//span[text()='Месяц'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Поле обязательно для заполнения']");
    public static final SelenideElement messageAboutValidityPeriodUnderMonthField = $x("//span[text()='Месяц'] /." +
            ".//input[contains(@class, 'input__control')] /.. /..//span[text()='Неверно указан срок действия карты']");
    public static final SelenideElement messageYearField = $x("//span[text()='Год'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Неверный формат']");
    public static final SelenideElement messageAboutRequiredFieldUnderYearField = $x("//span[text()='Год'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Поле обязательно для заполнения']");
    public static final SelenideElement messageAboutValidityPeriodUnderYearField = $x("//span[text()='Год'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Истёк срок действия карты']");
    public static final SelenideElement messageAboutInvalidityPeriodUnderYearField = $x("//span[text()='Год'] /." +
            ".//input[contains(@class, 'input__control')] /.. /..//span[text()='Неверно указан срок действия карты']");
    public static final SelenideElement messageUnderHolderField = $x("//span[text()='Владелец'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Неверный формат']");
    public static final SelenideElement messageAboutRequiredFieldUnderHolderField = $x("//span[text()='Владелец'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Поле обязательно для заполнения']");
    public static final SelenideElement messageUnderCvcField = $x("//span[text()='CVC/CVV'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Неверный формат']");
    public static final SelenideElement messageAboutRequiredFieldUnderCvcField = $x("//span[text()='CVC/CVV'] /..//input[contains" +
            "(@class, 'input__control')] /.. /..//span[text()='Поле обязательно для заполнения']");

    public static final SelenideElement successfulMessage = $x("//div[text() = 'Успешно']");
    public static final SelenideElement declineMessage = $x("//div[text() = 'Ошибка! Банк отказал в проведении операции.']");


//    public void validCardPage(DataHelper.CardData card) {
//        mainPage.shouldBe(visible);
//        buttonPay.click();
//        cardPayment.shouldBe(visible);
//        cardNumberField.setValue(card.getNumber());
//        cardNumberField.setValue(card.);

}
