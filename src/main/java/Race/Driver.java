package Race;

/**
 * Created by narubordeesarnsuwan on 4/25/2016 AD.
 */
public class Driver {

    private final Double HANDLING_FACTOR = 0.8;
    private Boolean isLast;
    private Boolean haveOpponentInRange;

    public Driver() {
        this.isLast = false;
        this.haveOpponentInRange = false;
    }

    public Boolean shouldTurnOnNitro() {
        return this.isLast;
    }

    public Boolean shouldReduceSpeed() {
        return this.haveOpponentInRange;
    }

    public void setOpponentInRange(Boolean value) {
        this.haveOpponentInRange = value;
    }

    public void setLast(Boolean value) {
        this.isLast = value;
    }

    public Double reduceSpeed(Double currentSpeed){
        return currentSpeed * this.HANDLING_FACTOR;
    }
}
