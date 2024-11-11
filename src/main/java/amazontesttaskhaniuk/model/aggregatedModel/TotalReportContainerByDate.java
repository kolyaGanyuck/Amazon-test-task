package amazontesttaskhaniuk.model.aggregatedModel;

import amazontesttaskhaniuk.model.SalesByDate;
import amazontesttaskhaniuk.model.TrafficByDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TotalReportContainerByDate {
    List<SalesByDate> totalSalesByDate;
    List<TrafficByDate> totalTrafficByDate;
}
