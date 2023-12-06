package org.main;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Random rnd;
    static Map<Integer, Boolean> stat;
    static int doorsCount;

    public static void main(String[] args) {
        rnd = new Random();
        stat = new HashMap<>();
        doorsCount = 3;

        for (int i = 0; i < 1000; i++) {
            //Играем и записываем результат раунда в статистику
            stat.put(i, round());
        }

        // Считаем статистику побед
        int win = 0;
        for (Map.Entry<Integer, Boolean> entry : stat.entrySet()) {
            if (entry.getValue()) {
                win++;
            }
        }
        System.out.println("Игроку удалось выиграть " + win + " раз");
    }

    private static boolean round() {
        int success = rnd.nextInt(doorsCount); // разместили приз
        int firstChoice = rnd.nextInt(doorsCount); // первый выбор игрока
        int hostChoice = -1;
        int secondChoice = -1;

        // Ведущий открывает одну из оставшихся дверей, за которой точно нет приза
        while (hostChoice == -1) {
            int choice = rnd.nextInt(doorsCount);
            if (choice != success && choice != firstChoice) {
                hostChoice = choice;
            }
        }

        // Игрок либо оставляет исходный вариант, либо меняет свой выбор
        if (rnd.nextBoolean()) {
            secondChoice = firstChoice;
        } else {
            while (secondChoice == -1) {
                int choice = rnd.nextInt(doorsCount);
                if (choice != firstChoice && choice != hostChoice) {
                    secondChoice = choice;
                }
            }
        }

        return success == secondChoice;
    }
}