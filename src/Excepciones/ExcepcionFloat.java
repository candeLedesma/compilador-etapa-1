package Excepciones;

public class ExcepcionFloat extends ExcepcionLexica{
    public ExcepcionFloat(String lexema, int lineNumber, int indexNumber, String line) {
        super(lexema, lineNumber, indexNumber, line);
    }

    public String getMessage() {
        return "Error float " + lexema + " en la linea " + lineNumber + " y en columna "+indexNumber+ ".\n";
    }
}
