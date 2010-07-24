package org.thialfihar.android.apg;

import org.bouncycastle2.bcpg.HashAlgorithmTags;
import org.bouncycastle2.openpgp.PGPEncryptedData;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static Preferences mPreferences;
    private SharedPreferences mSharedPreferences;

    public static synchronized Preferences getPreferences(Context context)
    {
        if (mPreferences == null) {
            mPreferences = new Preferences(context);
        }
        return mPreferences;
    }

    private Preferences(Context context)
    {
        mSharedPreferences = context.getSharedPreferences("APG.main", Context.MODE_PRIVATE);
    }

    public String getLanguage() {
        return mSharedPreferences.getString(Constants.pref.language, "");
    }

    public void setLanguage(String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.pref.language, value);
        editor.commit();
    }

    public int getPassPhraseCacheTtl() {
        int ttl = mSharedPreferences.getInt(Constants.pref.pass_phrase_cache_ttl, 180);
        // fix the value if it was set to "never" in previous versions, which currently is not
        // supported
        if (ttl == 0) {
            ttl = 180;
        }
        return ttl;
    }

    public void setPassPhraseCacheTtl(int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.pref.pass_phrase_cache_ttl, value);
        editor.commit();
    }

    public int getDefaultEncryptionAlgorithm() {
        return mSharedPreferences.getInt(Constants.pref.default_encryption_algorithm,
                                         PGPEncryptedData.AES_256);
    }

    public void setDefaultEncryptionAlgorithm(int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.pref.default_encryption_algorithm, value);
        editor.commit();
    }

    public int getDefaultHashAlgorithm() {
        return mSharedPreferences.getInt(Constants.pref.default_hash_algorithm,
                                         HashAlgorithmTags.SHA256);
    }

    public void setDefaultHashAlgorithm(int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.pref.default_hash_algorithm, value);
        editor.commit();
    }

    public int getDefaultMessageCompression() {
        return mSharedPreferences.getInt(Constants.pref.default_message_compression,
                                         Id.choice.compression.zlib);
    }

    public void setDefaultMessageCompression(int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.pref.default_message_compression, value);
        editor.commit();
    }

    public int getDefaultFileCompression() {
        return mSharedPreferences.getInt(Constants.pref.default_file_compression,
                                         Id.choice.compression.none);
    }

    public void setDefaultFileCompression(int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.pref.default_file_compression, value);
        editor.commit();
    }

    public boolean getDefaultAsciiArmour() {
        return mSharedPreferences.getBoolean(Constants.pref.default_ascii_armour, false);
    }

    public void setDefaultAsciiArmour(boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(Constants.pref.default_ascii_armour, value);
        editor.commit();
    }

    public boolean hasSeenChangeLog(String version) {
        return mSharedPreferences.getBoolean(Constants.pref.has_seen_change_log + version,
                                       false);
    }

    public void setHasSeenChangeLog(String version, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(Constants.pref.has_seen_change_log + version, value);
        editor.commit();
    }
}