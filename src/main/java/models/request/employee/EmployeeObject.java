package models.request.employee;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeObject {
    private AccountObject account;
    private List<String> colors;
    private double star;
}
