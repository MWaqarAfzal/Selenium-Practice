import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class PersonalLoan {
    @Test(groups = {"Smoke"})
    public void WebLoginPersonalLoan() {
        System.out.println("Personal Loan --> Hello this is web login personal loan test case");
    }
    @Test
    public void MobileLoginPersonalLoan()
    {
        System.out.println("Personal Loan --> Hello this is mobile login personal loan test case");
    }
    @Test
    public void ApiLoginPersonalLoan()
    {
        System.out.println("Personal Loan --> Hello this is api login personal loan test case");
    }
}
