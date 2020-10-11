package com.sid.parsers;

import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.UnparsableMessageException;
import com.sid.message.proccessing.models.messages.GenericMessage;
import com.sid.message.proccessing.models.messages.SaleDetailsMessage;
import com.sid.message.proccessing.models.messages.SaleOperationMessage;
import com.sid.message.proccessing.models.messages.SalesMessage;
import com.sid.message.proccessing.parsers.CsvParser;
import com.sid.message.proccessing.parsers.Parser;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvParserTest {
    final static Logger LOGGER = Logger.getLogger(CsvParserTest.class);

    @BeforeEach
    public void initSeeMap() {

    }

    @Test
    public void sendingEmptyMsgShouldThrowUnparsableMessageException() {
        Parser parser = new CsvParser();
        Exception exception = assertThrows(UnparsableMessageException.class, () -> {
            parser.parse("");
        });
        assertEquals(Constants.EMPTY_MSG_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void sendingNullMsgShouldThrowUnparsableMessageException() {
        Parser parser = new CsvParser();
        Exception exception = assertThrows(UnparsableMessageException.class, () -> {
            parser.parse(null);
        });
        assertEquals(Constants.EMPTY_MSG_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void sendingInvalidMsgFormatShouldThrowUnparsableMessageException() {
        Parser parser = new CsvParser();
        Exception exception = assertThrows(UnparsableMessageException.class, () -> {
            parser.parse("dummy nonsense");
        });
        assertEquals(Constants.INVALID_MSG_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void sendingInvalidMsgTypeShouldThrowUnSupportedMessageTypeException() {
        Parser parser = new CsvParser();
        Exception exception = assertThrows(UnSupportedMessageTypeException.class, () -> {
            parser.parse("5,orange,15");
        });
        assertEquals(Constants.INVALID_MSG_TYPE_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void type1MsgShoudBeParsedCorrectly() {
        Parser parser = new CsvParser();
        GenericMessage message;
        try {
            message = parser.parse("1,orange,15");
            assertNotNull(message);
            assertTrue(message instanceof SaleDetailsMessage);
            assertEquals(1, message.getMessageType());
        } catch (UnparsableMessageException | UnSupportedMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void type2MsgShoudBeParsedCorrectly() {
        Parser parser = new CsvParser();
        GenericMessage message;
        try {
            message = parser.parse("2,milk,5,4");
            assertNotNull(message);
            assertTrue(message instanceof SalesMessage);
            assertEquals(2, message.getMessageType());
        } catch (UnparsableMessageException | UnSupportedMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void type3MsgShoudBeParsedCorrectly() {
        Parser parser = new CsvParser();
        GenericMessage message;
        try {
            message = parser.parse("3,carrot,Add,4");
            assertNotNull(message);
            assertTrue(message instanceof SaleOperationMessage);
            assertEquals(3, message.getMessageType());
        } catch (UnparsableMessageException | UnSupportedMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }
}
