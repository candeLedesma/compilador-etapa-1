package Excepciones;

public class ExcepcionFloat extends ExcepcionLexica{

    String floatFueraDeRango;
    public ExcepcionFloat(String lexema, int lineNumber, int indexNumber, String line) {
        super(lexema, lineNumber, indexNumber, line);
    }

    public ExcepcionFloat(String lexema, int lineNumber, int index, String line, String floatFueraDeRango) {
        super(lexema, lineNumber, index, line);
        this.floatFueraDeRango = floatFueraDeRango;
    }

    public String getMessage() {
        return "Error float "+ floatFueraDeRango +lexema  +" en la linea " + lineNumber + " y en columna "+indexNumber+ ".\n";
    }

}
