package org.jesperancinha.kartracelaps.threads;

import org.jesperancinha.kartracelaps.exceptions.DriverAccidentException;
import org.jesperancinha.kartracelaps.manager.DriverManager;
import org.jesperancinha.kartracelaps.objects.Driver;
import org.jesperancinha.kartracelaps.objects.Kart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class ActivityManagerImpl implements ActivityManager {
    private final DriverManager driverManager;
    private boolean startRace = false;

    public ActivityManagerImpl(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void startRace() throws ExecutionException, InterruptedException, DriverAccidentException {
        startRace = true;
        final Collection<Driver> drivers = driverManager.getDriverList().values();
        List<Future<Kart>> allDriverWaiter = new ArrayList<>();
        final ExecutorService executor = getExecutorService(drivers);

        for (Driver driver : drivers) {
            Future<Kart> future = executor.submit(driver.getKart().startRacing());
            allDriverWaiter.add(future);
        }

        for (Future<Kart> future : allDriverWaiter) {
            Kart kart = future.get();
            if (!kart.isSuccess()) {
                throw new DriverAccidentException(kart.getKartId());
            }
        }
        shutDownExecutorService(executor);
    }

    @Override
    public ExecutorService getExecutorService(Collection<Driver> drivers) {
        return Executors.newFixedThreadPool(drivers.size());
    }

    @Override
    public void shutDownExecutorService(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("ending tasks");
            }
            executor.shutdownNow();
        }
        startRace = false;
    }

    @Override
    public boolean isStartRace() {
        return startRace;
    }
}
