# COMPILER_GROUP_SIXTEEN

*A Group 16 project for Introduction to Compilers.*

## About Our Project

Our group is developing a compiler that translates programs written in a simple programming language into a form that a computer can process.

The project is being developed in stages, with each stage responsible for a specific part of the compilation process.

## Our Programming Language: MiniLang

We are developing a simple programming language called **MiniLang**. It supports basic programming concepts and is designed to demonstrate how a compiler works.

The language supports features such as:

- Declaring and assigning values to variables
- Working with integers, decimal numbers, and strings
- Performing arithmetic and comparison operations
- Displaying output using `DISPLAY`
- Making decisions using `IF` and `ELSE`
- Repeating instructions using `WHILE` and `FOR`
- Adding comments using `//`

## Compiler Development Stages

Our project is divided into five main stages:

1. **Scanner (Lexical Analysis):** Reads source code and separates it into tokens, including keywords, identifiers, numbers, and operators.
2. **Parser (Syntax Analysis):** Checks whether the tokens follow the grammatical rules of MiniLang and constructs an Abstract Syntax Tree (AST).
3. **Semantic Analysis and Intermediate Representation (IR):** Checks whether programs are logically meaningful, identifies semantic errors, verifies types and prepares an intermediate representation.
4. **Code Generation:** Converts the intermediate representation into the selected target code or representation.
5. **Optimization and Final Demonstration:** Improves the generated code where possible and demonstrates the completed compiler.

## Current Development Progress: Phase 3

We have progressed through the initial stages of compiler development and are currently working on **Phase 3: Semantic Analysis and Intermediate Representation (IR)**.

Our current focus includes:

- Defining and checking data types
- Managing variables through a symbol table
- Detecting semantic errors
- Checking type compatibility
- Analysing expressions and assignments
- Generating an intermediate representation of the source program

The successful completion of this phase will prepare the compiler for code generation and subsequent optimization.

## Technologies and Tools

- **Programming Language:** Java
- **Build Tool:** Apache Maven
- **Code Editor:** Visual Studio Code
- **Version Control:** Git and GitHub

## Group Members

| Index Number | Full Name | GitHub Username |
|---|---|---|
| PS/CSC/23/0171 | Emmanuella Odonkor Kwarteng | [SHS-Inspirations](https://github.com/SHS-Inspirations) |
| PS/CSC/23/0172 | Andy Gyasi Asare | [AndyDhausty](https://github.com/AndyDhausty) |
| PS/CSC/23/0174 | Arthur Collins | [Jojocandoit](https://github.com/Jojocandoit) |
| PS/CSC/23/0175 | Acquah Boakye Emmanuel | [kay0004](https://github.com/kay0004) |
| PS/CSC/23/0176 | Bismark Nyarko | [bismark432-hash](https://github.com/bismark432-hash) |
| PS/CSC/23/0177 | Tahiru Tijani Dawuda | [OneCydeGH](https://github.com/OneCydeGH) |
| PS/CSC/23/0178 | Tetteh Robert Kwaku | [Robbo77](https://github.com/Robbo77) |
| PS/CSC/23/0179 | Adjei Collins | [Snilloc-7](https://github.com/Snilloc-7) |
| PS/CSC/23/0180 | Evans Obiri Yeboah | [evans06-dot](https://github.com/evans06-dot) |
| PS/CSC/23/0181 | Christian Donkor | [Techphlakes](https://github.com/Techphlakes) |

---

**Course:** Introduction to Compilers  
**Group:** 16  
**Project:** MiniLang Compiler