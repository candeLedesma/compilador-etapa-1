package Excepciones;

public class ExcepcionLexica extends Throwable {

    protected int lineNumber;

    protected int indexNumber;
    protected String lexema;

    protected String line;

    public ExcepcionLexica(String lexema, int lineNumber, int indexNumber,String line) {
        super();
        this.lexema = lexema;
        this.lineNumber = lineNumber;
        this.indexNumber = indexNumber;
        this.line = line;
    }

    public String getMessage() {
        return "Error lexico en linea " + lineNumber + " con el lexema " + lexema +" en el indice "+indexNumber;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public String getErrorMessage() {
       return "[Error:" + lexema + "|" + lineNumber + "]";
    }

    public String getLine() {
        return line;
    }
}
