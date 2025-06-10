package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    DataGenerator() {
    }

    public static String generateDate(int days, String pattern) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    public static String generateCity() {

        String [] cities = new String[] {"Пермь", "Ижевск", "Екатеринбург", "Казань", "Оренбург",
                "Самара", "Тольятти", "Новосибирск", "Барнаул", "Киров", "Тюмень"};

        return cities[new Random().nextInt(cities.length)];



    public static String generateName() {
        Faker faker = new Faker(Locale.forLanguageTag("ru"));
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = (lastName + " " + firstName);
        return name;
    }

    public static String generatePhone() {
        Faker faker = new Faker(Locale.forLanguageTag("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {
            UserInfo user = new UserInfo(generateCity(), generateName(), generatePhone());
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}