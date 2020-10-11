package com.sid.message.proccessing;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import com.sid.message.proccessing.exceptions.InvalidAdjustmentException;
import com.sid.message.proccessing.exceptions.InvalidFileNameException;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import com.sid.message.proccessing.exceptions.InvalidMessageTypeException;
import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.UnparsableMessageException;
import com.sid.message.proccessing.exceptions.UnsupportedAdjustmentOperationException;
import com.sid.message.proccessing.models.messages.GenericMessage;
import com.sid.message.proccessing.models.reports.SaleAdjustment;
import com.sid.message.proccessing.models.reports.SalesReport;
import com.sid.message.proccessing.processors.Processor;
import com.sid.message.proccessing.processors.ProcessorCreator;
import com.sid.message.proccessing.processors.ProcessorFactory;
import com.sid.message.proccessing.services.FileService;
import com.sid.message.proccessing.services.MessageReaderService;
import com.sid.message.proccessing.services.SaleAdjustmentsReportService;
import com.sid.message.proccessing.services.TotalSalesReportService;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;

public class Application {
    final static Logger LOGGER = Logger.getLogger(Application.class);
    static Integer numberOfMessages = 0;
    static Integer counter = 0;

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Please enter a file name to process:");
            final MessageReaderService messageReader = new MessageReaderService();
            final FileService fileservice = new FileService();
            while (scanner.hasNext()) {
                final GenericMessage message = messageReader.read(scanner.nextLine());
                if (Objects.nonNull(message)) {
                    processMessage(message);
                    if (counter == 10) {
                        createTotalPriceByProductReport(fileservice);
                    }
                    if (numberOfMessages == 50) {
                        createAdjustmentReport(fileservice);
                        break;
                    }
                    LOGGER.info(Constants.MSG_PROCESSED);
                } else {
                    LOGGER.error(Constants.COUDNT_PROCESS);
                    LOGGER.error(Constants.NULL_MSG_ERROR);
                }
                LOGGER.info("Enter next message:");
            }
        } catch (InvalidFileNameException | UnparsableMessageException | UnSupportedMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    private static void createTotalPriceByProductReport(final FileService fileservice) {
        final TotalSalesReportService service = new TotalSalesReportService();
        final List<SalesReport> report = service.getTotalPriceByProductReport();
        LOGGER.info(report);
        final Calendar now = Calendar.getInstance();
        final String fileName = "TotalSalesReport_" + now.getTimeInMillis() + ".txt";
        fileservice.saveToFile(fileName, report.toString());
        counter = 0;
    }

    private static void createAdjustmentReport(final FileService fileservice) {
        LOGGER.info("The Application is pausing and will stop accepting new messages");
        final Map<String, List<SaleAdjustment>> report = SaleAdjustmentsReportService.getAdjustmentMap();
        LOGGER.info(report);
        final Calendar now = Calendar.getInstance();
        final String fileName = "TotalAdjustmentsReport_" + now.getTimeInMillis() + ".txt";
        fileservice.saveToFile(fileName, report.toString());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void processMessage(final GenericMessage message) {
        try {
            final ProcessorFactory processorFactory = ProcessorCreator.getFacory(message.getMessageType());
            final Processor processor = processorFactory.getProcessor();
            processor.process(message);
            numberOfMessages++;
            counter++;
        } catch (UnSupportedMessageTypeException | InvalidMessageTypeException | InvalidSaleException | InvalidMessageException | InvalidAdjustmentException | UnsupportedAdjustmentOperationException e) {
            LOGGER.error(e.getLocalizedMessage());
        }

    }
}
