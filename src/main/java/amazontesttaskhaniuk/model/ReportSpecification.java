package amazontesttaskhaniuk.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportSpecification {
    String id;
    String reportType;
    ReportOptions reportOptions;
    String dataStartTime;
    String dataEndTime;
    List<String> marketplaceIds;
    List<SalesAndTrafficByDate> salesAndTrafficByDate;
    List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
