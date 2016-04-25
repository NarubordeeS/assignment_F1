package Race;

import java.text.DecimalFormat;

/**
 * Created by narubordeesarnsuwan on 4/24/2016 AD.
 */
public class Car {

    private Driver driver = new Driver();
    private Integer carNumber;
    private Integer rank;
    private Double distance;
    private Double currentSpeed;
    private Double topSpeed;

    public Integer getCarNumber() {
        return carNumber;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public Integer getTimeElapse() {
        return timeElapse;
    }

    public Boolean getHaveNitro() {
        return haveNitro;
    }

    private Double acceleration;
    private Boolean stop;
    private Integer timeElapse;
    private Boolean haveNitro;
    private Double finishLine;
    private final Double INTERVAL_TIME = 2.0;
    private final Double SEC_PER_HOUR = 3600.00;
    private final Double TOP_SPEED_BASE = 150.00;
    private final Double TOP_SPEED_FACTOR = 10.00;
    private final Double METRE_PER_KM = 1000.00;
    private final Double ACCEL_FACTOR = 2.0;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public Double getTopSpeed() {
        return this.topSpeed;
    }

    public Double getFinishLine() { return this.finishLine;}

    public Boolean getStop() {
        return stop;
    }

    public Car(Integer carNumber,Double distance,Double finishLine) {
        this.carNumber = carNumber;
        this.finishLine = finishLine;
        this.rank = carNumber;
        this.stop = false;
        this.topSpeed = (TOP_SPEED_BASE + new Double(carNumber) * TOP_SPEED_FACTOR) * METRE_PER_KM/SEC_PER_HOUR /* Convert Km/hr to m/s */;
        this.currentSpeed = new Double(0);
        this.acceleration = new Double(carNumber) * ACCEL_FACTOR;
        this.haveNitro = true;
        this.timeElapse = 0;
        this.distance = distance;
    }

    public Car(Integer carNumber,Double distance,Double finishLine,Driver driver) {
        this(carNumber,distance,finishLine);
        this.driver = driver;
    }

    public void isLast(Boolean value) {
        driver.setLast(value);
    }

    public void haveNearOpponent(Boolean value)
    {
        driver.setOpponentInRange(value);
    }

    public void accelerate() {
        if (!this.stop) {
            if (driver.shouldReduceSpeed()) {
                this.currentSpeed = driver.reduceSpeed(this.currentSpeed);
            }

            if (driver.shouldTurnOnNitro() && this.haveNitro) {
                this.currentSpeed = Math.min(this.currentSpeed * 2, this.topSpeed);
                this.haveNitro = false;
            }

            Double u = this.currentSpeed;
            this.timeElapse += this.INTERVAL_TIME.intValue();
            this.currentSpeed = Math.min(u + this.acceleration * this.INTERVAL_TIME, this.topSpeed);
            this.distance += (u * INTERVAL_TIME) + new Double(1)/ new Double(2) * new Double(this.acceleration) * new Double(INTERVAL_TIME) * new Double(INTERVAL_TIME);
            if (this.distance >= this.finishLine) {
                this.stop = true;
            }
        }
    }
    public void printRank() {
        System.out.println("team no. : " + carNumber);
        System.out.println("rank no. : " + rank);
        System.out.println("current speed : "
                + new DecimalFormat("#.0000").format(currentSpeed * SEC_PER_HOUR/METRE_PER_KM)
                + " km/hr") ;
        System.out.println("distance  : " + distance);
        System.out.println("finishLine  : " + finishLine);
        System.out.println("time Elapse  : " + timeElapse);
        System.out.println("---------------");
    }
}
