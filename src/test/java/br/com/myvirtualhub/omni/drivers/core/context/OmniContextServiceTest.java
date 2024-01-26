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

package br.com.myvirtualhub.omni.drivers.core.context;

import br.com.myvirtualhub.omni.commons.context.OmniContext;
import br.com.myvirtualhub.omni.commons.core.GenericOmniProcessId;
import br.com.myvirtualhub.omni.commons.sms.SmsOmniProcessId;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OmniContextServiceTest {

    private OmniContextService omniContextService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() throws Exception {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            omniContextService = new OmniContextService();
        }
    }

    @Test
    void testCreateOmniContext_ForSMSRequest() {
        when(request.getRequestURI()).thenReturn("/sms/send");
        OmniContext omniContext = omniContextService.createOmniContext(request);
        assertEquals(SmsOmniProcessId.class, omniContext.getOmniProcessId().getClass());
    }

    @Test
    void testCreateOmniContext_ForNonSMSRequest() {
        when(request.getRequestURI()).thenReturn("/other/request");
        OmniContext omniContext = omniContextService.createOmniContext(request);
        assertEquals(GenericOmniProcessId.class, omniContext.getOmniProcessId().getClass());
    }
}
