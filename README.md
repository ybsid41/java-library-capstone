# Library Management System

A command-line Java application for managing library items, members, loans, due dates, fines, and reports based on simulated days.

## How to Run

1. **Open a terminal in the project root** (where your `.java` source files are located).
2. **Compile all Java source files:**
   ```bash
   javac *.java
   ```
3. **Run interactively (type commands line by line and to stop input do ^D):**
   ```bash
   java LibraryApp
   ```
4. **Run from an input file and save output:**
   ```bash
   java LibraryApp < input.txt > actual.txt
   ```
5. **Compare actual output with expected output (macOS / Linux):**
   ```bash
   diff -u expected.txt actual.txt
   ```

## Notes

* **Added `isItemOnLoan` method on `Library`:** I added an `isItemOnLoan` method on `Library` ,that was not shown in the uploaded UML diagram, so `LibraryApp` can check whether an item is currently on loan (and identify the borrower) when printing the `INVENTORY` report without exposing `Library`'s internal `loans` map directly.