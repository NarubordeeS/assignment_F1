package Race;

/**
 * Created by narubordeesarnsuwan on 4/24/2016 AD.
 */
public class Car {
    private Integer carNumber;
    private Integer rank;
    private Double distance;
    private Double currentSpeed;
    private Double topSpeed;
    private Double accelerateFactor;
    private Boolean stop;
    private Integer timeElapse;
    private Boolean nitro;
    private Double finishLine;
    private final Double hf = 0.8;
    private final Double intervalTime = 2.0;

    public Integer getCarNumber() {
        return carNumber;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public Double getDistance() {
        return distance;
    }

    public Double getCurrentSpeed() {
        return currentSpeed;
    }

    public Double getTopSpeed() {
        return topSpeed;
    }

    public Double getAccelerateFactor() {
        return accelerateFactor;
    }

    public Boolean getStop() {
        return stop;
    }

    public Integer getTimeElapse() {
        return timeElapse;
    }

    public Double getHf() {
        return hf;
    }

    public Boolean getNitro() {
        return nitro;
    }

    public Double getFinishLine() {
        return finishLine;
    }

    public Car(Integer carNumber,Double distance,Integer finishLine) {

        this.carNumber = carNumber;
        this.finishLine = new Double(finishLine);
        this.rank = carNumber;
        this.stop = false;
        this.topSpeed = (150 + new Double(carNumber) * 10) * 1000/3600 /* Convert Km/hr to m/s */;
        this.currentSpeed = new Double(0);
        this.accelerateFactor = new Double(carNumber) * 2;
        this.nitro = true;
        this.timeElapse = 0;
        this.distance = distance;// new Double((carNumber-1) * 200);
    }
    public void turnOnNitro() {
        if (nitro) {
            this.currentSpeed = Math.min(this.currentSpeed * 2, this.topSpeed);
            this.nitro = false;
        }
    }

    public void reduceSpeed()
    {
        if (!this.stop){
            this.currentSpeed *= new Double(0.8);
        }
    }

    public void accelerate() {
        if (!this.stop) {
            Double u = this.currentSpeed;
            this.timeElapse += this.intervalTime.intValue();
            this.currentSpeed = Math.min(u + this.accelerateFactor * this.intervalTime, this.topSpeed);
            this.distance += (u * intervalTime) + new Double(1)/ new Double(2) * new Double(this.accelerateFactor) * new Double(intervalTime) * new Double(intervalTime);
            if (this.distance >= this.finishLine) {
                this.stop = true;
            }
        }
    }
}
