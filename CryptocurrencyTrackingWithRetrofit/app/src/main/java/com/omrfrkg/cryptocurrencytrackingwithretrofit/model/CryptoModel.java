package com.omrfrkg.cryptocurrencytrackingwithretrofit.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    @SerializedName("name")
    public String name;
    @SerializedName("volume_1day_usd")
    public String price;
}
