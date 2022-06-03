package at.ac.fhcampuswien.swt.numbersinwords;

import at.ac.fhcampuswien.swt.numbersinwords.exceptions.InputNotValidException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NumberConverterService {
    public String convert(String inputValue) throws InputNotValidException {
        
        StringBuilder outputString = new StringBuilder();
        String reversedString = new StringBuilder(sanitizeInput(inputValue)).reverse().toString();
        
        char [] chars = reversedString.toCharArray();
        
        for( int i = 0; i < chars.length; i++ ) {
            
            switch(i % 3) {
                case 0:
                    if ( i == 0 ) {
                        outputString.insert(0, getUnits(chars[i]));
                    }
                    else if ( i == 3 ) {
                        outputString.insert(0, getUnits(chars[i]).concat(" thousand "));
                    }
                    break;
                case 1:
                    if ( chars[i] == '1' ) {
                        String valueToReplace = getUnits(chars[ i - 1 ]);
                        outputString.replace(0, valueToReplace.length(), "");
                        outputString.insert(0, getSpecials(chars[i] + String.valueOf(chars[i - 1])));
                    } else {
                        outputString.insert(0, getTens(chars[i]).concat(" "));
                    }
                    break;
                case 2:
                    outputString.insert(0, getUnits(chars[i]).concat(" hundred and "));
                    break;
                default:
                    outputString.insert(0, " ".concat(getUnits(chars[i])));
                    break;
            }
        }
        
        return outputString.toString();
    }
    
    private String getSpecials(String specials) {
        switch (specials) {
            case "10":
                return "ten";
            case "11":
                return "eleven";
            case "12":
                return "twelve";
            case "13":
                return "thirteen";
            case "14":
                return "fourteen";
            case "15":
                return "fifteen";
            case "16":
                return "sixteen";
            case "17":
                return "seventeen";
            case "18":
                return "eighteen";
            case "19":
                return "nineteen";
            default:
                return "";
        }
    }
    
    private String getUnits(char character) {
        switch (character) {
            case '0':
                return "zero";
            case '1':
                return "one";
            case '2':
                return "two";
            case '3':
                return "three";
            case '4':
                return "four";
            case '5':
                return "five";
            case '6':
                return "six";
            case '7':
                return "seven";
            case '8':
                return "eight";
            case '9':
                return "nine";
            default:
                return "";
        }
    }
    
    private String getTens(char character) {
        switch (character) {
            case '1':
                return "teen";
            case '2':
                return "twenty";
            case '3':
                return "thirty";
            case '4':
                return "forty";
            case '5':
                return "fifty";
            case '6':
                return "sixty";
            case '7':
                return "seventy";
            case '8':
                return "eighty";
            case '9':
                return "ninety";
            default:
                return "";
        }
    }
    
    private String sanitizeInput(String inputValue) throws InputNotValidException {
        Integer parsedInt;
        try {
             parsedInt = Integer.parseInt(inputValue.replace(" ", ""));
        } catch (Exception e) {
            log.error("The input is invalid: {}", e.getLocalizedMessage());
            throw new InputNotValidException();
        }
        
        if (parsedInt < 0) {
            throw new InputNotValidException("The input must be positive");
        }
        
        if (parsedInt > 999999999) {
            throw new InputNotValidException("The input must be lower than 1 000 000 000");
        }
        return parsedInt.toString();
    }
}
