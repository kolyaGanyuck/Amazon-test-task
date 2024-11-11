package amazontesttaskhaniuk.config;

import amazontesttaskhaniuk.statisticsService.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingConfig {
    private final LoadService loadService;

    @Scheduled(fixedRate = 60 * 1000 * 5)
    public void updateData(){
        loadService.loadReportSpecification();
    }
}
