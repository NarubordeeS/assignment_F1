
import Race.Car;
import Race.Driver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Matchers.any;
/**
 * Created by narubordeesarnsuwan on 4/25/2016 AD.
 */
public class CarTest {

    @Mock
    Driver driver;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInstantiateInstanceCorrectly() {
        Integer carNumber = 5;
        Double startPosition = new Double(800);
        Double goalPosition = new Double(10000);
        Car car = new Car(carNumber,startPosition,goalPosition);
        assertNotNull(car);
        assertEquals(new Double(10),car.getAcceleration());
        assertEquals(new Double(0),car.getCurrentSpeed());
        assertEquals(new Double(800),car.getDistance());
        assertEquals(new Double(10000),car.getFinishLine());
        assertEquals(new Double(55.55555555555556),car.getTopSpeed());
        assertEquals(5,car.getCarNumber().intValue());
        assertEquals(new Double(10000),car.getFinishLine());
        assertEquals(0,car.getTimeElapse().intValue());
        assertEquals(true,car.getHaveNitro());
        assertEquals(5,car.getRank().intValue());
        car.setRank(1);
        assertEquals(1,car.getRank().intValue());
    };

    @Test
    public void shouldAccelerateCorrectly() {
        Integer carNumber = 5;
        Double startPosition = new Double(800);
        Double goalPosition = new Double(10000);
        //Inject Mock
        Car car = new Car(carNumber,startPosition,goalPosition,driver);
        // accelerate for 4 seconds;
        for (int i=0;i<2;i++) {
            car.accelerate();
        }

        assertEquals(new Double(40),car.getCurrentSpeed());
        verify(driver,times(2)).shouldReduceSpeed();
        verify(driver,times(2)).shouldTurnOnNitro();
    };

    @Test
    public void shouldReduceSpeedCorrectly() {
        when(driver.shouldReduceSpeed()).thenReturn(true);
        Integer carNumber = 5;
        Double startPosition = new Double(800);
        Double goalPosition = new Double(10000);
        //Inject Mock
        Car car = new Car(carNumber,startPosition,goalPosition,driver);
        // accelerate for 4 seconds;
        for (int i=0;i<2 ;i++) {
            car.accelerate();
        }
        car.haveNearOpponent(true);
        // continue accelerate 2 seconds
        car.accelerate();
        assertEquals(new Double(20),car.getCurrentSpeed());
        verify(driver,times(3)).shouldReduceSpeed();
        verify(driver,times(3)).shouldTurnOnNitro();
        verify(driver,times(1)).setOpponentInRange(true);

    };

    @Test
    public void shouldTurnOnNitroCorrectly() {
        when(driver.shouldTurnOnNitro()).thenReturn(true);
        Integer carNumber = 5;
        Double startPosition = new Double(0);
        Double goalPosition = new Double(10000000);

        //Inject Mock
        Car car = new Car(carNumber,startPosition,goalPosition,driver);
        // accelerate for 4 seconds;
        for (int i=0;i<2;i++) {
            car.accelerate();
        }
        car.isLast(true);
        car.accelerate();
        assertEquals(car.getTopSpeed(),car.getCurrentSpeed());
        verify(driver,times(3)).shouldReduceSpeed();
        verify(driver,times(3)).shouldTurnOnNitro();
        verify(driver,times(1)).setLast(true);
    };

    @Test
    public void shouldStopWhenCarReachFinishLine() {
        Integer carNumber = 5;
        Double startPosition = new Double(0);
        Double goalPosition = new Double(10000);
        //Inject Mock
        Car car = new Car(carNumber,startPosition,goalPosition,driver);
        while(true) {
            car.accelerate();
            if (car.getStop()) {
                break;
            }
        }

        assertTrue(car.getStop());
        assertTrue(car.getDistance() >= car.getFinishLine());
    };

}
