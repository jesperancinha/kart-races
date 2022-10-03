package org.jesperancinha.kartracelaps.manager;

import org.jesperancinha.kartracelaps.exceptions.DriverAccidentException;

import org.jesperancinha.kartracelaps.objects.TrackImpl;
import org.jesperancinha.kartracelaps.results.*;
import org.jesperancinha.kartracelaps.threads.ActivityManager;
import org.jesperancinha.kartracelaps.threads.ActivityManagerImpl;
import org.jesperancinha.kartracelaps.objects.Driver;
import org.jesperancinha.kartracelaps.objects.Kart;
import org.jesperancinha.kartracelaps.objects.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class RaceManagerImpl implements RaceManager {
    private static final Logger LOGGER = Logger.getLogger(RaceManagerImpl.class.getName());

    private final DriverManager driverManager;
    private final Track track;
    private FastestLap fastestLap;
    private Winner winner;

    /**
     * @param minTimeForLap In miliseconds
     * @param maxTimeForLap In miliseconds
     * @param nLaps         Unit number of laps
     * @param trackLength   Length in km
     */
    public RaceManagerImpl(Integer minTimeForLap, Integer maxTimeForLap, Integer nLaps, Integer trackLength) {
        driverManager = new DriverManagerImpl(minTimeForLap, maxTimeForLap, nLaps);
        track = new TrackImpl(trackLength);
    }

    @Override
    public void addDriver(String name, Integer kartId) {
        driverManager.addDriver(name, kartId);
    }

    @Override
    public void start() throws InterruptedException, ExecutionException, DriverAccidentException {
        final ActivityManager activityManager = new ActivityManagerImpl(driverManager);
        activityManager.startRace();
    }

    @Override
    public Collection<Driver> getAllDrivers() {
        return driverManager.getDriverList().values();
    }

    @Override
    public List<Result> getResults() {
        final List<Result> results = new ArrayList<>();
        for (final Driver driver : getAllDrivers()) {
            for (int i = 1; i <= driverManager.getnLaps(); i++) {
                final Result result = new ResultImpl(
                        driver.getName(),
                        driver.getKart().getKartId(),
                        driver.getKart().getSnapshotByLap(i).getTimeStamp(),
                        driver.getKart().getSnapshotByLap(i).getDuration(),
                        i);
                results.add(result);
            }
        }

        orderResultsByDuration(results);
        final Result winningResult = results.get(0);
        final Driver driverWinner = driverManager.getDriverByKartId(winningResult.getKartNumber());
        final Kart winningKart = driverWinner.getKart();

        this.winner = getWinnerFromResult(winningKart);
        this.fastestLap = getFastestLapFromResult(winningKart, winningResult);

        orderResultListByTimeStamp(results);

        return results;
    }

    @Override
    public FastestLap getFastestLapFromResult(Kart winningKart, Result winningResult) {
        final FastestLap fastestLap = new FastestLapImpl(
                winningKart.getKartId(),
                winningResult.getDuration(),
                winningResult.getLapNumber()
        );
        LOGGER.info(fastestLap.toString());
        return  fastestLap;
    }

    @Override
    public Winner getWinnerFromResult(Kart winningKart) {
        final Winner winner = new WinnerImpl(
                winningKart.getKartId(),
                winningKart.getCompleteDuration()
        );
        LOGGER.info(winner.toString());
       return winner;
    }

    private void orderResultsByDuration(List<Result> results) {
        Collections.sort(results,
                (Result result1, Result result2)
                        -> result1.getDuration().compareTo(result2.getDuration()));
    }

    private void orderResultListByTimeStamp(List<Result> results) {
        Collections.sort(results,
                (Result result1, Result result2)
                        -> result1.getTimeStamp().compareTo(result2.getTimeStamp()));
    }

}
