package at.ac.fhcampuswien.swt.numbersinwords;

import at.ac.fhcampuswien.swt.numbersinwords.exceptions.InputNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class NumbersInWordsShell {
    
    private final NumberConverterService numberConverterService;
    
    @ShellMethod("Add number to convert")
    public String convert(String inputString) throws InputNotValidException {
        return numberConverterService.convert(inputString);
    }
}
