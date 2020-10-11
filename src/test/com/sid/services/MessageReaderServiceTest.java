package com.sid.services;

import com.sid.message.proccessing.exceptions.InvalidFileNameException;
import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.UnparsableMessageException;
import com.sid.message.proccessing.models.messages.GenericMessage;
import com.sid.message.proccessing.models.messages.SaleDetailsMessage;
import com.sid.message.proccessing.services.MessageReaderService;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageReaderServiceTest {
    final static Logger LOGGER = Logger.getLogger(MessageReaderServiceTest.class);

    @Test
    public void nonEmptyFileShouldBeReadCorrectly() {
        MessageReaderService messageReaderService = new MessageReaderService();
        GenericMessage genericMessage;
        try {
            genericMessage = messageReaderService.read("sale1.txt");
            assertNotNull(genericMessage);
            assertEquals(1, genericMessage.getMessageType());
            assertTrue(genericMessage instanceof SaleDetailsMessage);
        } catch (InvalidFileNameException | UnparsableMessageException | UnSupportedMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void emptyFileShouldThrowInvalidFileNameException() {
        MessageReaderService messageReaderService = new MessageReaderService();
        Exception exception = assertThrows(InvalidFileNameException.class, () -> {
            messageReaderService.read(null);
        });
        assertEquals(Constants.NULL_FILENAME_ERROR,
                        exception.getLocalizedMessage());
    }

}
