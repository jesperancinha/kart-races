package org.jesperancinha.kartracelaps.objects;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class DriverImpl implements Driver{
    private Kart kart;

    private String name;

    public DriverImpl(Kart kart, String name)
    {
        this.kart = kart;
        this.kart.setDriver(this);
        this.name = name;
    }

    /**
     * Driver may need to change kart
     * @param kart Kart
     */
    @Override
    public void setKart(Kart kart) {
        this.kart = kart;
    }

    /**
     * Gets the Kart assigned to the driver
     * @return
     */
    @Override
    public Kart getKart() {
        return kart;
    }

    /**
     * This is the drivers name. It should not be modified
     * @return
     */
    @Override
    public String getName() {
        return name;
    }
}
