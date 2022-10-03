package org.jesperancinha.kartracelaps.objects;

/**
 * Created by joaofilipesabinoesperancinha on 09-04-16.
 */
public class TrackImpl implements Track {
    Integer trackLength;

    public TrackImpl(Integer trackLength) {
        this.trackLength = trackLength;
    }

    @Override
    public Integer getTrackLength() {
        return trackLength;
    }
}
