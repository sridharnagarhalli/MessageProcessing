package com.sid.message.proccessing.services;

import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.UnparsableMessageException;
import com.sid.message.proccessing.models.messages.GenericMessage;
import com.sid.message.proccessing.exceptions.InvalidFileNameException;
import com.sid.message.proccessing.parsers.CsvParser;
import com.sid.message.proccessing.parsers.Parser;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class MessageReaderService {
    final static Logger LOGGER = Logger.getLogger(MessageReaderService.class);

    public GenericMessage read(final String fileName) throws InvalidFileNameException, UnparsableMessageException,
                    UnSupportedMessageTypeException {
        GenericMessage message = null;
        if (Objects.nonNull(fileName)) {
            final File file = new File(Constants.BASE_URL.getPath() + fileName);
            try (final Scanner sc = new Scanner(file)) {
                final StringBuilder msg = new StringBuilder();
                while (sc.hasNext()) {
                    msg.append(sc.nextLine());
                }
                final Parser parser = new CsvParser();
                message = parser.parse(msg.toString());
            } catch (final FileNotFoundException e1) {
                LOGGER.error(e1.getLocalizedMessage());
            }
        } else {
            throw new InvalidFileNameException(Constants.NULL_FILENAME_ERROR);
        }
        return message;
    }
}
