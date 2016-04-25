package Race;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by narubordeesarnsuwan on 4/24/2016 AD.
 */
public class RaceTrack {
    public static void main(String[] args) {
        System.out.println("Enter number of team: ");
        Scanner scanner = new Scanner(System.in);
        Integer team = new Integer(scanner.nextLine());
        System.out.println("Enter number of length: ");
        Integer length = new Integer(scanner.nextLine());

        System.out.println("Number of Team = " + team);
        System.out.println("Number of Length = " + length);

        //Construct racing
        List<Car> race = InitiateRace(team, length);
        //Let's go
        while(true) {

            //assessment
            if (canStop(race)) {
                break;
            }

            //Reduce speed with Handling Factor
            reduceSpeed(race);

            //Go Go
            accelerate(team, race);

            //Ranking
            race = ranking(race);

        }

        race.forEach(car -> {
            System.out.println("===========");
            System.out.println("team no. : " + car.getCarNumber());
            System.out.println("rank no. : " + car.getRank());
            DecimalFormat df = new DecimalFormat("#.0000");
            System.out.println("current speed : " + df.format(car.getCurrentSpeed() * new Double(3600)/new Double(1000)).toString() + " km/hr") ;
            System.out.println("distance  : " + car.getDistance());
            System.out.println("goal  : " + car.getFinishLine());
            System.out.println("time Elapse  : " + car.getTimeElapse());
            System.out.println("===========");
        });
    }

    private static List<Car> InitiateRace(Integer team, Integer length) {
        List<Car> race = new ArrayList();
        for (int i = 1 ; i <= team; i++)
        {
            race.add(new Car(i, new Double((team-(i)) * 200),length));
        }
        return race;
    }

    private static List<Car> ranking(List<Car> race) {
        race = race.stream().sorted((h1,h2) -> {
            if (h1.getStop()== false && h2.getStop() == false) {
                return Double.compare(h2.getDistance(), h1.getDistance());
            }
            else {
                return 1;
            }
        })
                .collect(Collectors.toList());
        for (int i = 0;i< race.size();i++) {
            race.get(i).setRank(i+1);
        }
        return race;
    }

    private static boolean canStop(List<Car> race) {
        if (race.stream()
                .filter(car1 -> car1.getStop() == false)
                .collect(Collectors.toList()).size() == 0){
            return true;
        }
        return false;
    }

    private static void accelerate(Integer team, List<Car> race) {
        race.forEach(car->{
            if (team != 1 && car.getRank() == team) {
                car.turnOnNitro();
            }
            car.accelerate();
        });
    }

    private static void reduceSpeed(List<Car> race) {
        if (race.size()>1) {
            for (int i = 0; i < race.size(); i++) {
                if (i == 0) {
                    Double lowerRankDistance = race.get(1).getDistance();
                    Double rankDistance = race.get(i).getDistance();
                    if (Math.abs(rankDistance - lowerRankDistance) <= 10) {
                        race.get(i).reduceSpeed();
                    }
                }
                else if (i == (race.size() - 1)) {
                    Double rankDistance = race.get(i).getDistance();
                    Double higherRankDistance = race.get(race.size()-1).getDistance();
                    if (Math.abs(higherRankDistance - rankDistance) <= 10) {
                        race.get(i).reduceSpeed();
                    }
                }
                else {
                    Double lowerRankDistance = race.get(i+1).getDistance();
                    Double rankDistance = race.get(i).getDistance();
                    Double higherRankDistance = race.get(i-1).getDistance();
                    if (Math.abs(rankDistance - lowerRankDistance) <= 10
                            || Math.abs(higherRankDistance - rankDistance) <= 10) {
                        race.get(i).reduceSpeed();
                    }
                }
            }
        }
    }
}
