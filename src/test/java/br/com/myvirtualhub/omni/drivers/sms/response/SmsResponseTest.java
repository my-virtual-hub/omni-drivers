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

package br.com.myvirtualhub.omni.drivers.sms.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SmsResponseTest {

    @Test
    void testConstructorAndGetters() {
        SmsResponse smsResponse = new SmsResponse("SUCCESS", "123456");

        // Test constructor and getters
        assertEquals("SUCCESS", smsResponse.status());
        assertEquals("123456", smsResponse.omniProcessId());
    }

    @Test
    void testEqualsAndHashCode() {
        SmsResponse smsResponse1 = new SmsResponse("SUCCESS", "123456");
        SmsResponse smsResponse2 = new SmsResponse("SUCCESS", "123456");

        // Test equals and hashCode
        assertEquals(smsResponse1, smsResponse2);
        assertEquals(smsResponse1.hashCode(), smsResponse2.hashCode());
    }

    @Test
    void testToString() {
        SmsResponse smsResponse = new SmsResponse("SUCCESS", "123456");

        // Test toString
        assertEquals("SmsResponse[status=SUCCESS, omniProcessId=123456]", smsResponse.toString());
    }
}
