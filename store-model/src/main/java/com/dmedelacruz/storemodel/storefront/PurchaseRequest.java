package com.dmedelacruz.storemodel.storefront;

import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import com.dmedelacruz.storemodel.payment.PaymentDetails;
import com.dmedelacruz.storemodel.payment.PaymentMethod;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private String customerId;
    private List<ItemQtyDto> items;
    private String shippingAddressId;
    private PaymentMethod paymentMethod;
    private PaymentDetails paymentDetails;
}