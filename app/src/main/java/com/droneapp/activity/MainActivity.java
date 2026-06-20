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


         if (!isDeviceTrusted()) {
            return;  // App closes — won't run on compromised devices
        }







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


    private boolean isDeviceTrusted() {
        // Check 1: Rooted device
        if (IntegrityChecker.isDeviceRooted()) {
            showBlockedDialog("Rooted device detected. "
                + "For safety, this app cannot control drones on rooted devices.");
            return false;
        }
        // Check 2: Emulator
        if (IntegrityChecker.isEmulator()) {
            showBlockedDialog("Emulator detected. "
                + "Drone control requires a real device.");
            return false;
        }
        // Check 3: Modified APK
        if (IntegrityChecker.isAppTampered(this)) {
            showBlockedDialog("This app has been modified. "
                + "Please download the official version from Play Store.");
            return false;
        }
        // Check 4: Debugger
        if (IntegrityChecker.isDebuggerAttached()) {
            showBlockedDialog("Debugger detected. "
                + "Cannot run drone control with a debugger attached.");
            return false;
        }
        return true;  // All checks passed!
    }


      private void showBlockedDialog(String message) {
        new AlertDialog.Builder(this)
            .setTitle("⚠️ Security Alert")
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Exit", (dialog, which) -> finish())
            .show();
    }



    private void safeArmDrone() {
    PlayIntegrityChecker.verifyIntegrity(this, new PlayIntegrityChecker.IntegrityCallback() {
        
        @Override
        public void onTokenReceived(String token, String nonce) {
            // Send token to YOUR server
            // Server verifies with Google and returns: safe or not safe
            // 
            // Example server call:
            // POST https://your-server.com/api/verify-integrity
            // Body: { "token": "...", "nonce": "..." }
            //
            // Server response: { "verified": true, "deviceIntegrity": "MEETS_DEVICE_INTEGRITY" }
            //
            // If verified → proceed with arming
            String userToken = userPrefs.getAuthToken();
            apiService.sendDroneCommand(userToken, "ARM", 28.6139, 77.2090, 100.0);
        }
        @Override
        public void onCheckFailed(String error) {
            Toast.makeText(MainActivity.this, 
                "Security verification failed. Cannot arm drone.", 
                Toast.LENGTH_LONG).show();
        }
    });
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
