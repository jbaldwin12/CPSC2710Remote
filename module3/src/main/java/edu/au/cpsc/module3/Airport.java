/*
* Project Name: Airport Info. UI
* Author: Jordan Baldwin
* Auburn email: jtb0185@auburn.edu
* Date: 1/23/26
* Description: User will enter an ID for an airport to display information about it, including a simple map.
 */

package edu.au.cpsc.module3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Airport {

    private String ident;
    private String type;
    private String name;
    private Integer elevationFt;
    private String continent;
    private String isoCountry;
    private String isoRegion;
    private String municipality;
    private String gpsCode;
    private String iataCode;
    private String localCode;
    private double longitude;
    private double latitude;

    public Airport () {
    }

    public Airport(String code, String name, double latitude, double longitude) {
        this.ident = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Airport(String ident, String type, String name, Integer elevationFt,
                   String continent, String isoCountry, String isoRegion,
                   String municipality, String gpsCode, String iataCode,
                   String localCode, double longitude, double latitude) {
        this.ident = ident;
        this.type = type;
        this.name = name;
        this.elevationFt = elevationFt;
        this.continent = continent;
        this.isoCountry = isoCountry;
        this.isoRegion = isoRegion;
        this.municipality = municipality;
        this.gpsCode = gpsCode;
        this.iataCode = iataCode;
        this.localCode = localCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevationFt() {
        return elevationFt;
    }

    public void setElevationFt(Integer elevationFt) {
        this.elevationFt = elevationFt;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public void setIsoRegion(String isoRegion) {
        this.isoRegion = isoRegion;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public void setGpsCode(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public static List<Airport> readAll() throws IOException {
        List<Airport> airports = new ArrayList<>();

        InputStream is = Airport.class
                .getClassLoader()
                .getResourceAsStream("airport-codes.csv");

        if (is == null) {
            throw new FileNotFoundException("airport-codes.csv not found in resources");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;

            // If your CSV has a header row, skip it:
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Adjust indexes to match your CSV columns
                String ident = parts[0];
                String type = parts[1];
                String name = parts[2];
                Integer elevationFt = Integer.valueOf(parts[3]);
                String continent = parts[4];
                String isoCountry = parts[5];
                String isoRegion = parts[6];
                String municipality = parts[7];
                String gpsCode = parts[8];
                String iataCode = parts[9];
                String localCode = parts[10];
                double latitude = Double.parseDouble(parts[12]);
                double longitude = Double.parseDouble(parts[11]);

                airports.add(new Airport(ident, type, name, elevationFt, continent, isoCountry, isoRegion, municipality,
                             gpsCode, iataCode,localCode, longitude, latitude));
            }
        }

        return airports;
    }
}
