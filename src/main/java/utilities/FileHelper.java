package utilities;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileHelper {

    public static Path createFile(Response response) {
        String fileName = response.getHeader("Content-Disposition").split("=")[1];
        File file = new File("target/download/" + System.currentTimeMillis() + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.copy(response.asInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static <T> List<T> convertCsvToObject(Path path, Class<T> clazz){
        List<T> books = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(path, UTF_8)){
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(br)
                    .withType(clazz)
                    .build();
            books = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }


}
