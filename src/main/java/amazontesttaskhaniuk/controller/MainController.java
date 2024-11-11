package amazontesttaskhaniuk.controller;

import amazontesttaskhaniuk.model.SalesAndTrafficByAsin;
import amazontesttaskhaniuk.statisticsService.LoadService;
import amazontesttaskhaniuk.statisticsService.StatisticsService;
import amazontesttaskhaniuk.model.SalesAndTrafficByDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class MainController {
    private final StatisticsService statisticsService;
    private final LoadService loadService;

    @PostMapping("/load-report")
    public String loadReportData() {
        loadService.loadReportSpecification();
        return "Report data loaded successfully";
    }

    @PostMapping("/by-date")
    public SalesAndTrafficByDate getStatisticsByDate(@RequestParam String date) {
        return statisticsService.getStatisticsByDate(date).orElse(null);
    }

    @PostMapping("/date-range")
    public List<SalesAndTrafficByDate> getStatisticsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        return statisticsService.getSalesAndTrafficByDateRange(startDate, endDate);
    }

    @PostMapping("/by-asin")
    public SalesAndTrafficByAsin statisticsByAsin(@RequestParam String asin) {
        return statisticsService.getStatisticsByAsin(asin).orElse(null);
    }

    @PostMapping("/by-asin-list")
    public List<SalesAndTrafficByAsin> getStatisticsByAsinList(@RequestBody List<String> asinList) {
        return statisticsService.getStatisticsByAsinList(asinList);
    }

}
