package com.droneapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Stores user data locally on the device.
 * ⚠️ PROBLEM: Uses plain SharedPreferences — anyone with root access can read the XML file!
 */
public class UserPreferences {

    private SharedPreferences prefs;

    // public UserPreferences(Context context) {
    //     // ❌ DANGER: Plain SharedPreferences — stored as readable XML file!
    //     // File location: /data/data/com.droneapp/shared_prefs/drone_prefs.xml
    //     prefs = context.getSharedPreferences("drone_prefs", Context.MODE_PRIVATE);
    // }




 public UserPreferences(Context context) {
        try {
            // Create or get the master key from Android Keystore (hardware chip)
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            // Create encrypted preferences (same API as normal SharedPreferences!)
            prefs = EncryptedSharedPreferences.create(
                "secure_drone_prefs",                                           // file name
                masterKeyAlias,                                                  // master key
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,   // encrypt key names
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM  // encrypt values
            );
        } catch (Exception e) {
            // Fallback — should never happen on API 24+
            prefs = context.getSharedPreferences("drone_prefs", Context.MODE_PRIVATE);
        }
    }
















    /**
     * Save user login token
     * ❌ PROBLEM: Auth token stored in plain text
     */
    public void saveAuthToken(String token) {
        // This saves as plain text in XML:
        // <string name="auth_token">eyJhbGciOiJIUzI1NiIs...</string>
        prefs.edit().putString("auth_token", token).apply();



        // atfer adding dependecies and EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM  // encrypt values

           // Saved as: <string name="Xk2mN...">Bf3kL9x2mN...</string>
        // NOT as:   <string name="auth_token">eyJhbGci...</string>


    }

    public String getAuthToken() {
        return prefs.getString("auth_token", null);
    }

    /**
     * Save user credentials
     * ❌ PROBLEM: Password stored in plain text!
     */
    public void saveUserCredentials(String username, String password) {
        prefs.edit()
                .putString("username", username)
                .putString("password", password)    // ❌ NEVER store passwords in plain text!
                .apply();

                //now encrypted 
    }

    /**
     * Save drone connection details
     * ❌ PROBLEM: Connection secrets in plain text
     */
    public void saveDroneConnectionInfo(String droneId, String connectionKey) {
        prefs.edit()
                .putString("drone_id", droneId)
                .putString("drone_connection_key", connectionKey)  // ❌ Plain text!
                .apply();
    }
 