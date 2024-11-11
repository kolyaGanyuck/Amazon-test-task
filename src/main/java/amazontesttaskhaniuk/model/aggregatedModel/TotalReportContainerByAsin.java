package amazontesttaskhaniuk.model.aggregatedModel;

import amazontesttaskhaniuk.model.SalesByAsin;
import amazontesttaskhaniuk.model.SalesByDate;
import amazontesttaskhaniuk.model.TrafficByAsin;
import amazontesttaskhaniuk.model.TrafficByDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TotalReportContainerByAsin {
    List<SalesByAsin> totalSalesByAsin;
    List<TrafficByAsin> totalTrafficByAsin;
}
