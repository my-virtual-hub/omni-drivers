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

package br.com.myvirtualhub.omni.drivers.local;

import br.com.myvirtualhub.omni.commons.exceptions.ScannerException;
import br.com.myvirtualhub.omni.commons.scanner.Scanner;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * The LocalOperationExecutor class is responsible for executing local operations.
 * It scans initializers and performs the necessary tasks.
 */
@Component
@Profile("local")
public class LocalOperationExecutor {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LocalOperationExecutor.class);

    /**
     * Executes a local operation by scanning initializers.
     *
     * @throws ScannerException If an error occurs during scanning
     */
    @Bean
    public void executeLocalOperation() throws ScannerException {
        LOGGER.info("Scanning initializers...");
        Scanner.scanningInitializer();
    }
}