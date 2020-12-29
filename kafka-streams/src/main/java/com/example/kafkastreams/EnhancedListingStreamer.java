package com.example.kafkastreams;

import com.example.kafkastreams.types.BuyboxOrder;
import com.example.kafkastreams.types.EnhancedListing;
import com.example.kafkastreams.types.Listing;
import com.example.kafkastreams.types.Product;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.*;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.UUID;

@EnableBinding(CustomBindings.class)
public class EnhancedListingStreamer {
    @StreamListener
    @SendTo("enhanced-listing")
    public KStream<UUID, EnhancedListing> process(
        @Input("listing") KTable<UUID, Listing> listingStream,
        @Input("buybox") KTable<UUID, BuyboxOrder> buyboxStream,
        @Input("product") KTable<String, Product> productStream
    ) {
        var enhancedListingSer = new JsonSerializer<EnhancedListing>();
        var enhancedListingDes = new JsonDeserializer<EnhancedListing>();
        enhancedListingDes.addTrustedPackages("*");

        var listingWithBuyboxOrder = listingStream.leftJoin(buyboxStream,
            (listing, buyboxOrder) -> {
                var result = new EnhancedListing();
                result.ID = listing.ID;
                result.Sku = listing.Sku;
                result.BuyboxOrder = buyboxOrder == null ? 99 : buyboxOrder.Order;
                return result;
            },
            Named.as("listingWithBuyboxOrder")
        );

        var listingWithBuyboxOrderAndProductName = listingWithBuyboxOrder
            .toStream()
            .selectKey(
                (uuid, enhancedListing) -> enhancedListing.Sku
            )
            .leftJoin(productStream,
                (enhancedListing, product) -> {
                    enhancedListing.ProductName = product == null ? "no-name" : product.Name;
                    return enhancedListing;
                },
                Joined.with(Serdes.String(), new JsonSerde<>(enhancedListingSer, enhancedListingDes), new JsonSerde<Product>())
            ).selectKey((sku, enhancedListing) -> enhancedListing.ID);

        return listingWithBuyboxOrderAndProductName;
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
