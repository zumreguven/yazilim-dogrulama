package com.example.careermanagement.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidationTest {

    @Test
    void whenUsernameIsValid_thenReturnTrue() {
        String username = "zumre123";

        boolean isValid = username.length() >= 3;

        assertTrue(isValid);
    }

    @Test
    void whenUsernameIsTooShort_thenReturnFalse() {
        String username = "ab";

        boolean isValid = username.length() >= 3;

        assertFalse(isValid);
    }
}
