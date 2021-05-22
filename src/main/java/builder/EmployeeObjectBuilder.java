package builder;

import models.employee.AccountObject;
import models.employee.EmployeeObject;

import java.util.Arrays;

public class EmployeeObjectBuilder {

    public static EmployeeObject createDefault(){
        EmployeeObject employee = new EmployeeObject();
        employee.setAccount(new AccountObject());
        employee.setColors(Arrays.asList("red", "yellow", "pink"));
        employee.setStar(4.5);
        return employee;
    }

}
