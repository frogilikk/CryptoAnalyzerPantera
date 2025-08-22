package com.javarush.khmelov;

import com.javarush.khmelov.controller.MainController;
import com.javarush.khmelov.view.console.ConsoleApp;
import com.javarush.khmelov.view.console.Menu;

import java.util.Scanner;

public class ConsoleRunner {

    public static void main(String[] args) {
        //build console com.javarush.ivanenko.app
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu(input);
        MainController mainController = new MainController();
        ConsoleApp application = new ConsoleApp(mainController, menu);
        //run console com.javarush.ivanenko.app
        application.run(args);
    }


}

