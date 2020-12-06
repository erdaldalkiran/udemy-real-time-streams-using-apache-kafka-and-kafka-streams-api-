package com.example.foreignkeyexample;

import com.example.foreignkeyexample.types.Buybox;
import com.example.foreignkeyexample.types.EnhancedListing;
import com.example.foreignkeyexample.types.Listing;
import com.example.foreignkeyexample.types.Product;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.UUID;

@EnableBinding(CustomBindings.class)
public class EnhancedListingStreamer {
    @StreamListener
    @SendTo("enhanced-listing")
    public KStream<UUID, EnhancedListing> process(
        @Input("listing") KTable<UUID, Listing> listingTable,
        @Input("buybox") KTable<UUID, Buybox> buyboxTable,
        @Input("product") KTable<String, Product> productTable
    ) {
        var listingWithProductInfo = listingTable.leftJoin(
            productTable,
            listing -> listing.Sku,
            (listing, product) -> {
                var enhancedListing = new EnhancedListing();
                enhancedListing.ID = listing.ID;
                enhancedListing.Sku = listing.Sku;
                enhancedListing.ProductName = product == null ? "no-name" : product.Name;
                return enhancedListing;
            },
            Materialized.with(AppSerdes.UUID(), AppSerdes.EnhancedListing()));

        var enhancedListingTable = listingWithProductInfo.leftJoin(
            buyboxTable,
            (enhancedListing, buybox) -> {
                enhancedListing.BuyboxOrder = buybox == null ? 99 : buybox.Order;
                return enhancedListing;
            }
        );

        return enhancedListingTable.toStream();
    }
}

interface CustomBindings {

    @Input("listing")
    KTable<?, ?> input1();

    @Input("buybox")
    KTable<?, ?> input2();

    @Input("product")
    KTable<?, ?> input3();

    @Output("enhanced-listing")
    KStream<?, ?> output();
}