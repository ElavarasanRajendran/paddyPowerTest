package com.paddy.power.test.paddypowertest.service.impl.logging;

import com.paddy.power.test.paddypowertest.common.dto.SelectionLiability;
import com.paddy.power.test.paddypowertest.common.dto.TotalLiability;
import com.paddy.power.test.paddypowertest.service.LoggerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.paddy.power.test.paddypowertest.common.constants.LoggerConstants.SELECTION_LIABILITY_LOG_HEADER;
import static com.paddy.power.test.paddypowertest.common.constants.LoggerConstants.TOTAL_LIABILITY_LOG_HEADER;

@Service
public class FileLoggerService implements LoggerService{

    private FileWriter logfileWriter;

    @PostConstruct
    public void setup() throws IOException {
        this.logfileWriter = this.createFileWriter();
    }

    @Override
    public void logReports(List<SelectionLiability> selectionLiabilityList,
                           List<TotalLiability> totalLiabilityList) throws IOException {
        logfileWriter.write("The Selection Liability report is: \n");
        logfileWriter.write(SELECTION_LIABILITY_LOG_HEADER);
        selectionLiabilityList.forEach( selectionLiability -> {
            try {
                logfileWriter.write(selectionLiability.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logfileWriter.write("\n");
        logfileWriter.write("The Total Liability report is: \n");
        logfileWriter.write(TOTAL_LIABILITY_LOG_HEADER);
        logfileWriter.write("\n");
        totalLiabilityList.forEach( totalLiability -> {
            try {
                logfileWriter.write(totalLiability.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logfileWriter.close();
    }

    private FileWriter createFileWriter() throws IOException {
        return new FileWriter("logFile."+ LocalDateTime.now()+".txt");
    }
}
