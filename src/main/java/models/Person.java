package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {
    private String name;
    private int age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate year;

    private Person(String name, LocalDate year) {
        this.name = name;
        this.year = year;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person getInstance() {
        return new Person("Giang", LocalDate.now());
    }
}
