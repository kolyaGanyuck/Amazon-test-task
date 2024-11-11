package amazontesttaskhaniuk.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalesByDate {
     OrderedProductSales orderedProductSales;
     OrderedProductSalesB2B orderedProductSalesB2B;
     int unitsOrdered;
     int unitsOrderedB2B;
     int totalOrderItems;
     int totalOrderItemsB2B;
     Amount averageSalesPerOrderItem;
     Amount averageSalesPerOrderItemB2B;
     double averageUnitsPerOrderItem;
     double averageUnitsPerOrderItemB2B;
     Amount averageSellingPrice;
     Amount averageSellingPriceB2B;
     int unitsRefunded;
     double refundRate;
     int claimsGranted;
     Amount claimsAmount;
     Amount shippedProductSales;
     int unitsShipped;
     int ordersShipped;
}
