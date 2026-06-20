package com.droneapp.network;

import com.droneapp.config.AppConfig;
import android.util.Log;

/**
 * Handles communication with the drone backend server.
 * ⚠️ PROBLEM: Uses hardcoded secrets directly + logs sensitive data!
 */
public class DroneApiService {

    private static final String TAG = "DroneApiService";

    /**
     * Connect to the drone server
     * ❌ PROBLEM: Hardcoded credentials used directly
     */
    public boolean connectToServer() {
        String url = AppConfig.SERVER_URL;
        // String username = AppConfig.SERVER_USERNAME;
        // String password = AppConfig.SERVER_PASSWORD;

        // ❌ DANGER: Logging sensitive credentials!
        Log.d(TAG, "Connecting to: " + url);
        // Log.d(TAG, "Username: " + username);
        // Log.d(TAG, "Password: " + password);  // NEVER log passwords!

        // // Simulate connection
        // try {
        //     // In real app, this would be OkHttp/Retrofit call
        //     // OkHttpClient client = new OkHttpClient();
        //     // Request request = new Request.Builder()
        //     //     .url(url)
        //     //     .addHeader("Authorization", "Basic " + encodeCredentials(username, password))
        //     //     .build();

        //     Log.i(TAG, "Connected to drone server successfully");
        //     return true;
        // } catch (Exception e) {
        //     Log.e(TAG, "Connection failed: " + e.getMessage());
        //     return false;
        // }
        return true;

    }

    /**
     * Send a command to the drone
     * ❌ PROBLEM: API key sent directly from app
     */
    public String sendDroneCommand(String command, double lat, double lng, double altitude) {
        // String apiKey = AppConfig.DRONE_API_KEY;
        String url = AppConfig.SERVER_URL + "/drone/command";

        // ❌ DANGER: API key logged!
        // Log.d(TAG, "Using API Key: " + apiKey);
        Log.d(TAG, "Sending command: " + command + " to (" + lat + ", " + lng + ", " + altitude + ")");

        // In real app:
        // Request request = new Request.Builder()
        //     .url(AppConfig.SERVER_URL + "/command")
        //     .addHeader("X-API-Key", apiKey)          ← Secret exposed in network traffic
        //     .addHeader("X-Drone-Auth", AppConfig.ENCRYPTION_SECRET)  ← Another secret!
        //     .post(RequestBody.create(jsonBody, MediaType.parse("application/json")))
        //     .build();

        return "CMD_SENT_OK";
    }

    /**
     * Get flight telemetry data
     * ❌ PROBLEM: Database credentials used directly from app
     */
    public String getTelemetryData(String droneId) {
        // ❌ DANGER: Direct database connection from mobile app!
        // String connectionString = "jdbc:postgresql://"
        //         + AppConfig.DB_HOST + ":"
        //         + AppConfig.DB_PORT + "/"
        //         + AppConfig.DB_NAME
        //         + "?user=" + AppConfig.DB_USER
        //         + "&password=" + AppConfig.DB_PASSWORD;

        // Log.d(TAG, "DB Connection: " + connectionString);  // ❌ Logging DB credentials!

        // // This would connect directly to database (VERY DANGEROUS)
        // return "telemetry_data_here";


          String url = AppConfig.SERVER_URL + "/drone/" + droneId + "/telemetry";
        Log.d(TAG, "Fetching telemetry for drone: " + droneId);
        // In real app:
        // Request request = new Request.Builder()
        //     .url(url)
        //     .addHeader("Authorization", "Bearer " + userAuthToken)
        //     .get()
        //     .build();
        //
        // Server queries DB internally and returns JSON response
        // App NEVER touches the database directly
        return "telemetry_data_here";
    }

    /**
     * Process payment for premium features
     * ❌ PROBLEM: Payment keys hardcoded
     */
    public void processPayment(double amount) {
        // String razorpayKey = AppConfig.RAZORPAY_KEY_ID;
        // String razorpaySecret = AppConfig.RAZORPAY_KEY_SECRET;

        // Log.d(TAG, "Processing payment with key: " + razorpayKey);

        // In real app:
        // Checkout checkout = new Checkout();
        // checkout.setKeyID(razorpayKey);  ← Secret exposed



          String url = AppConfig.SERVER_URL + "/payment/process";
        Log.d(TAG, "Processing payment: ₹" + amount);
    }
}
