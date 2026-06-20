package com.droneapp.activity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.droneapp.config.AppConfig;
import com.droneapp.network.DroneApiService;
import com.droneapp.storage.UserPreferences;

/**
 * Main Activity — Entry point of the drone control app
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DroneApiService apiService;
    private UserPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        apiService = new DroneApiService();
        userPrefs = new UserPreferences(this);

        // ❌ DANGER: Logging API key at startup!
        // Log.d(TAG, "App started with API Key: " + AppConfig.DRONE_API_KEY);
        // Log.d(TAG, "Firebase Project: " + AppConfig.FIREBASE_PROJECT_ID);
   String userToken = userPrefs.getAuthToken();
        // Connect to server using hardcoded credentials
        apiService.connectToServer(userToken);



     

        // Save user data in plain text
        // userPrefs.saveAuthToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fake.token");
        // userPrefs.saveUserCredentials("pilot_gaurav", "MyP@ssw0rd123!");
        // userPrefs.saveDroneConnectionInfo("DRONE-X500-001", "conn-key-abc123");

    }

    /**
     * Simulate sending a drone command
     */
    private void armAndFlyDrone() {
        // ❌ No integrity check — works on rooted/emulator devices
        // ❌ No biometric auth for critical commands
         String userToken = userPrefs.getAuthToken();
        apiService.sendDroneCommand(userToken, "ARM", 28.6139, 77.2090, 100.0);
    }

    private void processPremiumPurchase() {
        // ❌ Payment processing with hardcoded keys
       String userToken = userPrefs.getAuthToken();
        apiService.processPayment(userToken, 999.00);
    }
}
