package analizadorlexico;
import java.util.HashMap;
import java.util.Map;

public class PalabraClave {

    private static final Map<String, String> PALABRAS_CLAVE;

    static {
        PALABRAS_CLAVE = new HashMap<>();
        PALABRAS_CLAVE.put("if", "pr_if");
        PALABRAS_CLAVE.put("class", "pr_class");
        PALABRAS_CLAVE.put("boolean", "pr_boolean");
        PALABRAS_CLAVE.put("switch", "pr_switch");
        PALABRAS_CLAVE.put("case", "pr_case");
        PALABRAS_CLAVE.put("this", "pr_this");
        PALABRAS_CLAVE.put("extends", "pr_extends");
        PALABRAS_CLAVE.put("char", "pr_char");
        PALABRAS_CLAVE.put("break", "pr_break");
        PALABRAS_CLAVE.put("else", "pr_else");
        PALABRAS_CLAVE.put("int", "pr_int");
        PALABRAS_CLAVE.put("float", "pr_float");
        PALABRAS_CLAVE.put("return", "pr_return");
        PALABRAS_CLAVE.put("void", "pr_void");
        PALABRAS_CLAVE.put("while", "pr_while");
        PALABRAS_CLAVE.put("new", "pr_new");
        PALABRAS_CLAVE.put("true", "pr_true");
        PALABRAS_CLAVE.put("false", "pr_false");
        PALABRAS_CLAVE.put("null", "pr_null");
        PALABRAS_CLAVE.put("public", "pr_public");
        PALABRAS_CLAVE.put("var", "pr_var");
        PALABRAS_CLAVE.put("static", "pr_static");
        PALABRAS_CLAVE.put("default", "pr_default");
    }

    public static boolean esPalabraClave(String lexema) {
        return PALABRAS_CLAVE.containsKey(lexema);
    }

    public static String getPalabraClave(String lexema) {
        return PALABRAS_CLAVE.get(lexema);
    }
}


