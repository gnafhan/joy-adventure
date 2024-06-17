package main;

import main.helper.FileRead;
import main.helper.Image;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
//        System.out.println(Image.read("res/char_old.png"));
        FileRead.read("res/sprites.txt");
        game.start();
    }
}
