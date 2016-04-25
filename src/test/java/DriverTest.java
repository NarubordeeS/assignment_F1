import Race.Driver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertNotNull;
/**
 * Created by narubordeesarnsuwan on 4/25/2016 AD.
 */

public class DriverTest {
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInstantiateInstanceCorrectly() {
        Driver driver = new Driver();
        assertNotNull(driver);
        assertEquals(false,driver.shouldReduceSpeed());
        assertEquals(false,driver.shouldTurnOnNitro());
        driver.setLast(true);
        driver.setOpponentInRange(true);
        assertEquals(true,driver.shouldReduceSpeed());
        assertEquals(true,driver.shouldTurnOnNitro());
    }

    @Test
    public void shouldReduceSpeedByHandlingFactorCorrecty() {
        Driver driver = new Driver();
        Double currentSpeed = new Double(40);
        assertEquals(new Double(32),driver.reduceSpeed(currentSpeed));
    };
}
