package ru.netology.web.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.netology.web.data.DataHelper.CardData.*;

public class BuyOnCreditTestAPI {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    @DisplayName("1. API test. Credit by approved card. Entering valid values")
    void shouldAPICreditByApprovedCardWithValidValues() {
        var validCard = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(5, "MM"),
                generateDate(24, "YY"));
        given()
                .spec(requestSpec)
                .body(validCard)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    @Test
    @DisplayName("2. API test. Credit by approved card. Checking for the current month and the current year")
    void shouldAPICreditByApprovedCardWithCurrentMonthYear() {
        var validCard = getCardWithParam(getApprovedNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(0, "MM"),
                generateDate(0, "YY"));
        given()
                .spec(requestSpec)
                .body(validCard)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));

    }

    @Test
    @DisplayName("3. API test. Credit by decline card. Entering valid values")
    void shouldAPICreditByDeclineCardWithValidValues() {
        var card = getCardWithParam(getDeclineNumbCard(),
                generateName("en"), generateValidCVC(),
                generateDate(12, "MM"),
                generateDate(15, "YY"));
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    @Test
    @DisplayName("4. API test. Credit by RANDOM number card. Other values valid")
    void shouldAPICreditByRANDOMCard() {
        var card = getCardWithParam("1111 1111 1111 1111",
                generateName("en"), generateValidCVC(),
                generateDate(12, "MM"),
                generateDate(15, "YY"));
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(500)
                .body("error", equalTo("Internal Server Error"));
    }
}