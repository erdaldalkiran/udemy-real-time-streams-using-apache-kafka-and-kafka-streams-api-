package com.example.foreignkeyexample.types;

import lombok.ToString;

import java.util.UUID;

@ToString
public class EnhancedListing {
    public UUID ID;
    public String Sku;
    public String ProductName;
    public int BuyboxOrder;

}
