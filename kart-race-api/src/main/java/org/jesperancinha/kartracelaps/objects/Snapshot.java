package org.jesperancinha.kartracelaps.objects;

import java.time.LocalTime;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public interface Snapshot {
    Integer getDuration();

    LocalTime getTimeStamp();
}
