package org.jesperancinha.kartracelaps.objects;

import java.time.LocalTime;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class SnapshotImpl implements Snapshot{
    private LocalTime timeStamp;

    private Integer duration;

    public SnapshotImpl(LocalTime timeStamp, Integer duration) {
        this.timeStamp = timeStamp;
        this.duration = duration;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }

    @Override
    public LocalTime getTimeStamp() {
        return timeStamp;
    }
}
