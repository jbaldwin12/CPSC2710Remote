package edu.au.cpsc.module4.data;

import java.io.*;

public class Db {

    public static final File DEFAULT_FILE = new File("ScheduledFlights.dat");

    private static AirlineDatabase scheduledFlightDatabase = null;

    public static AirlineDatabase getDatabase() {
        // Lazy Initialization
        if (scheduledFlightDatabase == null)
            scheduledFlightDatabase = loadDatabase();
        return scheduledFlightDatabase;
    }

    private static AirlineDatabase loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return AirlineDatabaseIO.load(is);
        } catch (IOException | ClassNotFoundException ex) {
            return new AirlineDatabase();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            AirlineDatabaseIO.save(getDatabase(), os);
        } catch (IOException ex) {
            System.err.println("Error saving database: " + DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }

}
