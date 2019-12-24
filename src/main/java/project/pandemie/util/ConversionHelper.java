package project.pandemie.util;

public class ConversionHelper {

    private ConversionHelper(){
    }

    public static int stringValueToNumeric(String value){
        switch(value){
            case "--" : return -2;
            case "-" : return -1;
            case "o" : return 0;
            case "+" : return 1;
            case "++" : return 2;
        }
        return 0;
    }

}
