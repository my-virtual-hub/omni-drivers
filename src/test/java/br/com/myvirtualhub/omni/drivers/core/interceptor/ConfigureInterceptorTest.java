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

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConfigureInterceptorTest {

    @Test
    void testAddInterceptors() {
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        OmniContextInterceptor omniContextInterceptor = mock(OmniContextInterceptor.class);
        ConfigureInterceptor configureInterceptor = new ConfigureInterceptor(omniContextInterceptor);
        configureInterceptor.addInterceptors(registry);
        verify(registry).addInterceptor(omniContextInterceptor);
    }
}
