package ru.netology.data;


import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    public static UserInfo generateUser() {
        return new UserInfo(
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                faker.address().city()
        );
    }

    public static class UserInfo {
        private final String name;
        private final String phone;
        private final String city;

        public UserInfo(String name, String phone, String city) {
            this.name = name;
            this.phone = phone;
            this.city = city;
        }

        // Getters
        public String getName() { return name; }
        public String getPhone() { return phone; }
        public String getCity() { return city; }
    }

    public static String generateCity() {

        String[] cities = new String[]{"Пермь", "Ижевск", "Екатеринбург", "Казань", "Оренбург",
                "Самара", "Тольятти", "Новосибирск", "Барнаул", "Киров", "Тюмень"};

        return cities[new Random().nextInt(cities.length)];
    }





}