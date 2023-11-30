package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardData {
        private final String number;
        private final String month;
        private final String year;
        private final String holder;
        private final String cvc;

        public static CardData getCard() {
            return new CardData(getApprovedNumbCard(), generateDate(10, "MM"), generateDate(5, "YY"), generateName(
                    "en"), generateValidCVC());
        }

        public static String getApprovedNumbCard() {
            return "1111 2222 3333 4444";
        }

        public static String getDeclineNumbCard() {
            return "5555 6666 7777 8888";
        }

        public static String getCardNumberWith15Symbols() {
            return "1111 2222 3333 444";
        }
        public static String getInvalidMonth() {
            return "13";
        }

        public static String getMonthWithOneDigit() {
            Faker faker = new Faker();
            return faker.numerify("#");
        }

        public static String generateDate(int month, String formatPattern) {
            return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern(formatPattern));
        }

        public static String generateName(String locale) {
            Faker faker = new Faker(new Locale(locale));
            String username = faker.name().firstName();
            String lastName = faker.name().lastName();
            String name = username + " " + lastName;
            return name;
        }

        public static String generateNameWithSpecChar() {
            Faker faker = new Faker();
            String username = faker.name().firstName();
            String lastName = faker.name().lastName();
            String name = username + "% " + lastName;
            return name;
        }

        public static String generateValidCVC() {
            Faker faker = new Faker();
            return faker.numerify("###");
        }

        public static String generateInvalidCVC() {
            Faker faker = new Faker();
            return faker.numerify("##");
        }
    }
}
