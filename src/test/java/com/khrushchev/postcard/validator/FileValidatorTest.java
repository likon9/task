package com.khrushchev.postcard.validator;

import com.khrushchev.postcard.validator.FileValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FileValidatorTest {

    public static final String CORRECT_FILE = "data\\old_cards.xml";
    public static final String NOT_CORRECT_FILE = "data\\old_card_null.xml";

    @Test()
    public void pathIsNull() {
        boolean result = FileValidator.isFiveExistIsFileisNull(null);
        assertFalse(result);
    }

    @Test
    public void testPositiveExistIsFileValid() {
        boolean result = FileValidator.isFiveExistIsFileisNull(CORRECT_FILE);
        assertTrue(result);
    }

    @Test
    public void testNegativeExistIsFileValid() {
        boolean result = FileValidator.isFiveExistIsFileisNull(NOT_CORRECT_FILE);
        assertFalse(result);
    }


    @Test
    public void testPositiveIsFileIsFileValid() {
        boolean result = FileValidator.isFiveExistIsFileisNull(CORRECT_FILE);
        assertTrue(result);
    }

    @Test
    public void testNegativeIsFileIsFileValid() {
        boolean result = FileValidator.isFiveExistIsFileisNull(NOT_CORRECT_FILE);
        assertFalse(result);
    }
}
