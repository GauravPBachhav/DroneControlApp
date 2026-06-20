package com.droneapp.config;

/**
 * ⚠️ PROBLEM: All secrets are hardcoded here — visible to ANYONE who decompiles the APK!
 */
public class AppConfig {

    // ❌ DANGER: API Keys hardcoded
    public static final String DRONE_API_KEY = "sk-drone-4f8a2b1c-9d3e-4567-abcd-ef0123456789";
    public static final String GOOGLE_MAPS_API_KEY = "AIzaSyB-FAKE-KEY-xYz123AbCdEfGhIjKlMnOp";

    // ❌ DANGER: Server credentials hardcoded
    public static final String SERVER_URL = "https://api.dronecontrol.com/v2";
    public static final String SERVER_USERNAME = "admin_drone_master";
    public static final String SERVER_PASSWORD = "Super$ecret@2026!";

    // ❌ DANGER: Database credentials hardcoded
    public static final String DB_HOST = "db.dronecontrol.com";
    public static final String DB_PORT = "5432";
    public static final String DB_NAME = "drone_telemetry_db";
    public static final String DB_USER = "db_admin";
    public static final String DB_PASSWORD = "Postgr3s@Pr0d#2026";

    // ❌ DANGER: Firebase credentials hardcoded
    public static final String FIREBASE_PROJECT_ID = "drone-control-prod-12345";
    public static final String FIREBASE_API_KEY = "AIzaSyC-FIREBASE-KEY-1234567890abcdef";
    public static final String FIREBASE_AUTH_DOMAIN = "drone-control-prod-12345.firebaseapp.com";

    // ❌ DANGER: Encryption key hardcoded (ironic!)
    public static final String ENCRYPTION_SECRET = "mY-eNcRyPtIoN-sEcReT-kEy-2026!@#";

    // ❌ DANGER: Payment gateway credentials
    public static final String RAZORPAY_KEY_ID = "rzp_live_FAKE1234567890";
    public static final String RAZORPAY_KEY_SECRET = "FAKE_razorpay_secret_abcdef123456";
}
