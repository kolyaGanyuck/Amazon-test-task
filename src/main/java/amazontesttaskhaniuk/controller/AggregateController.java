package amazontesttaskhaniuk.controller;

import amazontesttaskhaniuk.model.aggregatedModel.TotalReportContainerByAsin;
import amazontesttaskhaniuk.model.aggregatedModel.TotalReportContainerByDate;
import amazontesttaskhaniuk.statisticsService.AggregateStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class AggregateController {
    private final AggregateStatisticsService aggregateStatisticsService;
    @GetMapping("/aggregated-by-date")
    public TotalReportContainerByDate getAggregatedSalesByDate() {
        return aggregateStatisticsService.getAggregatedSalesByDate();
    }

    @GetMapping("/aggregated-by-asin")
    public TotalReportContainerByAsin getAggregatedSalesByAsin() {
        return aggregateStatisticsService.getAggregatedSalesByAsin();
    }
}
