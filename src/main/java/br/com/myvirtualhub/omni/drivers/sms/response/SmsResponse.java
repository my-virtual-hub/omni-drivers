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

/**
 * The SmsResponse class represents the response received after sending an SMS message.
 * It contains the status of the message and the Omni Process ID associated with the message.
 *
 * @param status the status of the SMS message
 * @param omniProcessId the Omni Process ID associated with the message
 */
public record SmsResponse(String status, String omniProcessId) { }