/*
 * Copyright 2024 My Virtual Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.myvirtualhub.omni.drivers.core.handler;

import br.com.myvirtualhub.omni.commons.context.OmniContext;
import br.com.myvirtualhub.omni.commons.context.OmniContextHolder;
import br.com.myvirtualhub.omni.commons.core.GenericOmniProcessId;
import br.com.myvirtualhub.omni.commons.exceptions.PhoneNumberException;
import br.com.myvirtualhub.omni.drivers.core.response.ErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OmniExceptionHandlerTest {

    private GenericOmniProcessId genericOmniProcessId;

    private OmniExceptionHandler omniExceptionHandler;

    @BeforeEach
    void setUp() {
        genericOmniProcessId = new GenericOmniProcessId();
        final OmniContext omniContext = new OmniContext();
        omniContext.setClientMessageId("1234567890");
        omniContext.setOmniProcessId(genericOmniProcessId);
        OmniContextHolder.setContext(omniContext);
        omniExceptionHandler = new OmniExceptionHandler();
    }

    @AfterEach
    void tearDown() {
        OmniContextHolder.clearContext();
    }

    @Test
    void testHandleException() {
        Exception exception = new Exception("Test exception message");
        ResponseEntity<ErrorResponse> responseEntity = omniExceptionHandler.handleException(exception);
        ErrorResponse expectedErrorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "1234567890",
                genericOmniProcessId.getOmniProcessId(),
                Collections.singletonList("Test exception message"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(expectedErrorResponse, responseEntity.getBody());
    }

    @Test
    void testHandlePhoneNumberException() {
        PhoneNumberException phoneNumberException = new PhoneNumberException("Test phone number exception");
        ResponseEntity<ErrorResponse> responseEntity = omniExceptionHandler.handleException(phoneNumberException);
        ErrorResponse expectedErrorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "1234567890",
                genericOmniProcessId.getOmniProcessId(),
                Collections.singletonList("Test phone number exception"));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrorResponse, responseEntity.getBody());
    }
}
