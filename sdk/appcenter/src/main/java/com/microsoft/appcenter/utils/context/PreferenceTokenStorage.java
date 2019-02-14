package com.microsoft.appcenter.utils.context;

import android.content.Context;

import com.microsoft.appcenter.utils.crypto.CryptoUtils;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;

/**
 * Storage for tokens that uses Shared Preferences. Handles saving and encryption.
 */
public class PreferenceTokenStorage implements ITokenStorage {

    /**
     * Context instance.
     */
    private final Context mContext;

    /**
     * Default constructor.
     *
     * @param context context instance.
     */
    PreferenceTokenStorage(Context context) {
        mContext = context;
    }

    /**
     * Used for authentication requests, string field for auth token.
     */
    @SuppressWarnings("WeakerAccess")
    static final String PREFERENCE_KEY_AUTH_TOKEN = "AppCenter.auth_token";

    @Override
    public void saveToken(String token) {
        String encryptedToken = CryptoUtils.getInstance(mContext).encrypt(token);
        SharedPreferencesManager.putString(PREFERENCE_KEY_AUTH_TOKEN, encryptedToken);
    }

    @Override
    public String getToken() {
        String encryptedToken = SharedPreferencesManager.getString(PREFERENCE_KEY_AUTH_TOKEN, null);
        if (encryptedToken == null || encryptedToken.length() == 0) {
            return null;
        }
        CryptoUtils.DecryptedData decryptedData = CryptoUtils.getInstance(mContext).decrypt(encryptedToken, false);
        return decryptedData.getDecryptedData();
    }

    @Override
    public void removeToken() {
        SharedPreferencesManager.remove(PREFERENCE_KEY_AUTH_TOKEN);
    }
}
