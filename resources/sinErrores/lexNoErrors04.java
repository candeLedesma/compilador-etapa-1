///[SinErrores]
public class ExampleProgram {
    public static void main(String args) {
        int number = 100 % 10;
        char character = 'a';
        String str = "Hello, World!";
        boolean flag = true;

        if (number == 5 && flag || number <= 10 || !(number != 4) && number >= 0 ){
            str = "Number is greater than 5";
            number -=2;
        } else {
            str = "Number is not greater than 5";
            number += 2;
            number = number *2 /3;
        }
         /* This is a
           multi-line comment */

        number = str.length();
        switch (character) {
            case 'a':
                str = "Character is 'a'";
                break;
            case 'b':
                str = "Character is 'b'";
                break;

        }
    }
}