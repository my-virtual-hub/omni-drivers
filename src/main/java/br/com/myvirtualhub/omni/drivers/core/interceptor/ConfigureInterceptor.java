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

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The ConfigureInterceptor class is a configuration class that adds the OmniContextInterceptor as an interceptor
 * to the Spring MVC configuration.
 */
@Configuration
public class ConfigureInterceptor implements WebMvcConfigurer {

    private final OmniContextInterceptor omniContextInterceptor;

    /**
     * The ConfigureInterceptor class is a configuration class that adds the OmniContextInterceptor as an interceptor
     * to the Spring MVC configuration.
     *
     * @param omniContextInterceptor the OmniContextInterceptor to be added as an interceptor
     */
    public ConfigureInterceptor(OmniContextInterceptor omniContextInterceptor) {
        this.omniContextInterceptor = omniContextInterceptor;
    }

    /**
     * This method adds an interceptor to the InterceptorRegistry. The interceptor added is the OmniContextInterceptor,
     * which sets the OmniContext in the OmniContextHolder before handling the request and clears it after handling the request.
     *
     * @param registry the InterceptorRegistry object to which the interceptor will be added
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(omniContextInterceptor);
    }
}