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

package br.com.myvirtualhub.omni.drivers.core.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testConstructorAndGetters() {
        ErrorResponse errorResponseWithDetails = new ErrorResponse("NOT_FOUND", "Resource not found", "clientMessageId", "omniProcessId", List.of("Detail 1", "Detail 2"));
        assertEquals("NOT_FOUND", errorResponseWithDetails.code());
        assertEquals("Resource not found", errorResponseWithDetails.message());
        assertEquals("clientMessageId", errorResponseWithDetails.clientMessageId());
        assertEquals("omniProcessId", errorResponseWithDetails.omniProcessId());
        assertEquals(List.of("Detail 1", "Detail 2"), errorResponseWithDetails.details());
        ErrorResponse errorResponseWithoutDetails = new ErrorResponse("BAD_REQUEST", "Invalid request", "clientMessageId", "omniProcessId",null);
        assertEquals("BAD_REQUEST", errorResponseWithoutDetails.code());
        assertEquals("Invalid request", errorResponseWithoutDetails.message());
        assertEquals("clientMessageId", errorResponseWithoutDetails.clientMessageId());
        assertEquals("omniProcessId", errorResponseWithoutDetails.omniProcessId());
        assertNull(errorResponseWithoutDetails.details());
    }

    @Test
    void testEqualsAndHashCode() {
        ErrorResponse errorResponse1 = new ErrorResponse("NOT_FOUND", "Resource not found", "clientMessageId", "omniProcessId", List.of("Detail 1", "Detail 2"));
        ErrorResponse errorResponse2 = new ErrorResponse("NOT_FOUND", "Resource not found", "clientMessageId", "omniProcessId", List.of("Detail 1", "Detail 2"));
        assertEquals(errorResponse1, errorResponse2);
        assertEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
    }

    @Test
    void testToString() {
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", "Resource not found", "clientMessageId", "omniProcessId", List.of("Detail 1", "Detail 2"));
        assertEquals("ErrorResponse[code=NOT_FOUND, message=Resource not found, clientMessageId=clientMessageId, omniProcessId=omniProcessId, details=[Detail 1, Detail 2]]", errorResponse.toString());
    }
}
