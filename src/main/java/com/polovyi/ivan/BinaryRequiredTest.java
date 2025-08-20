package com.polovyi.ivan;// BinaryRequiredTest.java
import java.util.*;
import java.io.*;

public class BinaryRequiredTest {
    // ✅ Detected WITHOUT bytecode
    private void unusedMethod() {}

    // ❓ Test these scenarios:

    // Cross-method null flow
    private String getData() {
        return null;
    }

    public void testNull() {
        String s = getData();
        System.out.println(s.length()); // NPE - needs bytecode?
    }

    // Collections API misuse
    public void testImmutable() {
        List<String> list = Arrays.asList("a", "b");
        list.add("c"); // UnsupportedOperationException - needs bytecode?
    }

    // Resource leak
    public void leak() throws IOException {
        FileInputStream fis = new FileInputStream("file");
        // Not closed - needs bytecode?
    }

    // Integer comparison
    public void compare() {
        Integer a = 1000;
        Integer b = 1000;
        if (a == b) { // Should use .equals() - needs bytecode?
            System.out.println("Equal");
        }
    }
}