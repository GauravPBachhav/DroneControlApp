package com.droneapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Stores user data locally on the device.
 * ⚠️ PROBLEM: Uses plain SharedPreferences — anyone with root access can read the XML file!
 */
public class UserPreferences {

    private SharedPreferences prefs;

    public UserPreferences(Context context) {
        // ❌ DANGER: Plain SharedPreferences — stored as readable XML file!
        // File location: /data/data/com.droneapp/shared_prefs/drone_prefs.xml
        prefs = context.getSharedPreferences("drone_prefs", Context.MODE_PRIVATE);
    }

    /**
     * Save user login token
     * ❌ PROBLEM: Auth token stored in plain text
     */
    public void saveAuthToken(String token) {
        // This saves as plain text in XML:
        // <string name="auth_token">eyJhbGciOiJIUzI1NiIs...</string>
        prefs.edit().putString("auth_token", token).apply();
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

    public String getDroneConnectionKey() {
        return prefs.getString("drone_connection_key", null);
    }

    /**
     * ❌ What the saved XML file looks like on the device:
     *
     * <?xml version='1.0' encoding='utf-8'?>
     * <map>
     *     <string name="auth_token">eyJhbGciOiJIUzI1NiIs...</string>
     *     <string name="username">pilot_gaurav</string>
     *     <string name="password">MyP@ssw0rd123!</string>
     *     <string name="drone_id">DRONE-X500-001</string>
     *     <string name="drone_connection_key">conn-key-abc123</string>
     * </map>
     *
     * Anyone with root access or a backup tool can read this file!
     */
}
