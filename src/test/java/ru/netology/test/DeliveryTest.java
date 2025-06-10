package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class DataGeneratorTest {

    @BeforeEach
    void setUpAll (){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown (){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {

        var user = DataGenerator.generateUser();
        var validUser = DataGenerator.generateUser();
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting, "dd.MM.yyyy");

        $("[data-test-id='city'] input").setValue(String.valueOf(user.getCity()));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(String.valueOf(user.getName()));
        $("[data-test-id='phone'] input").setValue(String.valueOf(user.getPhone()));
        $("[data-test-id='agreement']").click();
        $$(".button").find(Condition.exactText("Запланировать")).click();
        $(Selectors.byCssSelector("[data-test-id='success-notification']")).shouldBe(visible, Duration.ofSeconds(5));
        $(Selectors.byCssSelector("[data-test-id='success-notification']"))
                .shouldHave(visible, Condition.exactText("Успешно! Встреча успешно запланирована на " + firstMeetingDate));
        $$(".button").find(Condition.exactText("Запланировать")).click();
        $(Selectors.byCssSelector("[data-test-id='replan-notification']")).shouldBe(visible);
        $(Selectors.byCssSelector("[data-test-id='replan-notification']"))
                .shouldHave(visible, Condition.text("Необходимо подтверждение"));
        $$(".button").find(Condition.exactText("Перепланировать")).click();
        $(Selectors.byCssSelector("[data-test-id='success-notification']"))
                .shouldHave(visible, Condition.exactText("Успешно! Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $$(".button").find(Condition.exactText("Запланировать")).click();
        $(Selectors.byCssSelector("[data-test-id='replan-notification']"))
                .shouldHave(visible, Condition.text("Необходимо подтверждение"));
        $$(".button").find(Condition.exactText("Перепланировать")).click();
        $(Selectors.byCssSelector("[data-test-id='success-notification']"))
                .shouldHave(visible, Condition.exactText("Успешно! Встреча успешно запланирована на " + secondMeetingDate));
    }
}
