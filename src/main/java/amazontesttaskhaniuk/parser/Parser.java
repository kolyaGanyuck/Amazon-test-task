package amazontesttaskhaniuk.parser;

import amazontesttaskhaniuk.model.ReportContainer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Parser {
    public static ReportContainer parseJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), ReportContainer.class);

    }
}