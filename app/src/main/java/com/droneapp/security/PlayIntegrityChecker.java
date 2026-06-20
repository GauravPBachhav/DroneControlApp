package com.droneapp.security;

import android.content.Context;
import android.util.Log;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import java.util.UUID;

/**
 * Uses Google's Play Integrity API to verify:
 * 1. Is this a real device? (not emulator)
 * 2. Is the app genuine? (not modified)
 * 3. Is the app from Play Store? (not sideloaded)
 *
 * Google does the checking on THEIR servers — hackers can't bypass this.
 */
public class PlayIntegrityChecker {

    private static final String TAG = "PlayIntegrity";

    /**
     * Request an integrity token from Google.
     * 
     * How it works:
     * 1. We generate a random "nonce" (one-time code)
     * 2. Google checks the device/app and creates a signed token
     * 3. We send this token to OUR server
     * 4. Our server verifies it with Google
     */
    public static void verifyIntegrity(Context context, IntegrityCallback callback) {

        // Step 1: Create the Integrity Manager
        IntegrityManager integrityManager = IntegrityManagerFactory.create(context);

        // Step 2: Generate a unique one-time code (nonce)
        // This prevents replay attacks (hacker can't reuse an old valid token)
        String nonce = UUID.randomUUID().toString();

        // Step 3: Request a token from Google
        integrityManager.requestIntegrityToken(
            IntegrityTokenRequest.builder()
                .setNonce(nonce)
                .build()
        )
        // Step 4: Google responds with a token
        .addOnSuccessListener(response -> {
            String token = response.token();
            Log.d(TAG, "Got integrity token, sending to server for verification...");

            // Step 5: Send token to YOUR server for verification
            // NEVER verify the token on the phone — always on your server!
            callback.onTokenReceived(token, nonce);
        })
        // If Google can't verify (no internet, Play Services missing, etc.)
        .addOnFailureListener(e -> {
            Log.e(TAG, "Integrity check failed: " + e.getMessage());
            callback.onCheckFailed(e.getMessage());
        });
    }

    /**
     * Callback interface — your activity implements this
     */
    public interface IntegrityCallback {
        void onTokenReceived(String token, String nonce);  // Send to your server
        void onCheckFailed(String error);                   // Handle failure
    }
}
