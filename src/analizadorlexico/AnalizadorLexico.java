package analizadorlexico;

import Excepciones.ExcepcionFloat;
import Excepciones.ExcepcionLexica;
import Excepciones.ExcepcionLongInt;
import sourcemanager.SourceManager;

import java.io.IOException;
import java.lang.String;


public class AnalizadorLexico {

    private String lexema;
    private char caracterActual;
    private SourceManager sourceManager;
    private int cantDigitos;

    private int lineNumberAnterior;

    private int indexAnterior;
    private String lexemaAnterior;

    private String line;



    public AnalizadorLexico(SourceManager sourceManager) throws IOException {
        this.sourceManager = sourceManager;
        actualizarCaracterActual();
    }

    private void actualizarCaracterActual() throws IOException {
        caracterActual = sourceManager.getNextChar();
    }
    
    public Token proximoToken() throws ExcepcionLexica, IOException {
        lexema = "";
        cantDigitos = 0;
        return estadoInicial();
    }

    private void actualizarLexema(){
        lexemaAnterior = lexema;
        if(caracterActual != '\n')
            lexema += caracterActual;
    }


    private Token esDigito() throws IOException, ExcepcionLexica{
        if(Character.isDigit(caracterActual)){
            cantDigitos++;
            if(cantDigitos > 9){
                guardarEstado();
                actualizarCaracterLexema();
                throw new ExcepcionLongInt(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
            }
            actualizarCaracterLexema();
            return esDigito();
        }else if(caracterActual == 'e' || caracterActual == 'E') {
                actualizarCaracterLexema();
                return s3();
        }else if(caracterActual == '.'){
            actualizarCaracterLexema();
            return s4();
        }else{
            return new Token("intLiteral", lexema, sourceManager.getLineNumber());
        }
    }

    private Token s4() throws IOException, ExcepcionLexica {
        if(Character.isDigit(caracterActual)) {
            actualizarCaracterLexema();
            return s4();
        }else if(caracterActual == 'e' || caracterActual == 'E') {
            actualizarCaracterLexema();
            return s3();
        }else{
            return new Token("floatLiteral", lexema, sourceManager.getLineNumber());
        }
    }

    private Token s3() throws IOException, ExcepcionLexica {
        if(caracterActual == '+' || caracterActual == '-' || Character.isDigit(caracterActual)){
            actualizarCaracterLexema();
            return s5();
        }else{
            guardarEstado();
            actualizarCaracterLexema();
            throw new ExcepcionFloat(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
        }
    }

    private Token s5() throws IOException, ExcepcionLexica {

        if (Character.isDigit(caracterActual)) {
            actualizarCaracterLexema();
            return s5();
        } else {
            if(lexema.charAt(lexema.length()-1) == '+' || lexema.charAt(lexema.length()-1) == '-') {
                guardarEstado();
                actualizarCaracterLexema();
                throw new ExcepcionFloat(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
            }
            return new Token("floatLiteral", lexema, sourceManager.getLineNumber());
        }
    }



    private Token estadoInicial() throws ExcepcionLexica, IOException {
        switch (caracterActual) {
            case '/':
                actualizarCaracterActual();
                return isSlash();

            case '"':
                actualizarCaracterLexema();
                return esString();

            case '\'':
                actualizarCaracterLexema();
                return isChar();

            case '+':
                actualizarCaracterLexema();
                return isPlus();

            case '-':
                actualizarCaracterLexema();
                return isMinus();

            case '*':
                actualizarCaracterLexema();
                return multiplicacion();

            case '%':
                actualizarCaracterLexema();
                return modulo();

            case '!':
                actualizarCaracterLexema();
                return isNot();

            case '=':
                actualizarCaracterLexema();
                return isEquals();

            case '<':
                actualizarCaracterLexema();
                return isLess();

            case '>':
                actualizarCaracterLexema();
                return isGreater();

            case '&':
                actualizarCaracterLexema();
                return isAnd();

            case '|':
                actualizarCaracterLexema();
                return isOr();

            case '(':
                actualizarCaracterLexema();
                return new Token("pn_par_open", lexema, sourceManager.getLineNumber());

            case ')':
                actualizarCaracterLexema();
                return new Token("pn_par_close", lexema, sourceManager.getLineNumber());

            case '{':
                actualizarCaracterLexema();
                return new Token("pn_brace_open", lexema, sourceManager.getLineNumber());

            case '}':
                actualizarCaracterLexema();
                return new Token("pn_brace_close",lexema, sourceManager.getLineNumber());

            case ':':
                actualizarCaracterLexema();
                return new Token("pn_dots", lexema, sourceManager.getLineNumber());

            case ';':
                actualizarCaracterLexema();
                return new Token("pn_semicolon",lexema, sourceManager.getLineNumber());

            case ',':
                actualizarCaracterLexema();
                return new Token("pn_comma",lexema, sourceManager.getLineNumber());

            case '.':
                actualizarCaracterLexema();
                return isDot();


            default:
                if (Character.isDigit(caracterActual)) {
                    cantDigitos++;
                    actualizarCaracterLexema();
                    return esDigito();
                } else if (Character.isLowerCase(caracterActual)) {
                    actualizarCaracterLexema();
                    return esMetoVar();
                } else if (Character.isUpperCase(caracterActual)) {
                    actualizarCaracterLexema();
                    return esClase();
                } else if (caracterActual == sourceManager.END_OF_FILE) {
                    return esEOF();
                } else if (Character.isWhitespace(caracterActual)) {
                    actualizarCaracterActual();
                    return estadoInicial();
                } else {
                    guardarEstado();
                    actualizarCaracterLexema();
                    throw new ExcepcionLexica(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
                }
        }
    }

    private Token isSlash() throws IOException, ExcepcionLexica {
        if (caracterActual == '/') {
            while (caracterActual != '\n' & caracterActual != sourceManager.END_OF_FILE) {
                actualizarCaracterActual();
            }
            actualizarCaracterActual();
            return estadoInicial();
        } else if (caracterActual == '*') {
            actualizarCaracterActual();
            boolean cierreEncontrado = false;
            while (!cierreEncontrado) {
                if (caracterActual == '*') {
                    actualizarCaracterActual();
                    if (caracterActual == '/') {
                        cierreEncontrado = true;
                    }
                } else {
                    actualizarCaracterActual();
                }
                if (caracterActual == sourceManager.END_OF_FILE) {
                    guardarEstado();
                    throw new ExcepcionLexica("",lineNumberAnterior, indexAnterior, line);
                }
            }
            actualizarCaracterActual();
            return estadoInicial();
        } else {
            lexema += '/';
            return new Token("op_div", lexema, sourceManager.getLineNumber());
        }
    }

    private Token isChar() throws IOException, ExcepcionLexica {
        if (caracterActual == '\\') {// barra
            actualizarCaracterLexema();
            if(caracterActual == '\''){
                actualizarCaracterLexema();
            }
        }
        if (caracterActual != sourceManager.END_OF_FILE && caracterActual != '\n' && caracterActual != '\'') {
            actualizarCaracterLexema();
        }
        if (caracterActual == '\'') { //cierrre char
            actualizarCaracterLexema();
            return new Token("charLiteral", lexema, sourceManager.getLineNumber());
        } else {//excepcion char
            guardarEstado();
            actualizarCaracterActual();
            throw new ExcepcionLexica(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
        }
    }

    private Token isPlus() throws IOException {
        if (caracterActual == '=') {
            actualizarCaracterLexema();
            return new Token("op_add_eq", lexema, sourceManager.getLineNumber());
        }else{
            return new Token("op_add",lexema, sourceManager.getLineNumber());
        }
    }

    private Token isMinus() throws IOException {
        if (caracterActual == '=') {
            actualizarCaracterLexema();
            return new Token("op_sub_eq", lexema, sourceManager.getLineNumber());
        } else {
            return new Token("op_sub", lexema, sourceManager.getLineNumber());
        }
    }

    private Token isNot() throws IOException {
        if (caracterActual == '=') {
            actualizarCaracterLexema();
            return new Token("op_not_eq", lexema, sourceManager.getLineNumber());
        } else {
            return new Token("op_not", lexema, sourceManager.getLineNumber());
        }
    }

    private Token isEquals() throws IOException {
        if (caracterActual == '=') {
            actualizarCaracterLexema();
            return new Token("op_eq", lexema, sourceManager.getLineNumber());
        } else {
            return new Token("op_asig", lexema, sourceManager.getLineNumber());
        }
    }

    private Token isLess() throws IOException {
        if (caracterActual == '=') {
            actualizarCaracterLexema();
            return new Token("op_less_eq", lexema, sourceManager.getLineNumber());
        } else {
            return new Token("op_less", lexema, sourceManager.getLineNumber());
        }
    }

    private Token isGreater() throws IOException {
        if (caracterActual == '=') {
            actualizarCaracterLexema();
            return new Token("op_great_eq", lexema, sourceManager.getLineNumber());
        } else {
            return new Token("op_great", lexema, sourceManager.getLineNumber());
        }
    }

    private Token isAnd() throws IOException, ExcepcionLexica {
        if (caracterActual == '&') {
            actualizarCaracterLexema();
            return new Token("op_and", lexema, sourceManager.getLineNumber());
        } else {
            guardarEstado();
            actualizarCaracterLexema();
            throw new ExcepcionLexica(getLexemaAnterior(), lineNumberAnterior, indexAnterior,line);
        }
    }

    private Token isOr() throws IOException, ExcepcionLexica {
        if (caracterActual == '|') {
            actualizarCaracterLexema();
            return new Token("op_or", lexema, sourceManager.getLineNumber());
        } else {
            guardarEstado();
            actualizarCaracterLexema();
            throw new ExcepcionLexica(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
        }
    }

    private Token isDot() throws ExcepcionLexica, IOException {
        if(Character.isDigit(caracterActual)){
            return s4();
        }else{
            return new Token("pn_dot", lexema, sourceManager.getLineNumber());
        }
    }

    private void guardarEstado(){
        lineNumberAnterior = sourceManager.getLineNumber();
        indexAnterior = sourceManager.getIndexNumber();
        line = sourceManager.getCurrentLine();
    }


    private Token esString() throws IOException, ExcepcionLexica {
        while (caracterActual != '\n' && caracterActual != '"' && caracterActual != sourceManager.END_OF_FILE) {
            if (caracterActual == '\\') {
                actualizarCaracterLexema();
            }
            if(caracterActual != sourceManager.END_OF_FILE && caracterActual != '\n') {
                actualizarCaracterLexema();
            }
            if(caracterActual == '"'){
                break;
            }
        }

        if(caracterActual == '"' ){
            actualizarCaracterLexema();
            return new Token("stringLiteral", lexema, sourceManager.getLineNumber());
        }else{
            guardarEstado();
            actualizarCaracterLexema();
            throw new ExcepcionLexica(getLexemaAnterior(), lineNumberAnterior, indexAnterior, line);
        }
    }


    private Token modulo() {
        return new Token("op_mod",lexema, sourceManager.getLineNumber());
    }

    private Token multiplicacion() {
        return new Token("op_mul", lexema, sourceManager.getLineNumber());
    }

    public void actualizarCaracterLexema() throws IOException {
        actualizarLexema();
        actualizarCaracterActual();
    }

    private Token esClase() throws IOException {
        if(Character.isLetter(caracterActual) || Character.isDigit(caracterActual) || caracterActual == '_'){
            actualizarCaracterLexema();
            return esClase();
        }else{
            return new Token("idClase", lexema, sourceManager.getLineNumber());
        }
    }

    private Token esMetoVar() throws IOException {
        if(Character.isLetter(caracterActual) || Character.isDigit(caracterActual) || caracterActual == '_'){
            actualizarCaracterLexema();
            return esMetoVar();
        }else{
            if(PalabraClave.esPalabraClave(lexema)){
                return new Token(PalabraClave.getPalabraClave(lexema),lexema, sourceManager.getLineNumber());
            }else {
                return new Token("idMetVar", lexema, sourceManager.getLineNumber());
            }
        }
    }


    private Token esEOF() {
        lexema = "end_of_file";
        return new Token("EOF", lexema, sourceManager.getLineNumber());
    }

    private String getLexemaAnterior(){
        lexemaAnterior = lexema;
        return lexemaAnterior;
    }


}
