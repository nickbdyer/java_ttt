package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Game().run(new Display(new Scanner(System.in), System.out), new Board());
    }

}
