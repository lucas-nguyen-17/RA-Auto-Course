package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate year;

    private Person(String name, LocalDate year) {
        this.name = name;
        this.year = year;
    }

    public static Person getInstance() {
        return new Person("Giang", LocalDate.now());
    }
}
