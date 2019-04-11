package com.ermias.snacktruck;

public class SnackClass {
    private String _snackName;
    private String _snackType;
    public SnackClass(String snackName, String snackType){
        _snackName=snackName;
        _snackType=snackType;
    }
    public String get_snackName() {
        return _snackName;
    }

    public String get_snackType() {
        return _snackType;
    }


}
