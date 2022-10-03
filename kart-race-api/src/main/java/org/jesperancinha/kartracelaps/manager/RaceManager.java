package org.jesperancinha.kartracelaps.manager;

import org.jesperancinha.kartracelaps.exceptions.DriverAccidentException;
import org.jesperancinha.kartracelaps.objects.Driver;
import org.jesperancinha.kartracelaps.objects.Kart;
import org.jesperancinha.kartracelaps.results.FastestLap;
import org.jesperancinha.kartracelaps.results.Result;
import org.jesperancinha.kartracelaps.results.Winner;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public interface RaceManager {
    void addDriver(String name, Integer kartId);

    void start() throws InterruptedException, ExecutionException, DriverAccidentException;

    Collection<Driver> getAllDrivers();

    List<Result> getResults();

    FastestLap getFastestLapFromResult(Kart winningKart, Result winningResult);

    Winner getWinnerFromResult(Kart winningKart);
}
