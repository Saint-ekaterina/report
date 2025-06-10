package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateName() {
        return faker.name().fullName();
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateCity() {

        String[] cities = new String[]{"Пермь", "Ижевск", "Екатеринбург", "Казань", "Оренбург",
                "Самара", "Тольятти", "Новосибирск", "Барнаул", "Киров", "Тюмень"};

        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateDate(int days, String pattern) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }
    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }


}