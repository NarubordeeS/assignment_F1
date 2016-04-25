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

    private Integer numberOfTeam;
    private Double finishLine;
    //Construct Race
    List<Car> race;// = raceTrack.InitiateRace(team, new Double(length));

    public RaceTrack(Integer numberOfTeam, Double finishLine) {
        this.numberOfTeam = numberOfTeam;
        this.finishLine = finishLine;
        this.race = new ArrayList();
        for (int i = 1 ; i <= numberOfTeam; i++)
        {
            final Double startPosition = new Double((numberOfTeam-(i)) * 200);
            race.add(new Car(i, startPosition ,this.finishLine));
        }
    }

    public RaceTrack(Integer numberOfTeam, Double finishLine,List<Car> race) {
        this(numberOfTeam,finishLine);
        this.race = race;
    }
    public void ranking() {
        this.race = this.race.stream().sorted((h1,h2) -> {
            if (h1.getStop()== false && h2.getStop() == false) {
                return Double.compare(h2.getDistance(), h1.getDistance());
            }
            else {
                return 1;
            }
        })
                .collect(Collectors.toList());
        for (int i = 0;i< this.race.size();i++) {
            this.race.get(i).setRank(i+1);
            Boolean bool = this.numberOfTeam != 1 && this.race.get(i).getRank() == this.numberOfTeam;
            this.race.get(i).isLast(bool);
        }
    }

    public boolean canStop() {
        if (this.race.stream()
                .filter(car1 -> car1.getStop() == false)
                .collect(Collectors.toList()).size() == 0){
            return true;
        }
        return false;
    }

    private List<Car> doRacing() {
        this.race.forEach(car->{
            car.accelerate();
        });

        return this.race;
    }

    private List<Car> calculateNearestDistanceFromOpponent() {
        if (this.race.size()>1) {
            for (int i = 0; i < this.race.size(); i++) {
                Boolean bool;
                if (i == 0) {
                    Double lowerRankDistance = this.race.get(1).getDistance();
                    Double rankDistance = this.race.get(i).getDistance();
                    bool = Math.abs(rankDistance - lowerRankDistance) <= 10;
                    this.race.get(i).haveNearOpponent(bool);
                }
                else if (i == (this.race.size() - 1)) {
                    Double rankDistance = this.race.get(i).getDistance();
                    Double higherRankDistance = this.race.get(this.race.size()-1).getDistance();
                    bool = Math.abs(higherRankDistance - rankDistance) <= 10;
                    this.race.get(i).haveNearOpponent(bool);
                }
                else {
                    Double lowerRankDistance = this.race.get(i+1).getDistance();
                    Double rankDistance = this.race.get(i).getDistance();
                    Double higherRankDistance = this.race.get(i-1).getDistance();
                    bool = Math.abs(rankDistance - lowerRankDistance) <= 10
                            || Math.abs(higherRankDistance - rankDistance) <= 10;
                    this.race.get(i).haveNearOpponent(bool);
                }
            }
        }
        return race;
    }

    public void execRacing() {
        this.race = this.calculateNearestDistanceFromOpponent();
        this.race = this.doRacing();
    }

    public void printResult() {
        race.forEach(car -> {
            car.printRank();
        });
    }
}
