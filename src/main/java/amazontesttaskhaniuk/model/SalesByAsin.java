package amazontesttaskhaniuk.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalesByAsin {
     int unitsOrdered;
     int unitsOrderedB2B;
     OrderedProductSales orderedProductSales;
     OrderedProductSales orderedProductSalesB2B;
     int totalOrderItems;
     int totalOrderItemsB2B;
}
