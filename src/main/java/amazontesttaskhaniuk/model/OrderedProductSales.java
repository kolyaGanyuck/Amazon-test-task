package amazontesttaskhaniuk.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor


public class OrderedProductSales {
    double amount;
    String currencyCode;

    public OrderedProductSales(Amount amount) {
        this.amount = amount.getAmount();
        this.currencyCode = amount.getCurrencyCode();
    }
}
