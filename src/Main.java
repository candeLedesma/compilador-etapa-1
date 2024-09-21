import Excepciones.ExcepcionLexica;
import analizadorlexico.AnalizadorLexico;
import analizadorlexico.Token;
import sourcemanager.SourceManager;
import sourcemanager.SourceManagerImpl;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("El argumento debe ser la ruta del archivo fuente.");
            return;
        }

        String sourceFile = args[0];
        SourceManager sourceManager = new SourceManagerImpl();
        Token token = null;
        boolean catchError = false;

        try {
            sourceManager.open(sourceFile);
            AnalizadorLexico lexer = new AnalizadorLexico(sourceManager);

            do {
                try {
                    token = lexer.proximoToken();
                    System.out.println(mostrarToken(token));
                } catch (ExcepcionLexica e) {
                    catchError = true;
                    manejarExcepcionLexica(e);
                }
            } while (token != null && !token.getId().equals("EOF"));

            if (!catchError) {
                System.out.println(msjSinErrores());
            } else {
                System.out.println(msjConErrores());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sourceManager.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    private static String mostrarToken(Token token) {
        return "(" + token.getId() + "," + token.getLexema() + "," + token.getLineNumber() + ")";
    }

    private static String msjConErrores() {
        return "[ConErrores]";
    }
    private static String msjSinErrores() {
        return "[SinErrores]";
    }

    private static void manejarExcepcionLexica(ExcepcionLexica e) {
        System.out.println(e.getMessage());
        String detalle = "Detalle: ";
        System.out.println(detalle + e.getLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < e.getIndexNumber() + detalle.length() - 1; i++) {
            sb.append(" ");
        }
        sb.append("^");
        System.out.println(sb.toString());
        System.out.println(e.getErrorMessage());
    }
}
