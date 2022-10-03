package org.jesperancinha.kartracelaps.objects;

import java.util.concurrent.Callable;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public interface Kart {
    void setDriver(Driver driver);

    void addSnapshot(Integer lap, Snapshot snapshot);

    Snapshot getSnapshotByLap(Integer lap);

    Integer getKartId();

    Callable<Kart> startRacing();

    Integer getSimulatedTimeToWait() throws InterruptedException;

    boolean isSuccess();

    Integer getCompleteDuration();
}