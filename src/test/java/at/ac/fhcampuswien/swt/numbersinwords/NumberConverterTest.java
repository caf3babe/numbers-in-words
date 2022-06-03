package at.ac.fhcampuswien.swt.numbersinwords;

import at.ac.fhcampuswien.swt.numbersinwords.exceptions.InputNotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberConverterTest {
    
    private NumberConverterService numberConverterService = null;
    
    @BeforeEach
    void init(){
        this.numberConverterService = new NumberConverterService();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"-99", "33.3", "234,3", "$234.23", "1000000000"})
    void ensure_invalid_input_throws_exception(String inputValue){
        assertThrows(InputNotValidException.class, () -> {this.numberConverterService.convert(inputValue);}) ;
    }
    
    @Test
    void input_745_generates_seven_hundred_and_forty_five() throws InputNotValidException {
        // arrange
        String inputValue = "745";
        
        // act
        String actualOutput = this.numberConverterService.convert(inputValue);
        String expectedOutput = "seven hundred and forty five";
        
        // assert
        assertEquals(expectedOutput, actualOutput);
    }
    
    @ParameterizedTest
    @CsvSource({"10,ten", "11,eleven", "12,twelve", "13,thirteen", "14,fourteen", "15,fifteen", "16,sixteen", "17,seventeen","18,eighteen","19,nineteen"})
    void ensure_special_numbers_translate_correctly( String inputValue, String expectedValue)
            throws InputNotValidException {
        String actualValue = this.numberConverterService.convert(inputValue);
        assertEquals(expectedValue, actualValue);
    }
    
    @ParameterizedTest
    @CsvSource({"1221,one thousand two hundred and twenty one", "4334,four thousand three hundred and thirty four", "2349,two thousand three hundred and forty nine", "1151,one thousand one hundred and fifty one", "6969,six thousand nine hundred and sixty nine", "9972,nine thousand nine hundred and seventy two", "8888,eight thousand eight hundred and eighty eight", "9911,nine thousand nine hundred and eleven"})
    void ensure_proper_tens_handling_over_thousand( String inputValue, String expectedValue)
            throws InputNotValidException {
        String actualValue = this.numberConverterService.convert(inputValue);
        assertEquals(expectedValue, actualValue);
    }
    
    @Test
    void input_greater_than_999_includes_thousand_as_string() throws InputNotValidException {
        String inputValue = "1111";
        String actualValue = this.numberConverterService.convert(inputValue);
        String expectedValue = "one thousand one hundred and eleven";
        assertEquals(expectedValue, actualValue);
    }
    
    // Waleed, for you
    @Test
    void input_greater_than_999999_includes_million_as_string(){
    }
    
}
