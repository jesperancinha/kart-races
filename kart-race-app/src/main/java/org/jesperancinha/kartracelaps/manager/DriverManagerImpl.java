package org.jesperancinha.kartracelaps.manager;

import org.jesperancinha.kartracelaps.objects.Driver;
import org.jesperancinha.kartracelaps.objects.DriverImpl;
import org.jesperancinha.kartracelaps.objects.Kart;
import org.jesperancinha.kartracelaps.objects.KartImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class DriverManagerImpl implements DriverManager {
    final Map<Integer, Driver> driverList;
    private Integer minTimeForLap;
    private Integer maxTimeForLap;
    private Integer nLaps;

    public DriverManagerImpl(Integer minTimeForLap, Integer maxTimeForLap, Integer nLaps) {
        this.minTimeForLap = minTimeForLap;
        this.maxTimeForLap = maxTimeForLap;
        this.nLaps = nLaps;
        driverList = new HashMap<>();
    }

    @Override
    public void addDriver(String name, Integer kartId) {
        final Kart kart = new KartImpl(kartId, minTimeForLap, maxTimeForLap, nLaps);
        final Driver driver = new DriverImpl(kart, name);
        driverList.put(kartId, driver);
    }

    @Override
    public Map<Integer, Driver> getDriverList() {
        return driverList;
    }

    @Override
    public Driver getDriverByKartId(Integer kartId) {
        return driverList.get(kartId);
    }

    @Override
    public void restart() {
        driverList.clear();
    }

    @Override
    public Integer getnLaps() {
        return nLaps;
    }
}
