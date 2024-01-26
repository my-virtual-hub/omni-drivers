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

package br.com.myvirtualhub.omni.drivers.core.interceptor;

import br.com.myvirtualhub.omni.commons.context.OmniContextHolder;
import br.com.myvirtualhub.omni.drivers.core.context.OmniContextService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * The OmniContextInterceptor class is an implementation of the HandlerInterceptor interface.
 * It sets the OmniContext in the OmniContextHolder before handling the request and clears it after handling the request.
 */
@Component
public class OmniContextInterceptor implements HandlerInterceptor {

    private final OmniContextService omniContextService;

    /**
     * The OmniContextInterceptor constructor.
     *
     * @param omniContextService the service used to create OmniContext objects
     */
    public OmniContextInterceptor(OmniContextService omniContextService) {
        this.omniContextService = omniContextService;
    }

    /**
     * The preHandle method is an implementation of the preHandle method from the HandlerInterceptor interface.
     * It is used to set the OmniContext in the OmniContextHolder before handling the request.
     *
     * @param request  the HttpServletRequest object representing the request
     * @param response the HttpServletResponse object representing the response
     * @param handler  the Object representing the handler for the request
     * @return true indicating that the request should proceed to the next interceptor or the handler,
     *         or false to abort the request processing
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        OmniContextHolder.setContext(omniContextService.createOmniContext(request));
        OmniContextHolder.getContext().setClientMessageId(request.getHeader("x-omni-client-message-id"));
        return true;
    }

    /**
     * The afterCompletion method is an implementation of the afterCompletion method from the HandlerInterceptor interface.
     * It is used to clear the OmniContext from the OmniContextHolder after handling the request.
     *
     * @param request  the HttpServletRequest object representing the request
     * @param response the HttpServletResponse object representing the response
     * @param handler  the Object representing the handler for the request
     * @param ex       the Exception that occurred during the request handling, or null if no exception occurred
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        OmniContextHolder.clearContext();
    }
}