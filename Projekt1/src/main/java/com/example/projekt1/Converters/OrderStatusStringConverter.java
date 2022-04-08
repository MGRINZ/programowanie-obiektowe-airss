package com.example.projekt1.Converters;

import com.example.projekt1.Model.OrderStatus;
import javafx.util.StringConverter;

public class OrderStatusStringConverter extends StringConverter<OrderStatus> {
    @Override
    public String toString(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case IN -> "Przyjęto";
            case IN_SERVICE -> "W realizacji";
            case READY -> "Do odbioru";
            case OUT -> "Zakończone";
        };
    }

    @Override
    public OrderStatus fromString(String s) {
        return switch (s) {
            default -> OrderStatus.IN;
            case "W realizacji" -> OrderStatus.IN_SERVICE;
            case "Do odbioru" -> OrderStatus.READY;
            case "Zakończone" -> OrderStatus.OUT;
        };
    }
}
