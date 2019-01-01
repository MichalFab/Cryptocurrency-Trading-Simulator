package com.crypto.tradingsimulator.models.currencies;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Coins")
@Data
public class Coin {
//TODO: separate enity class with DTO.

    @Id
    @Column(name = "id", nullable = false)
    @SerializedName(value = "id")
    private String id;

    @Column(name = "nameId", nullable = false)
    @SerializedName(value = "nameid")
    private String nameId;

    private String symbol;

    private String name;

    private Integer rank;

    @SerializedName(value = "price_usd")
    private String price;

    @SerializedName(value = "percent_change_24h")
    private String percenChange24h;

    @SerializedName(value = "percent_change_1h")
    private String percenChange1h;

    @SerializedName(value = "percent_change_7d")
    private String percenChange7d;

    @SerializedName(value = "price_btc")
    private String priceBTC;

    @SerializedName(value = "market_cap_usd")
    private String marketCapUSD;

}
