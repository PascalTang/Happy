package com.pascal.pray.android.utils.common;

/**
 * Created by pascal on 2017/12/26.
 */

public class CheckInfoUtils {
    public static boolean isValid(String text){
        return !text.trim().isEmpty();
    }

    private static final String mEmailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    public static boolean isEmailFormatAvailable(String email){
        return email.trim().matches(mEmailPattern);
    }

    public static boolean isBarcodeFormatAvailable(String barcode){
        barcode = barcode.trim().replace("-","");
        return barcode.length() == 12 ;
    }

    public static String barcodeFormat(String barcode) {
        barcode = barcode.trim().replace("-","");

        StringBuilder barcodeStringBuilder = new StringBuilder(barcode);

        if (barcode.length() >4) barcodeStringBuilder.insert(4,"-");
        if (barcode.length() >8) barcodeStringBuilder.insert(9,"-");

        return barcodeStringBuilder.toString();
    }

    public static float safeStringToFloat(String value){
        if (value.endsWith(".")){
            value = value.replace(".",".0");
        }
        float valueFloat = 0 ;
        try {
            valueFloat = Float.parseFloat(value);
        }catch (Exception e){}
        return valueFloat;
    }
}
