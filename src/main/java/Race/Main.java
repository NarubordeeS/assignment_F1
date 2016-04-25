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
        Integer numberOfTeam;
        try {
            numberOfTeam = new Integer(scanner.nextLine());
        }
        catch (NumberFormatException ex)
        {
            numberOfTeam = new Integer(1);
            System.out.println("please Enter Number only... default to 1 team");
        }

        System.out.println("Enter number of length: ");

        Integer finishLineLength;
        try {
            finishLineLength = new Integer(scanner.nextLine());
        }
        catch (NumberFormatException ex) {
            finishLineLength = new Integer(1000);
            System.out.println("please Enter Number only... default to 1000 metre");
        }

        System.out.println("Number of Team = " + numberOfTeam + " team(s)");
        System.out.println("Number of Length = " + finishLineLength + " km/hr");

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
