package org.jesperancinha.kartracelaps.results;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class ResultImpl implements Result {
    private static DateTimeFormatter DATEFORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");
    private String driverName;
    private Integer kartNumber;
    private LocalTime timeStamp;
    private Integer duration;
    private Integer lapNumber;

    public ResultImpl(String driverName, Integer kartNumber, LocalTime timeStamp, Integer duration, Integer lapNumber) {
        this.driverName = driverName;
        this.kartNumber = kartNumber;
        this.timeStamp = timeStamp;
        this.duration = duration;
        this.lapNumber = lapNumber;
    }

    @Override
    public String getDriverName() {
        return driverName;
    }

    @Override
    public Integer getKartNumber() {
        return kartNumber;
    }

    @Override
    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String getTimeStampString() {
        return DATEFORMATTER.format(timeStamp);
    }

    @Override
    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return driverName.concat(",").concat(kartNumber.toString()).concat(",").concat(getTimeStampString());
    }

    @Override
    public Integer getLapNumber() {
        return lapNumber;
    }
}
