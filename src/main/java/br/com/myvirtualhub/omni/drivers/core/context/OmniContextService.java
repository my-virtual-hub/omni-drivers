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
import org.springframework.stereotype.Service;

/**
 * The OmniContextService class provides methods to create OmniContext objects based on the given parameters.
 */
@Service
public class OmniContextService {

    /**
     * Creates an OmniContext object based on the provided parameters.
     *
     * @param request the HttpServletRequest object used to extract the request URI
     * @return an OmniContext object with the specified type of OmniProcessId
     */
    public OmniContext createOmniContext(HttpServletRequest request) {
        final OmniContext omniContext = new OmniContext();
        if (request.getRequestURI().contains("/sms/")) {
            omniContext.setOmniProcessId(new SmsOmniProcessId());
        } else {
            omniContext.setOmniProcessId(new GenericOmniProcessId());

        }
        return omniContext;
    }
}