package amazontesttaskhaniuk.statisticsService;

import amazontesttaskhaniuk.model.SalesAndTrafficByAsin;
import amazontesttaskhaniuk.model.SalesAndTrafficByDate;
import amazontesttaskhaniuk.repository.ReportContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final ReportContainerRepository repository;

    @Cacheable(value = "date", key = "#date")
    public Optional<SalesAndTrafficByDate> getStatisticsByDate(String date) {
        return repository.findBySalesAndTrafficByDate_Date(date)
                .flatMap(reportContainer -> reportContainer.getSalesAndTrafficByDate()
                        .stream()
                        .filter(data -> data.getDate().equals(date))
                        .findFirst());
    }


    @Cacheable(value = "multipleDates", key = "#startDate + '-' + #endDate")
    public List<SalesAndTrafficByDate> getSalesAndTrafficByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        return repository.findAll().stream()
                .flatMap(reportContainer -> reportContainer.getSalesAndTrafficByDate().stream())
                .filter(data -> {
                    LocalDate dataDate = LocalDate.parse(data.getDate(), formatter);
                    return (dataDate.isEqual(start) || dataDate.isAfter(start)) &&
                            (dataDate.isEqual(end) || dataDate.isBefore(end));
                })
                .collect(Collectors.toList());
    }

    @Cacheable(value = "asin", key = "#asinId")
    public Optional<SalesAndTrafficByAsin> getStatisticsByAsin(String asinId) {
        return repository.findAll()
                .stream()
                .flatMap(reportContainer -> reportContainer.getSalesAndTrafficByAsin()
                        .stream())
                .filter(salesAndTraffic -> salesAndTraffic.getParentAsin().equals(asinId))
                .findFirst();
    }

    @Cacheable(value = "multipleAsins", key = "#asinList")
    public List<SalesAndTrafficByAsin> getStatisticsByAsinList(List<String> asinList) {
        return repository.findAll()
                .stream()
                .flatMap(reportContainer -> reportContainer.getSalesAndTrafficByAsin().stream())
                .filter(salesAndTraffic -> asinList.contains(salesAndTraffic.getParentAsin()))
                .collect(Collectors.toList());
    }


}


