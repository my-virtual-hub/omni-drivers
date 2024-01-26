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

package br.com.myvirtualhub.omni.drivers.sms.controller;

import br.com.myvirtualhub.omni.commons.context.OmniContext;
import br.com.myvirtualhub.omni.commons.context.OmniContextHolder;
import br.com.myvirtualhub.omni.commons.sms.SmsOmniProcessId;
import br.com.myvirtualhub.omni.ports.inbound.core.provider.InboundActionProviderFactory;
import br.com.myvirtualhub.omni.ports.inbound.sms.dto.SmsResultDTO;
import br.com.myvirtualhub.omni.ports.inbound.sms.interfaces.SmsInboundActionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SmsController.class)
@ContextConfiguration(classes = SmsController.class)
class SmsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        final OmniContext omniContext = new OmniContext();
        omniContext.setClientMessageId("clientMessageId");
        omniContext.setOmniProcessId(new SmsOmniProcessId());
        OmniContextHolder.setContext(omniContext);
        InboundActionProviderFactory.getINSTANCE().charge((SmsInboundActionFactory) () -> smsPayload -> SmsResultDTO.queued());
    }

    @AfterEach
    void tearDown() {
        OmniContextHolder.clearContext();
    }

    @Test
    void testSendSms() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(post("/sms/sendSms")
                        .content("{\"from\": \"+1234567890\",\"recipientRequest\": {\"phoneNumber\": \"+5541991910691\"},\"messageRequest\": {\"content\": \"string\",\"encodingType\": \"string\"}}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("{\"status\":\"OMNI_QUEUED\",\"omniProcessId\":\"omni:SMS:");
    }
}
