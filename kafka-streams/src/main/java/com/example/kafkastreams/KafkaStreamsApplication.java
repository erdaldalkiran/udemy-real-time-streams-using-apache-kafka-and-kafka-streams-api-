package com.example.kafkastreams;

import com.example.kafkastreams.types.BuyboxOrder;
import com.example.kafkastreams.types.Listing;
import com.example.kafkastreams.types.Product;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class KafkaStreamsApplication {

    public static void main(String[] args) {
        Gson gsonSerializer = new Gson();

        var listing = new Listing();
        listing.ID = UUID.randomUUID();
        listing.Sku = "Sku";
        var ls = gsonSerializer.toJson(listing);

        var buyboxOrder = new BuyboxOrder();
        buyboxOrder.Order = 3;
        buyboxOrder.Order = 3;
        buyboxOrder.ListingID = listing.ID;
        var bbx = gsonSerializer.toJson(buyboxOrder);

        var product = new Product();
        product.Sku = listing.Sku;
        product.Name = "Cikolata";
        var p = gsonSerializer.toJson(product);

        SpringApplication.run(KafkaStreamsApplication.class, args);
    }

}
