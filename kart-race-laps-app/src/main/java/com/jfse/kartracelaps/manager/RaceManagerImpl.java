package com.jfse.kartracelaps.manager;

import com.jfse.kartracelaps.exceptions.DriverAccidentException;
import com.jfse.kartracelaps.objects.*;
import com.jfse.kartracelaps.results.*;
import com.jfse.kartracelaps.threads.ActivityManager;
import com.jfse.kartracelaps.threads.ActivityManagerImpl;

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
    private Winner winne;

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
                        driver.getName(), //
                        driver.getKart().getKartId(), //
                        driver.getKart().getSnapshotByLap(i).getTimeStamp(), //
                        driver.getKart().getSnapshotByLap(i).getDuration(),
                        i);
                results.add(result);
            }
        }

        orderResultsByDuration(results);

        final Result winningResult = results.get(0);
        final Driver driverWinner = driverManager.getDriverByKartId(winningResult.getKartNumber());
        final Kart winningKart = driverWinner.getKart();
        final Winner winner = new WinnerImpl( //
                winningKart.getKartId(), //
                winningKart.getCompleteDuration() //
        );
        LOGGER.info(winner.toString());

        final FastestLap fastesLap = new FastestLapImpl( //
                winningKart.getKartId(), //
                winningResult.getDuration(), //
                winningResult.getLapNumber() //
        );
        LOGGER.info(fastesLap.toString());

        orderResultListByTimeStamp(results);

        return results;
    }

    private void orderResultsByDuration(List<Result> results) {
        Collections.sort(results, //
                (Result result1, Result result2) //
                        -> result1.getDuration().compareTo(result2.getDuration()));
    }

    private void orderResultListByTimeStamp(List<Result> results) {
        Collections.sort(results, //
                (Result result1, Result result2) //
                        -> result1.getTimeStamp().compareTo(result2.getTimeStamp()));
    }

}
