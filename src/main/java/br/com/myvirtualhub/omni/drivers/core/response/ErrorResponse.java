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

import java.util.List;

/**
 * The ErrorResponse class represents an error response object.
 *
 * @param code The error code.
 * @param message The error message.
 * @param clientMessageId The client's message ID.
 * @param omniProcessId The processing ID of omni.
 * @param details Additional error details.
 */
public record ErrorResponse(String code, String message, String clientMessageId, String omniProcessId, List<String> details) { }