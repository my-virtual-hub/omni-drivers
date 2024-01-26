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

import br.com.myvirtualhub.omni.commons.context.OmniContext;
import br.com.myvirtualhub.omni.commons.context.OmniContextHolder;
import br.com.myvirtualhub.omni.drivers.core.context.OmniContextService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OmniContextInterceptorTest {

    @Mock
    private OmniContextService omniContextService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Object handler;

    @InjectMocks
    private OmniContextInterceptor interceptorUnderTest;

    @BeforeEach
    void setUp() {
        try (var ignored = MockitoAnnotations.openMocks(this)) {
            System.out.println("setUp() ok!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPreHandle() {
        OmniContext omniContext = new OmniContext();
        when(omniContextService.createOmniContext(any(HttpServletRequest.class))).thenReturn(omniContext);
        boolean result = interceptorUnderTest.preHandle(request, response, handler);
        verify(omniContextService).createOmniContext(request);
        assert result;
    }

    @Test
    void testAfterCompletion() {
        interceptorUnderTest.afterCompletion(request, response, handler, null);
        Assertions.assertNull(OmniContextHolder.getContext());
    }
}
