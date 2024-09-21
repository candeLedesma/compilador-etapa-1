package analizadorlexico;

public class Token {
    private String id;
    private String lexema;
    private int numeroLinea;

    public Token(String id, String lexema, int numeroLinea) {
        this.id = id;
        this.lexema = lexema;
        this.numeroLinea = numeroLinea;
    }

    public String getId() {
        return id;
    }

    public String getLexema() {
        return lexema;
    }

    public String getLineNumber() {
        return String.valueOf(numeroLinea);
    }
}
