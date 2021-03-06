package com.mkao.newsgo.configuration;

import android.content.res.Resources;

import androidx.core.os.ConfigurationCompat;

import java.util.Locale;

public class Config {
    private static  final String[] SUPPORT_LANG = {"ar","de","en","es","fr","he","it","nl"
            ,"no","pt","ru","se","ud","zh"};
    private static final String[] SUPPORT_COUNTRY = {"ae","ar","at","au","be","bg","br","ca","ch"
            ,"cn","co","cu","cz","de","eg","fr","gb","gr","hk","hu","id","ie","il","in","it","jp"
            ,"kr","lt","lv","ma","mx","my","ng","nl","no","nz","ph","pl","pt","ro","rs","ru",
            "sa","se","sg","si","sk","th","tr","tw","ua","us","ve","za","ke"};
    // Locale object represents a specific geographical, political, or cultural region
    //Lang for the dialect
    //County for Country
    private static Locale mLocale;
    private  static String lang;
    private  static  String country;

    public static String geCountry() {
        if (mLocale == null) {
            Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration())
                    .getFirstMatch(SUPPORT_COUNTRY);
            if (mLocale == null) mLocale = Locale.ENGLISH;

        }
        country = mLocale.getCountry().toLowerCase();
        return country;
    }

    public static String getLang() {
        Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration())
                .getFirstMatch(SUPPORT_LANG);
        if (locale==null) locale=Locale.ENGLISH;
        mLocale = locale;
        lang = locale.getLanguage();
        return lang;
    }
    public  static  String getSort(Sort type){
        return type.toString();
    }
}
