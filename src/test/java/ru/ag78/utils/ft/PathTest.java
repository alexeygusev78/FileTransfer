package ru.ag78.utils.ft;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

    @Test
    public void pathTest() {

        System.out.println(".pathTest");
        Path p = Paths.get("/Users/alexey/test.txt");
        System.out.println(p.getFileName());
    }
}
