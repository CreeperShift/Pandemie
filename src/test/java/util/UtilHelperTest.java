package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.pandemie.util.UtilHelper;

@DisplayName("UtilHelper")
public class UtilHelperTest {

    private static String[] values;

    @BeforeAll
    static void setup(){
        values = new String[]{"--", "-", "o", "+", "++"};
    }

    @Test
    void getStringValuesToNumerical(){
        int i = -2;
        for(String s : values){
            int result = UtilHelper.stringValueToNumeric(s);
            Assertions.assertEquals(i ,result);
            i++;
        }
    }

    @Test
    void noSuchNumber(){
        int result = UtilHelper.stringValueToNumeric("easdsad");
        Assertions.assertEquals(0, result);
    }
}
