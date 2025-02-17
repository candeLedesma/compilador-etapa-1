# Compiler - lexical analyzer

## Project Description
This project corresponds to the first stage of a compiler developed as part of the **Compilers and Interpreters** course at the **National University of the South**. The goal of this phase is to implement a **lexical analyzer**, which processes a source code, identifies and categorizes different tokens of the language, and properly manages lexical errors.

## Key Features
- **Token recognition:** Identification of keywords, identifiers, literals, and operators.
- **Error handling:** Implementation of exceptions for lexical error detection.
- **Modular organization:** Separation of the lexical analyzer and error handling into independent packages.
- **Detailed error reporting:** Indicates the line and column number of the error and highlights the part of the code where it occurred.

## Installation Instructions
### Requirements
- **Java 8 or higher**
- **Java Compiler (`javac`)**

### Compilation
1. Navigate to the project folder:
   ```sh
   cd /path/to/your/project
   ```
2. Compile the main file `Main.java`:
   ```sh
   javac Main.java
   ```

### Execution
1. Run the program providing an input file:
   ```sh
   java Main /path/to/input/file.txt
   ```
   **Note:** It is recommended to use absolute paths to avoid errors.

## Project Structure
```
/project-root
├── src/
│   ├── lexicalanalyzer/
│   │   ├── LexicalAnalyzer.java
│   │   ├── Keyword.java
│   │   ├── Token.java
│   ├── exceptions/
│   │   ├── LexicalException.java
│   │   ├── LongIntException.java
│   │   ├── FloatException.java
│   ├── Main.java
│
└── README.md
```


## Error Handling and Exceptions
The analyzer handles the following errors:
- **`LexicalException`**: Thrown when an unrecognized symbol is found.
- **`LongIntException`**: Thrown if an integer literal exceeds 9 digits.
- **`FloatException`**: Thrown if a floating-point literal is malformed.

## Achievements
- **Detailed error reporting** with line number, column number, and descriptive message.
- **Handling of floating-point literals** following Java syntax.
- **Error recovery** allowing the analysis to continue after detecting an error.
- **Early submission** 48 hours before the deadline.

## Conclusion
This first stage of the compiler successfully developed a robust and modular lexical analyzer, focusing on error detection and reporting. The project's structure allows for future expansion into later stages, such as syntactic and semantic analysis.

---
Developed by **Candela Ledesma** - **Universidad Nacional del Sur** - 2024

