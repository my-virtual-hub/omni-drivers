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

import br.com.myvirtualhub.omni.commons.context.OmniContextHolder;
import br.com.myvirtualhub.omni.commons.exceptions.PhoneNumberException;
import br.com.myvirtualhub.omni.drivers.core.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * The OmniExceptionHandler class handles exceptions that occur during the execution of the application.
 * It provides methods to handle generic exceptions and specific exceptions related to phone numbers.
 */
@ControllerAdvice
public class OmniExceptionHandler {

    /**
     * Handles exceptions that occur during the execution of the application.
     * Returns a ResponseEntity containing an ErrorResponse object.
     *
     * @param ex The exception to be handled.
     * @return A ResponseEntity containing an ErrorResponse object.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        OmniContextHolder.getContext().getClientMessageId(),
                        OmniContextHolder.getContext().getOmniProcessId().getOmniProcessId(),
                        List.of(ex.getMessage())));
    }

    /**
     * Handles PhoneNumberException and returns a ResponseEntity containing an ErrorResponse object.
     *
     * @param ex The PhoneNumberException to be handled.
     * @return A ResponseEntity containing an ErrorResponse object.
     */
    @ExceptionHandler(PhoneNumberException.class)
    public ResponseEntity<ErrorResponse> handleException(PhoneNumberException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.name(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        OmniContextHolder.getContext().getClientMessageId(),
                        OmniContextHolder.getContext().getOmniProcessId().getOmniProcessId(),
                        List.of(ex.getMessage())));
    }
}
