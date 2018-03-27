package pl.stanislawwozniak;

import java.util.Scanner;
import java.io.IOException;

public class Game {

    private int[][] tab = new int[6][7];
    private Scanner declare = new Scanner(System.in);

    private Game() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tab[i][j] = 0;
            }
        }
    }

    private int move() {
        return declare.nextInt();
    }

    private boolean match() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (tab[i][j] != 0 && tab[i][j] == tab[i][j + 1] &&
                        tab[i][j] == tab[i][j + 2] && tab[i][j] == tab[i][j + 3]) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (tab[i][j] != 0 && tab[i][j] == tab[i + 1][j] &&
                        tab[i][j] == tab[i + 2][j] && tab[i][j] == tab[i + 3][j]) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (tab[i][j] != 0 && tab[i][j] == tab[i + 1][j + 1] &&
                        tab[i][j] == tab[i + 2][j + 2] && tab[i][j] == tab[i + 3][j + 3]) {
                    return false;
                }
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (tab[i][j] != 0 && tab[i][j] == tab[i - 1][j + 1] &&
                        tab[i][j] == tab[i - 2][j + 2] && tab[i][j] == tab[i - 3][j + 3]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void fill(int player) throws InterruptedException, IOException {
        int field;
        //new ProcessBuilder("cls").inheritIO().start().waitFor();
        //Runtime.getRuntime().exec("cls");
        this.print();
        System.out.println("Write a number a chosen column: ");
        while (true) {
            int i = 5;
            field = move();
            while (field < 0 || field > 6) {
                Runtime.getRuntime().exec("cls");
                this.print();
                System.out.println("It's wrong number, try again: ");
                field = move();
            }

            while (i >= 0 && tab[i][field] != 0) {
                i--;
                /*if (i<0){
                    break;
                }*/
            }

            if (i >= 0) {
                tab[i][field] = player;
                break;
            } else {
                //new ProcessBuilder("clear").inheritIO().start().waitFor();
                //Runtime.getRuntime().exec("cls");
                this.print();
                System.out.println("This column is full, choose another ");
            }
        }
    }

    private void print() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tab[i][j] == 0) {
                    System.out.print("|  ");
                } else if (tab[i][j] == 1) {
                    System.out.print("|X ");
                } else if (tab[i][j] == 2) {
                    System.out.print("|O ");
                }
            }
            System.out.println("|");
        }

        for (int i = 0; i < 7; i++) {
            System.out.print("(" + i + ")");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int round = 1;
        Game play = new Game();

        while (play.match()) {
            if (round % 2 == 1) {
                System.out.println("Player 1: ");
                play.fill(1);

            } else {
                System.out.println("Player 2: ");
                play.fill(2);
            }
            round++;
        }
        //new ProcessBuilder("clear").inheritIO().start().waitFor();
        //Runtime.getRuntime().exec("cls");
        if (round % 2 == 0 && !play.match()) {
            System.out.println("Player 1 won!");
        } else if (round % 2 == 1 && !play.match()) {
            System.out.println("Player 2 won!");
        } else {
            System.out.println("Draw!!!");
        }
        play.print();
    }
}