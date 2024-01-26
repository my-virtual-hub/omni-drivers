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

class SmsRequestTest {

    @Test
    void testConstructorAndGetters() {
        String from = "sender";
        RecipientRequest recipientRequest = new RecipientRequest("123456789");
        MessageRequest messageRequest = new MessageRequest("Hello, World!", "UTF-8");
        SmsRequest smsRequest = new SmsRequest(from, recipientRequest, messageRequest);
        assertEquals(from, smsRequest.from());
        assertEquals(recipientRequest, smsRequest.recipientRequest());
        assertEquals(messageRequest, smsRequest.messageRequest());
    }

    @Test
    void testEqualsAndHashCode() {
        SmsRequest smsRequest1 = new SmsRequest("sender", new RecipientRequest("123456789"),
                new MessageRequest("Hello, World!", "UTF-8"));
        SmsRequest smsRequest2 = new SmsRequest("sender", new RecipientRequest("123456789"),
                new MessageRequest("Hello, World!", "UTF-8"));
        assertEquals(smsRequest1, smsRequest2);
        assertEquals(smsRequest1.hashCode(), smsRequest2.hashCode());
    }

    @Test
    void testToString() {
        SmsRequest smsRequest = new SmsRequest("sender", new RecipientRequest("123456789"),
                new MessageRequest("Hello, World!", "UTF-8"));
        assertEquals("SmsRequest[from=sender, recipientRequest=RecipientRequest[phoneNumber=123456789], " +
                        "messageRequest=MessageRequest[content=Hello, World!, encodingType=UTF-8]]",
                smsRequest.toString());
    }

    @Test
    void smsRequestShouldHaveCorrectValues() {
        String from = "sender";
        RecipientRequest recipientRequest = new RecipientRequest("123456789");
        MessageRequest messageRequest = new MessageRequest("Hello, World!", "UTF-8");
        SmsRequest smsRequest = new SmsRequest(from, recipientRequest, messageRequest);
        assertEquals(from, smsRequest.from());
        assertEquals(recipientRequest, smsRequest.recipientRequest());
        assertEquals(messageRequest, smsRequest.messageRequest());
    }
}
