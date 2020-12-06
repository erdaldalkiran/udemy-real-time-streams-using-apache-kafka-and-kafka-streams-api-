package com.example.foreignkeyexample;

import com.example.foreignkeyexample.types.Buybox;
import com.example.foreignkeyexample.types.EnhancedListing;
import com.example.foreignkeyexample.types.Listing;
import com.example.foreignkeyexample.types.Product;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {
    static final class ListingSerde extends WrapperSerde<Listing> {
        ListingSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Listing> Listing() {
        var serde = new ListingSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Listing.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class ProductSerde extends WrapperSerde<Product> {
        ProductSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Product> Product() {
        var serde = new ProductSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Product.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class BuyboxSerde extends WrapperSerde<Buybox> {
        BuyboxSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Buybox> Buybox() {
        var serde = new BuyboxSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Buybox.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class EnhancedListingSerde extends WrapperSerde<EnhancedListing> {
        EnhancedListingSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<EnhancedListing> EnhancedListing() {
        var serde = new EnhancedListingSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, EnhancedListing.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

}
