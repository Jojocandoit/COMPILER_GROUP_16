# COMPILER_GROUP_SIXTEEN

*A Group 16 project for Introduction to Compilers.*

## About Our Project

Our group is working on a compiler that will translate programs written in a small programming language into a form a computer can process. We plan to build it step by step, with each stage handling a different part of the translation.

## Our Proposed Language: MiniLang

We are proposing a simple language called **MiniLang**. It is intended to support basic programming features, making it suitable for learning how a compiler works.

The planned features include:

- Declaring and assigning values to variables
- Working with integers, decimal numbers, and strings
- Performing arithmetic and comparison operations
- Displaying output using `DISPLAY`
- Making decisions with `IF` and `ELSE`
- Repeating instructions with `WHILE` and `FOR`
- Adding comments with `//`

We will finalize these features as a group before moving fully into implementation.

## How We Plan to Build the Compiler

Our work is divided into five main stages:

1. **Scanner:** Reads the source code and separates it into tokens, such as keywords, names, and operators.
2. **Parser:** Checks whether the tokens follow the rules of our language.
3. **Semantic Analysis and Intermediate Representation:** Checks whether the program makes sense and prepares an intermediate form for the next stages.
4. **Code Generation:** Converts the intermediate form into the target representation we choose.
5. **Optimization and Final Demonstration:** Looks for ways to improve the generated code and demonstrates the finished compiler.

## Our First Stage: The Scanner

We will start by developing the scanner. Its job is to read the source program and identify the different parts of the code.

The scanner is expected to:

- Recognize keywords and variable names
- Identify numbers and strings
- Detect operators and punctuation marks
- Skip unnecessary spaces and handle comments
- Report characters or tokens that are not valid in our language

## Tools We Plan to Use

- **Programming language:** Java
- **Build tool:** Apache Maven
- **Code editor:** Visual Studio Code
- **Version control:** Git and GitHub

