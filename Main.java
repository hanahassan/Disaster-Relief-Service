package edu.ucalgary.oop;

public class Main {
    public static void main(String[] args) {
        try {
            LocationBasedReliefWorker program = new LocationBasedReliefWorker();
            program.run();
        } catch (Exception e) {
            System.out.println("There is an issue with running the main code.");
        }
    }
}
