# ======================================
# ProGuard Rules for DroneControlApp
# ======================================

# --- KEEP data model classes (so JSON parsing doesn't break) ---
-keep class com.droneapp.config.** { *; }

# --- REMOVE all Log.d and Log.v in release builds ---
# This is CRITICAL — stops secrets from appearing in Logcat!
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
}

# --- Standard Android rules ---
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# --- Keep classes used by Gson/Retrofit (if you use them) ---
# -keep class com.droneapp.models.** { *; }
