package features;

import annotation.Regression;
import annotation.SmokeRegression;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import utilities.AppProperty;

@DisplayName("This is Session 9 Test")
@Tag("se9")
public class Session9Test {

    @Test
    @DisplayName("This is first test")
    void test1(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @Regression
    @DisplayName("This is second test")
    void aest_2_new(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @SmokeRegression
    @Disabled("Waiting for bug fix 445")
    @DisplayName("This is third test")
    void fskhfs_3_new(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @RepeatedTest(3)
    void repeat_test(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 2) {
            String name = new Faker().funnyName().name();
            System.out.println("name = " + name);
        } else
            System.out.println("no new data");
    }

    @ParameterizedTest(name = "[{index}] with data {arguments}")
    @ValueSource(ints = {1, 2, 435, 435345})
    void para_test(int age) {
        System.out.println("age = " + age);
    }

    @ParameterizedTest(name = "Run number [{index}] with data {arguments}")
    @ValueSource(strings = {"1#$%", "@gmail", "fff"})
    void para_test_2(String email) {
        System.out.println("email = " + email);
    }

    @ParameterizedTest(name = "Run number [{index}] with data {arguments}")
    @EnumSource(AppProperty.class)
    void para_test_3(AppProperty appProperty) {
        System.out.println("appProperty = " + appProperty);
    }

    @ParameterizedTest(name = "Run number [{index}] with data {arguments}")
    @CsvSource(value = {
            "giang,20,giang@gmail.com",
            "tuan,40,tuansaco@gmail.com",
    })
    void para_test_4(String name, int age, String email) {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("email = " + email);
    }

    @ParameterizedTest(name = "Run number [{index}] with data {arguments}")
    @CsvFileSource(resources = "/register.csv", numLinesToSkip = 1)
    void para_test_5(String name, int age, String email) {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("email = " + email);
    }
}
