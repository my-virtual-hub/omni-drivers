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

class RecipientRequestTest {

    @Test
    void testConstructorAndGetters() {
        String phoneNumber = "+123456789";
        RecipientRequest recipientRequest = new RecipientRequest(phoneNumber);
        assertEquals(phoneNumber, recipientRequest.phoneNumber());
    }

    @Test
    void testToString() {
        String phoneNumber = "+123456789";
        RecipientRequest recipientRequest = new RecipientRequest(phoneNumber);
        assertEquals("RecipientRequest[phoneNumber=" + phoneNumber + "]", recipientRequest.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        String phoneNumber1 = "+123456789";
        String phoneNumber2 = "+987654321";
        RecipientRequest recipientRequest1 = new RecipientRequest(phoneNumber1);
        RecipientRequest recipientRequest2 = new RecipientRequest(phoneNumber1);
        RecipientRequest recipientRequest3 = new RecipientRequest(phoneNumber2);
        assertEquals(recipientRequest1, recipientRequest2);
        assertNotEquals(recipientRequest1,recipientRequest3);
        assertEquals(recipientRequest1.hashCode(), recipientRequest2.hashCode());
        assertNotEquals(recipientRequest1.hashCode(), recipientRequest3.hashCode());
    }
}
