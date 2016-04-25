package Race;

import java.util.Scanner;

/**
 * Created by narubordeesarnsuwan on 4/25/2016 AD.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to F1 Racing!! ");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of team: ");

        Integer numberOfTeam = new Integer(scanner.nextLine());

        System.out.println("Enter number of length: ");

        Integer finishLineLength = new Integer(scanner.nextLine());

        System.out.println("Number of Team = " + numberOfTeam);
        System.out.println("Number of Length = " + finishLineLength);

        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(finishLineLength));
        //Let's go
        while(true) {

            //assessment
            if (raceTrack.canStop()) {
                break;
            }

            raceTrack.execRacing();

            //Ranking
            raceTrack.ranking();

        }
        System.out.println("=================");
        System.out.println("= R A N K I N G =");
        System.out.println("=================");
        raceTrack.printResult();
    }
}
