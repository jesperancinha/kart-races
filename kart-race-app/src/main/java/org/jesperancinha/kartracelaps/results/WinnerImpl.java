package org.jesperancinha.kartracelaps.results;

import java.text.MessageFormat;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class WinnerImpl implements Winner {

    private Integer kartNumber;

    private Integer totalRaceTime;

    public WinnerImpl(Integer kartNumber, Integer totalRaceTime) {
        this.kartNumber = kartNumber;
        this.totalRaceTime = totalRaceTime;
    }

    @Override
    public String toString(){
        return MessageFormat.format("Kart {0} is the winner! It only took {1} ms! Congratulations!", kartNumber,totalRaceTime);
    }
}
