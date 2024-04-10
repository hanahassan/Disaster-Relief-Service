package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost/ensf380project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "14286502";

    private Map<String, DisasterVictim> victimCache = new HashMap<>();

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error: Failed to connect to the database.");
            System.out.println("Please check your database connection settings.");
//            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void initializeLocations(List<Location> locations) {
        locations.clear();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM LOCATION")) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Location location = new Location(name, address);
                initializeVictimsForLocation(location); // Load victims for this location
                initializeSuppliesForLocation(location); // Load supplies for this location
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeSuppliesForLocation(Location location) {
        String locationName = location.getName();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SUPPLIES WHERE locationName = ?")) {
            preparedStatement.setString(1, locationName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    int quantity = resultSet.getInt("quantity");
                    Supply supply = new Supply(type, quantity);
                    location.addSupply(supply);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error initializing supplies for location " + locationName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void initializeVictimsForLocation(Location location) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DISASTERVICTIMS WHERE locationName = ?")) {
            preparedStatement.setString(1, location.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String entryDate = resultSet.getString("entryDate");
                Integer age = resultSet.getInt("age");
                String dateOfBirth = resultSet.getString("dateOfBirth");

                // Check if victim with this firstName is already cached
                DisasterVictim victim = victimCache.get(firstName);

                if (victim == null) {
                    if (dateOfBirth != null) {
                        victim = new DisasterVictim(firstName, entryDate, dateOfBirth);
                    } else {
                        victim = new DisasterVictim(firstName, entryDate, age);
                    }
                    victimCache.put(firstName, victim); // Cache the new victim
                }

                location.addOccupant(victim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDisasterVictim(String firstName, String entryDate, Integer age, String dob, String locationName) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DISASTERVICTIMS (firstName, entryDate, age, dateOfBirth, locationName) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, entryDate);
            if (age != null) {
                preparedStatement.setInt(3, age);
                preparedStatement.setNull(4, Types.DATE); // dob should be null
            } else {
                preparedStatement.setNull(3, Types.INTEGER); // age should be null
                preparedStatement.setString(4, dob);
            }
            preparedStatement.setString(5, locationName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DisasterVictim getDisasterVictimByFirstName(String firstName) {
        DisasterVictim victim = victimCache.get(firstName);

        if (victim == null) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DISASTERVICTIMS WHERE firstName = ?")) {
                preparedStatement.setString(1, firstName);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String entryDate = resultSet.getString("entryDate");
                    Integer age = resultSet.getInt("age");
                    String dateOfBirth = resultSet.getString("dateOfBirth");

                    if (dateOfBirth != null) {
                        victim = new DisasterVictim(firstName, entryDate, dateOfBirth);
                    } else {
                        victim = new DisasterVictim(firstName, entryDate, age);
                    }

                    victimCache.put(firstName, victim); // Cache the new victim
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (victim == null) {
            System.out.println("Victim with first name '" + firstName + "' not found in the database. Please add the victim first.");
        }

        return victim;
    }


    public Location getLocationByName(String locationName) {
        Location location = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LOCATION WHERE name = ?")) {
            preparedStatement.setString(1, locationName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                location = new Location(name, address);
                initializeVictimsForLocation(location);
                initializeSuppliesForLocation(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    public void addLocation(String name, String address) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO LOCATION (name, address) VALUES (?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void decreaseSupplyQuantity(String supplyType, int quantity, String locationName) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE SUPPLIES SET quantity = quantity - ? WHERE type = ? AND locationName = ?")) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, supplyType);
            preparedStatement.setString(3, locationName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ReliefService> initializeReliefService() {
        ArrayList<ReliefService> reliefServices = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ReliefService")) {

            while (resultSet.next()) {
                int inquirerId = resultSet.getInt("inquirerId");
                String missingPersonFirstName = resultSet.getString("missingPersonFirstName");
                String missingPersonLastName = resultSet.getString("missingPersonLastName");
                String dateOfInquiry = resultSet.getString("dateOfInquiry");
                String lastKnownLocationName = resultSet.getString("lastKnownLocationName");
                String lastKnownLocationAddress = resultSet.getString("lastKnownLocationAddress");

                // Fetch the associated Inquirer
                Inquirer inquirer = getInquirerById(inquirerId);

                // Create the Location object for last known location
                Location lastKnownLocation = new Location(lastKnownLocationName, lastKnownLocationAddress);

                // Create the ReliefService instance
                ReliefService reliefService = new ReliefService(inquirer, new DisasterVictim(missingPersonFirstName, missingPersonLastName), dateOfInquiry, lastKnownLocation);
                // Retrieve interaction history as a list of strings
                Array interactionHistoryArray = resultSet.getArray("interactionHistory");
                if (interactionHistoryArray != null) {
                    String[] interactionHistory = (String[]) interactionHistoryArray.getArray();
                    for (String log : interactionHistory) {
                        reliefService.addInteractionLog(log);
                    }
                }
                reliefServices.add(reliefService);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reliefServices;
    }

    private Inquirer getInquirerById(int inquirerId) {
        Inquirer inquirer = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Inquirer WHERE id = ?")) {

            preparedStatement.setInt(1, inquirerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String servicesPhoneNum = resultSet.getString("phonenumber");
                String info = resultSet.getString("info");

                inquirer = new Inquirer(firstName, lastName, servicesPhoneNum, info);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inquirer;
    }

    public ArrayList<ReliefService> getAllReliefServices() {
        ArrayList<ReliefService> reliefServices = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ReliefService")) {

            while (resultSet.next()) {
                Inquirer inquirer = getInquirerById(resultSet.getInt("inquirerId"));
                DisasterVictim missingPerson = new DisasterVictim(
                        resultSet.getString("missingPersonFirstName"),
                        resultSet.getString("missingPersonLastName")
                );
                String dateOfInquiry = resultSet.getString("dateOfInquiry");
                Location lastKnownLocation = new Location(
                        resultSet.getString("lastKnownLocationName"),
                        resultSet.getString("lastKnownLocationAddress")
                );

                ReliefService reliefService = new ReliefService(inquirer, missingPerson, dateOfInquiry, lastKnownLocation);
                reliefService.addInteractionLog(resultSet.getString("interactionHistory"));

                reliefServices.add(reliefService);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reliefServices;
    }

    public void editInquiry(ReliefService reliefService, String newInfoProvided) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE ReliefService SET interactionHistory = ? WHERE inquirerId = ? AND missingPersonFirstName = ? AND missingPersonLastName = ? AND dateOfInquiry = ?")) {

            preparedStatement.setArray(1, connection.createArrayOf("text", reliefService.getInteractionHistory().toArray()));
            preparedStatement.setInt(2, getInquirerId(reliefService.getInquirer()));
            preparedStatement.setString(3, reliefService.getMissingPerson().getFirstName());
            preparedStatement.setString(4, reliefService.getMissingPerson().getLastName());
            preparedStatement.setDate(5, Date.valueOf(reliefService.getDateOfInquiry()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReliefServiceEntry(ReliefService reliefService) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ReliefService (inquirerId, missingPersonFirstName, missingPersonLastName, dateOfInquiry, lastKnownLocationName, lastKnownLocationAddress, interactionHistory) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, getInquirerId(reliefService.getInquirer()));
            preparedStatement.setString(2, reliefService.getMissingPerson().getFirstName());
            preparedStatement.setString(3, reliefService.getMissingPerson().getLastName());
            preparedStatement.setDate(4, Date.valueOf(reliefService.getDateOfInquiry()));
            preparedStatement.setString(5, reliefService.getLastKnownLocation().getName());
            preparedStatement.setString(6, reliefService.getLastKnownLocation().getAddress());
            preparedStatement.setArray(7, connection.createArrayOf("text", reliefService.getInteractionHistory().toArray()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getInquirerId(Inquirer inquirer) {
        int inquirerId = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM Inquirer WHERE firstName = ? AND lastName = ?")) {

            preparedStatement.setString(1, inquirer.getFirstName());
            preparedStatement.setString(2, inquirer.getLastName());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                inquirerId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inquirerId;
    }

    public Inquirer getInquirerByName(String firstName, String lastName) {
        Inquirer inquirer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Inquirer WHERE firstName = ? AND lastName = ?")) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String phoneNum = resultSet.getString("servicesPhoneNum");
                String info = resultSet.getString("info");
                inquirer = new Inquirer(firstName, lastName, phoneNum, info);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inquirer;
    }

    public void addInquirer(Inquirer inquirer) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Inquirer (firstName, lastName, phonenumber, info) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, inquirer.getFirstName());
            preparedStatement.setString(2, inquirer.getLastName());
            preparedStatement.setString(3, inquirer.getServicesPhoneNum());
            preparedStatement.setString(4, inquirer.getInfo());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DisasterVictim> searchVictimsByName(String partialName) {
        ArrayList<DisasterVictim> matchingVictims = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DISASTERVICTIMS WHERE LOWER(firstName) LIKE LOWER(?)")) {

            String wildcardedName = "%" + partialName + "%";
            preparedStatement.setString(1, wildcardedName.toLowerCase());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String entryDate = resultSet.getString("entryDate");
                Integer age = resultSet.getInt("age");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String locationname = resultSet.getString("locationName");

                DisasterVictim victim;
                if (dateOfBirth != null) {
                    victim = new DisasterVictim(firstName, entryDate, dateOfBirth);
                    victim.setLocationName(locationname);
                } else {
                    victim = new DisasterVictim(firstName, entryDate, age);
                    victim.setLocationName(locationname);
                }

                matchingVictims.add(victim);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingVictims;
    }

    public void updateSupply(Supply supply) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE SUPPLIES SET quantity = ? WHERE type = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, supply.getQuantity());
            preparedStatement.setString(2, supply.getType());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Supply updated successfully in the database.");
            } else {
                System.out.println("No matching supply found in the database for update.");
                // Alternatively, you can insert a new supply record here if desired
            }
        } catch (SQLException e) {
            System.out.println("Error updating supply in the database: " + e.getMessage());
        }
    }

    public void addSupply(Supply supply, String locationName) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO SUPPLIES (type, quantity, locationName) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supply.getType());
            preparedStatement.setInt(2, supply.getQuantity());
            preparedStatement.setString(3, locationName);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Supply added successfully to the database.");
            } else {
                System.out.println("Failed to add supply to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding supply to the database: " + e.getMessage());
        }
    }

    public void updateSupplyQuantityInDatabase(String locationName, String supplyType, int newQuantity) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE SUPPLIES SET quantity = ? WHERE locationName = ? AND type = ?")) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setString(2, locationName);
            preparedStatement.setString(3, supplyType);
            preparedStatement.executeUpdate();
            System.out.println("Supply quantity updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSupplyQuantity(String locationName, String supplyType, int quantityToSubtract) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT quantity FROM SUPPLIES WHERE locationName = ? AND type = ?")) {
            preparedStatement.setString(1, locationName);
            preparedStatement.setString(2, supplyType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("quantity");
                int newQuantity = currentQuantity - quantityToSubtract;

                // Update the supply quantity in the database
                updateSupplyQuantityInDatabase(locationName, supplyType, newQuantity);
            } }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
