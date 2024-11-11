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

public class OrderedProductSalesB2B {
    double amount;
    String currencyCode;

    public OrderedProductSalesB2B(Amount amountB2B) {
        this.amount = amountB2B.getAmount();
        this.currencyCode = amountB2B.getCurrencyCode();
    }
}
