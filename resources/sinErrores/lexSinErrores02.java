//(equivalente a 123 × 10^4)

//caso 1

//----- Char test -----------/

'e'
'1'
        '\"'
        '\3'
        '\n'
        '\t'
        '\b'
        '\\'



//-------- String test ------------/

"hola don pepito"

//caso 2
"hola don jose"

//caso 3
"hola \"chicken\" bebe"

//caso 4
"El dijo: \"Hola, ¿cómo estás?\""

//caso 5
"Este es un ejemplo con una barra invertida: \\"

//caso 10
"Este es un string con comillas simples: 'texto'"

//caso 11
" "

//caso 6
"Este es un ejemplo con una barra invertida: \n"

//caso 7
"\"Esto es una \"cadena\" con \\varias\\ comillas y barras\""

//caso 8
""

//caso 9
"\\"

//caso 12
        "Un string con caracteres especiales: !@#$%^&*()"






//------------------------- float test ------------------------------------/
testCases = {
        1.23e4,     // Notación científica
        1.23E4,     // Notación científica con 'E'
        1.23e-4,    // Notación científica con exponente negativo
        1.23E+4,    // Notación científica con exponente positivo
        123e4,      // Notación científica sin parte fraccionaria
        123E-4,     // Notación científica sin parte fraccionaria, exponente negativo
        0e4,        // Notación científica con 0
        0.456e-2,   // Decimal con notación científica
        .456e3,     // Decimal sin parte entera, con notación científica
        0e4,        // CASO ESPECIAL
        1e03,      // CASO ESPECIAL
        24e04,     // CASO ESPECIAL
        39E2       // CASO ESPECIAL
        };


//otros casos
23E2e3
23e2e3
23e2e3e4
23e2e3e4e5
23e2e3e-4
23e2e3e-4e5
23e2e3e-4e-5

        3234234
        23232
        e3334
        222111

//------------------------- int test ------------------------------------/
        012345

        /*2147483647 // Long int
        2147483648 // lomg int
        9223372036854775807
        9223372036854775808*/
        123.12.1