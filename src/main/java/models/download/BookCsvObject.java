package models.download;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class BookCsvObject {
    @CsvBindByName
    private String Title;
    @CsvBindByName
    private String Author;
    @CsvBindByName
    private String Genre;
    @CsvBindByName
    private int Height;
    @CsvBindByName
    private String Publisher;
}
