/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author ThitXaoBoa
 */
public class Tour implements Serializable {
    private String tourId;
    private String tourName;
    private String tourDestination;
    private String tourDuration;
    private String tourDescription;
    private double tourPrice;
    private String tourInclusions;
    private String tourExclusions;

    public Tour() {
    }

    public Tour(String tourId, String tourName, String tourDestination, String tourDuration, String tourDescription, double tourPrice, String tourInclusions, String tourExclusions) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDestination = tourDestination;
        this.tourDuration = tourDuration;
        this.tourDescription = tourDescription;
        this.tourPrice = tourPrice;
        this.tourInclusions = tourInclusions;
        this.tourExclusions = tourExclusions;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDestination() {
        return tourDestination;
    }

    public void setTourDestination(String tourDestination) {
        this.tourDestination = tourDestination;
    }

    public String getTourDuration() {
        return tourDuration;
    }

    public void setTourDuration(String tourDuration) {
        this.tourDuration = tourDuration;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public List<String> getTourInclusions() {
        return Collections.singletonList(tourInclusions);
    }

    public void setTourInclusions(String tourInclusions) {
        this.tourInclusions = tourInclusions;
    }

    public List<String> getTourExclusions() {
        return Collections.singletonList(tourExclusions);
    }

    public void setTourExclusions(String tourExclusions) {
        this.tourExclusions = tourExclusions;
    }
}
