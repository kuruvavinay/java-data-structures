import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomerManagementApp {
    private static final String BASE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    private static String bearerToken;

    public static void main(String[] args) {
        authenticateUser();
        createCustomer("Jane", "Doe", "sam@gmail.com", "12345678");
        getCustomerList();
        deleteCustomer("CUSTOMER_UUID");
        updateCustomer("CUSTOMER_UUID", "Jane", "Doe", "sam@gmail.com", "12345678");
    }

    private static void authenticateUser() {
        String authenticationUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        try {
            URL url = new URL(authenticationUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String requestBody = "{\"login_id\":\"test@sunbasedata.com\",\"password\":\"Test@123\"}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the response JSON and extract the bearer token
                bearerToken = response.toString();
            } else {
                System.out.println("Authentication failed. HTTP error code: " + connection.getResponseCode());
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createCustomer(String firstName, String lastName, String email, String phone) {
        try {
            URL url = new URL(BASE_URL + "?cmd=create");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
            connection.setDoOutput(true);

            String requestBody = "{\"first_name\":\"" + firstName + "\",\"last_name\":\"" + lastName + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone + "\"}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == 201) {
                System.out.println("Customer created successfully.");
            } else if (responseCode == 400) {
                System.out.println("First Name or Last Name is missing.");
            } else {
                System.out.println("Failed to create customer. HTTP error code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getCustomerList() {
        try {
            URL url = new URL(BASE_URL + "?cmd=get_customer_list");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the response JSON and display the customer list
                System.out.println("Customer List:\n" + response.toString());
            } else {
                System.out.println("Failed to get customer list. HTTP error code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCustomer(String uuid) {
        try {
            URL url = new URL(BASE_URL + "?cmd=delete&uuid=" + uuid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Customer deleted successfully.");
            } else if (responseCode == 400) {
                System.out.println("UUID not found.");
            } else {
                System.out.println("Failed to delete customer. HTTP error code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateCustomer(String uuid, String firstName, String lastName, String email, String phone) {
        try {
            URL url = new URL(BASE_URL + "?cmd=update&uuid=" + uuid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
            connection.setDoOutput(true);

            String requestBody = "{\"first_name\":\"" + firstName + "\",\"last_name\":\"" + lastName + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone + "\"}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Customer updated successfully.");
            } else if (responseCode == 400) {
                System.out.println("Body is Empty.");
            } else if (responseCode == 500) {
                System.out.println("UUID not found.");
            } else {
                System.out.println("Failed to update customer. HTTP error code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
