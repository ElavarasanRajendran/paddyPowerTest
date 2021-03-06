package com.paddy.power.test.paddypowertest;

import com.paddy.power.test.paddypowertest.common.dto.Bet;
import com.paddy.power.test.paddypowertest.common.dto.SelectionLiability;
import com.paddy.power.test.paddypowertest.common.dto.TotalLiability;
import com.paddy.power.test.paddypowertest.service.FileParserService;
import com.paddy.power.test.paddypowertest.service.LoggerService;
import com.paddy.power.test.paddypowertest.service.ReportService;
import com.paddy.power.test.paddypowertest.service.impl.logging.LoggerFactoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class Client implements CommandLineRunner {

    private final FileParserService fileParserService;
    private final ReportService reportService;

    @Value("${paddy.BetFair.Test.Logging.Type:#{null}}")
    private String logType;

    public Client(final FileParserService fileParserService,
                  final ReportService reportService) {
        this.fileParserService = fileParserService;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();

        File inputFile = new File(classLoader.getResource("BettingSheet.csv").getFile());
        List<Bet> betList = fileParserService.readInputData(inputFile);
        List<TotalLiability> totalLiabilityList = reportService.generateTotalLiabilityReport(betList);
        List<SelectionLiability> selectionLiabilities = reportService.generateSelectionLiabilityReport(betList);
        LoggerService loggerService = LoggerFactoryService.createInstance(logType);
        loggerService.logReports(selectionLiabilities,totalLiabilityList);
    }
}
