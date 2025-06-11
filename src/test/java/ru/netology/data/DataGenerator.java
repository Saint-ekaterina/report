package ru.netology.data;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
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
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        return date;
    }

    public static String generateCity() {
//        админимтративные центры РФ
        String [] cities = new String[] {"Пермь", "Ижевск", "Екатеринбург", "Казань", "Оренбург",
                "Самара", "Тольятти", "Новосибирск", "Барнаул", "Киров", "Тюмень"};
//        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
//        // с помощью Faker, либо используя массив валидных городов и класс Random
        return cities[new Random().nextInt(cities.length)];

//        Faker faker = new Faker(Locale.forLanguageTag("ru"));
//        String city = faker.address().city();
//        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
//        // с помощью Faker, либо используя массив валидных городов и класс Random
//        return city;
    }

    public static String generateName() {
        Faker faker = new Faker(Locale.forLanguageTag("ru"));
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String name = (lastName + " " + firstName);
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        return name;
    }

    public static String generatePhone() {
        Faker faker = new Faker(Locale.forLanguageTag("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {
            UserInfo user = new UserInfo(generateCity(), generateName(), generatePhone());
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
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