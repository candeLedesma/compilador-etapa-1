package Excepciones;

public class ExcepcionLongInt extends ExcepcionFloat{
    public ExcepcionLongInt(String lexema, int lineNumber, int indexNumber,String line) {
        super(lexema, lineNumber, indexNumber, line);
    }

    public String getMessage() {
        return "El entero " + lexema + " en la linea " + lineNumber + " y en index "+indexNumber+ " supera el maximo de 9 digitos.\n";
    }

}
