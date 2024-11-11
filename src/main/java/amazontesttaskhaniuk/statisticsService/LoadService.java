package amazontesttaskhaniuk.statisticsService;

import amazontesttaskhaniuk.model.ReportContainer;
import amazontesttaskhaniuk.parser.Parser;
import amazontesttaskhaniuk.repository.ReportContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class LoadService {
    @Value("${file.path}")
    private String filePath;
    private final ReportContainerRepository repository;

    public void loadReportSpecification() {
        try {
            ReportContainer reportContainer = Parser.parseJson(filePath);
            repository.deleteAll();
            repository.save(reportContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
