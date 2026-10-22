# COMPILER_GROUP_TWENTY_FOUR
A group assignment on Introduction to Compilers

# COMPILER_GROUP_TWENTY_FOUR

A group assignment on Introduction to Compilers.

## Project Overview

This project involves designing and implementing a compiler for a proposed mini programming language called **MiniLang**. The language design is a proposal for the group to review and agree on before implementation.

The compiler will be developed in phases, with each phase contributing to the complete compilation process.

## Proposed Language: MiniLang

MiniLang is a proposed mini programming language designed to support basic programming constructs, including:

- Variable declaration and assignment
- Integer, decimal, and string values
- Arithmetic and comparison operations
- Output using `DISPLAY`
- Conditional statements using `IF` and `ELSE`
- Loops using `WHILE` and `FOR`
- Comments using `//`

The final language specification will be confirmed by the group before implementation.

## Compiler Development Phases

1. **Phase 1: Scanner** — Recognizes tokens such as keywords, identifiers, literals, operators, and symbols.
2. **Phase 2: Parser** — Checks whether the token sequence follows the language grammar.
3. **Phase 3: Semantic Analysis and Intermediate Representation** — Checks semantic rules and develops an intermediate representation.
4. **Phase 4: Code Generation** — Translates the program into the selected target representation.
5. **Phase 5: Optimization and Final Demonstration** — Improves the generated representation and demonstrates the completed compiler.

## Phase 1: Scanner

The scanner will read NexLang source code and recognize the tokens required by the proposed language.

Its responsibilities will include:

- Identifying keywords and identifiers
- Recognizing integer, decimal, and string literals
- Recognizing operators and punctuation
- Handling whitespace and comments
- Reporting invalid characters or malformed tokens

## Development Tools

- **Programming language:** Java 26
- **Build tool:** Apache Maven
- **Code editor:** Visual Studio Code
- **Version control:** Git and GitHub

## Project Status

The language design is proposed and awaiting group agreement. Phase 1 will begin after the group confirms the language specification and development setup.
