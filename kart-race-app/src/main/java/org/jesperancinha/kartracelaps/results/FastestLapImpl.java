package org.jesperancinha.kartracelaps.results;

import java.text.MessageFormat;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class FastestLapImpl implements FastestLap {

    private Integer karNumber;

    private Integer duration;

    private Integer number;

    public FastestLapImpl(Integer karNumber, Integer duration, Integer number) {
        this.karNumber = karNumber;
        this.duration = duration;
        this.number = number;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "Kart number {0} was the fasted with {1} ms on lap number {2}",
                karNumber,
                duration,
                number
        );
    }
}
