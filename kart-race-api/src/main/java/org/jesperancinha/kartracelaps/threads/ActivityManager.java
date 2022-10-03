package org.jesperancinha.kartracelaps.threads;

import org.jesperancinha.kartracelaps.exceptions.DriverAccidentException;
import org.jesperancinha.kartracelaps.objects.Driver;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public interface ActivityManager {
    void startRace() throws ExecutionException,
            InterruptedException,
            DriverAccidentException;

    ExecutorService getExecutorService(Collection<Driver> drivers);

    void shutDownExecutorService(ExecutorService executor);

    boolean isStartRace();
}
