package com.compilergroup.minilang;
import java.util.LinkedHashMap; import java.util.Map;
public class SymbolTable {
private final Map<String, ValueType> symbols =
        new LinkedHashMap<>();

// Add a new variable
public void define(String name, ValueType type) {
    symbols.put(name, type);
}

// Check whether a variable exists
public boolean contains(String name) {
    return symbols.containsKey(name);
}

// Get the type of a variable
public ValueType getType(String name) {
    return symbols.getOrDefault(name, ValueType.UNKNOWN);
}

// Update an existing variable
public void update(String name, ValueType type) {
    symbols.put(name, type);
}

// Return all stored variables
public Map<String, ValueType> getAllSymbols() {
    return new LinkedHashMap<>(symbols);
}

// Clear the symbol table
public void clear() {
    symbols.clear();
}
}
