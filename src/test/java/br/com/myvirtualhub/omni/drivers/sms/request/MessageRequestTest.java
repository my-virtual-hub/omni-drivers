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

package br.com.myvirtualhub.omni.drivers.sms.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageRequestTest {

    @Test
    void testConstructorAndGetters() {
        // Create MessageRequest
        MessageRequest messageRequest = new MessageRequest("Hello, World!", "UTF-8");

        // Test constructor and getters
        assertEquals("Hello, World!", messageRequest.content());
        assertEquals("UTF-8", messageRequest.encodingType());
    }

    @Test
    void testEqualsAndHashCode() {
        MessageRequest messageRequest1 = new MessageRequest("Hello, World!", "UTF-8");
        MessageRequest messageRequest2 = new MessageRequest("Hello, World!", "UTF-8");

        // Test equals and hashCode
        assertEquals(messageRequest1, messageRequest2);
        assertEquals(messageRequest1.hashCode(), messageRequest2.hashCode());
    }

    @Test
    void testToString() {
        MessageRequest messageRequest = new MessageRequest("Hello, World!", "UTF-8");

        // Test toString
        assertEquals("MessageRequest[content=Hello, World!, encodingType=UTF-8]", messageRequest.toString());
    }
}
