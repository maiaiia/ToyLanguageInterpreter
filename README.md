# Toy Language Interpreter

A Java-based interpreter for a custom imperative language featuring heap memory management, garbage collection, file I/O, and concurrent execution.

## Features

- **Execution engine**: Stack-based execution with symbol tables, heap, and file management
- **Memory management**: Heap allocation with automatic garbage collection
- **Type system**: Static typing with `int`, `bool`, `string`, and reference types (`Ref<T>`)
- **Concurrency**: Multi-threaded program execution (to be added)
- **File operations**: Read/write text files from programs
- **Control flow**: If statements, while loops, compound statements

## Architecture

Built using **MVC pattern** with:
- **Model**: Statements, expressions, types, values, generic ADT implementations (Stack, Dictionary, List, Heap)
- **Repository**: Program state management with execution logging
- **Controller**: Step-by-step execution and garbage collection
- **View**: Text-based menu for program selection and execution (GUI to be added)

---

Built as part of Advanced Programming Methods coursework at Babeș-Bolyai University.
