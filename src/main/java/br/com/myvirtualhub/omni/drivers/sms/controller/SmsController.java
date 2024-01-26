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

import br.com.myvirtualhub.omni.commons.context.OmniContextHolder;
import br.com.myvirtualhub.omni.commons.core.OmniPhoneNumber;
import br.com.myvirtualhub.omni.commons.exceptions.PhoneNumberException;
import br.com.myvirtualhub.omni.drivers.sms.request.SmsRequest;
import br.com.myvirtualhub.omni.drivers.sms.response.SmsResponse;
import br.com.myvirtualhub.omni.ports.inbound.core.exceptions.ProviderFactoryException;
import br.com.myvirtualhub.omni.ports.inbound.core.provider.InboundActionProviderFactory;
import br.com.myvirtualhub.omni.ports.inbound.sms.dto.SmsMessageDTO;
import br.com.myvirtualhub.omni.ports.inbound.sms.dto.SmsPayloadDTO;
import br.com.myvirtualhub.omni.ports.inbound.sms.dto.SmsRecipientDTO;
import br.com.myvirtualhub.omni.ports.inbound.sms.dto.SmsResultDTO;
import br.com.myvirtualhub.omni.ports.inbound.sms.interfaces.SmsInboundAction;
import br.com.myvirtualhub.omni.ports.inbound.sms.interfaces.SmsInboundActionFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The SmsController class is responsible for handling SMS-related operations.
 * It provides an endpoint for sending SMS messages.
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SmsController.class);

    /**
     * Sends an SMS message using the provided {@link SmsRequest} object.
     *
     * @param smsRequest the {@link SmsRequest} object containing the sender, recipient, and message details
     * @return a {@link ResponseEntity} containing the {@link SmsResponse} object
     * @throws ProviderFactoryException if there is an error with the provider factory
     * @throws PhoneNumberException if there is an error with the phone number
     */
    @PostMapping("/sendSms")
    public ResponseEntity<SmsResponse> sendSms(@RequestBody SmsRequest smsRequest) throws ProviderFactoryException, PhoneNumberException {
        LOGGER.info("SmsController: OmniProcessId: {}", OmniContextHolder.getContext().getOmniProcessId().getOmniProcessId());
        InboundActionProviderFactory<SmsInboundActionFactory<SmsInboundAction>, SmsInboundAction> providerFactory = InboundActionProviderFactory.getINSTANCE();
        SmsInboundAction smsInboundAction = providerFactory.getFactory(SmsInboundActionFactory.class.getSimpleName()).createAction();
        return ResponseEntity.ok( buildSmsResponse(smsInboundAction.sendSms(buildSmsPayload(smsRequest))));
    }

    private SmsPayloadDTO buildSmsPayload(SmsRequest smsRequest) throws PhoneNumberException {
        return SmsPayloadDTO.builder()
                .withFromOmniPhoneNumber(new OmniPhoneNumber(smsRequest.from()))
                .withClientMessageId(OmniContextHolder.getContext().getClientMessageId())
                .withMessage(SmsMessageDTO.builder()
                        .withContent(smsRequest.messageRequest().content())
                        .withEncodingType(smsRequest.messageRequest().encodingType())
                        .build())
                .withRecipient(SmsRecipientDTO.builder()
                        .withPhoneNumber(new OmniPhoneNumber(smsRequest.recipientRequest().phoneNumber()))
                        .build())
                .build();
    }

    private SmsResponse buildSmsResponse(SmsResultDTO smsResultDTO) {
        return new SmsResponse(smsResultDTO.getStatus().name(), OmniContextHolder.getContext().getOmniProcessId().getOmniProcessId());
    }
}
