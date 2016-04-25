import Race.Car;
import Race.RaceTrack;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by narubordeesarnsuwan on 4/25/2016 AD.
 */
public class RaceTrackTest {
    @Mock
    Car car1;

    @Mock
    Car car2;

    @Mock
    Car car3;

    RaceTrack raceTrack;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Integer numberOfTeam = 10;
        Integer length = 10000;
        raceTrack = new RaceTrack(numberOfTeam,new Double(length));
    }

    @Test
    public void shouldInstantiateInstanceCorrectly() {
        Integer numberOfTeam = 10;
        Integer length = 10000;
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length));
        assertNotNull(raceTrack);
    }

    @Test
    public void shouldStopCorrectlyWhenAllCarReachFinishLine() {

        when(car1.getStop()).thenReturn(true);
        when(car2.getStop()).thenReturn(true);
        when(car3.getStop()).thenReturn(true);

        List<Car> race = new ArrayList();
        race.add(car1);
        race.add(car2);
        race.add(car3);
        Integer numberOfTeam = 10;
        Integer length = 10000;
        //Inject Mock
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length),race);
        assertTrue(raceTrack.canStop());
    }
    @Test
    public void shouldCallGetStopTwiceWhenCallCanstopWithTwoCar() {
        List<Car> race = new ArrayList();
        race.add(car1);
        race.add(car1);
        Integer numberOfTeam = 10;
        Integer length = 10000;
        //Inject Mock
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length),race);
        raceTrack.canStop();
        verify(car1,times(2)).getStop();
    }

    @Test
    public void shouldCallaccelerateTwiceWhenCallRacingWithTwoCar() {
        List<Car> race = new ArrayList();
        race.add(car1);
        race.add(car2);
        race.add(car3);
        Integer numberOfTeam = 10;
        Integer length = 10000;
        //Inject Mock
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length),race);
        raceTrack.execRacing();
        verify(car1,times(1)).accelerate();
        verify(car2,times(1)).accelerate();
        verify(car3,times(1)).accelerate();
    }

    @Test
    public void shouldCallRankingCorrectly() {
        when(car1.getStop()).thenReturn(false);
        when(car2.getStop()).thenReturn(false);
        when(car3.getStop()).thenReturn(false);

        when(car1.getDistance()).thenReturn(new Double("100.00"));
        when(car2.getDistance()).thenReturn(new Double("200.00"));
        when(car3.getDistance()).thenReturn(new Double("150.00"));

        List<Car> race = new ArrayList();
        race.add(car1);
        race.add(car2);
        race.add(car3);

        Integer numberOfTeam = 10;
        Integer length = 10000;
        //Inject Mock
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length),race);
        raceTrack.ranking();
        verify(car1,times(1)).setRank(3);
        verify(car2,times(1)).setRank(1);
        verify(car3,times(1)).setRank(2);

        verify(car1,times(1)).getRank();
        verify(car2,times(1)).getRank();
        verify(car3,times(1)).getRank();
    }
    @Test

    public void shouldCalculateNearOpponentCorrectly() {
        when(car1.getStop()).thenReturn(false);
        when(car2.getStop()).thenReturn(false);
        when(car3.getStop()).thenReturn(false);

        when(car1.getDistance()).thenReturn(new Double("100.00"));
        when(car2.getDistance()).thenReturn(new Double("111.00"));
        when(car3.getDistance()).thenReturn(new Double("105.00"));

        List<Car> race = new ArrayList();
        race.add(car1);
        race.add(car2);
        race.add(car3);

        Integer numberOfTeam = 10;
        Integer length = 10000;
        //Inject Mock
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length),race);
        raceTrack.ranking();
        raceTrack.execRacing();
        verify(car1,times(1)).haveNearOpponent(true);
        verify(car2,times(1)).haveNearOpponent(true);
        verify(car3,times(1)).haveNearOpponent(true);
    }

    public void shouldCalculateNearOpponent2Correctly() {
        when(car1.getStop()).thenReturn(false);
        when(car2.getStop()).thenReturn(false);
        when(car3.getStop()).thenReturn(false);

        when(car1.getDistance()).thenReturn(new Double("100.00"));
        when(car2.getDistance()).thenReturn(new Double("200.00"));
        when(car3.getDistance()).thenReturn(new Double("199.00"));

        List<Car> race = new ArrayList();
        race.add(car1);
        race.add(car2);
        race.add(car3);

        Integer numberOfTeam = 10;
        Integer length = 10000;
        //Inject Mock
        RaceTrack raceTrack = new RaceTrack(numberOfTeam,new Double(length),race);
        raceTrack.ranking();
        raceTrack.execRacing();
        verify(car1,times(1)).haveNearOpponent(false);
        verify(car2,times(1)).haveNearOpponent(true);
        verify(car3,times(1)).haveNearOpponent(true);
    }
}
