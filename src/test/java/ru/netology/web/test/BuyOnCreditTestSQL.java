package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.CardData.*;
import static ru.netology.web.data.DataHelper.CardData.generateDate;
import static ru.netology.web.data.SQLHelper.*;
import static ru.netology.web.page.CreditPurchasePage.*;

public class BuyOnCreditTestSQL {
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        var apprCard1 = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(5, "MM"),
                generateDate(24, "YY"));
        buttonPay.click();
        fillingFieldsCreditPage(apprCard1);
        successfulMessage.should(appear, Duration.ofSeconds(15));
        clearingFields();
        var apprCard2 = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(0, "MM"),
                generateDate(0, "YY"));
        buttonPay.click();
        fillingFieldsCreditPage(apprCard2);
        successfulMessage.should(appear, Duration.ofSeconds(15));
        clearingFields();
        var declCard1 = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(10, "MM"),
                generateDate(32, "YY"));
        buttonPay.click();
        fillingFieldsCreditPage(declCard1);
        successfulMessage.should(appear, Duration.ofSeconds(15));
        clearingFields();
        var declCard2 = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(9, "MM"),
                generateDate(40, "YY"));
        buttonPay.click();
        fillingFieldsCreditPage(declCard2);
        successfulMessage.should(appear, Duration.ofSeconds(15));
        clearingFields();
        var declCard3 = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(0, "MM"),
                generateDate(0, "YY"));
        buttonPay.click();
        fillingFieldsCreditPage(declCard3);
        successfulMessage.should(appear, Duration.ofSeconds(15));
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    static void teardown() {
        cleanDataBase();
    }

    @SneakyThrows
    @Test
    void test() {
        Assertions.assertEquals("5", RowCount());
        Assertions.assertEquals("2", approvedRowCountCreditCard());
        Assertions.assertEquals("3", declineRowCountCreditCard());
    }
}
