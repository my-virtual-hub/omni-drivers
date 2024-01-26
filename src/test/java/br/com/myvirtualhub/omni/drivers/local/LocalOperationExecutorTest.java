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

import br.com.myvirtualhub.omni.commons.annotations.Initializer;
import br.com.myvirtualhub.omni.commons.annotations.InitializerMethod;
import br.com.myvirtualhub.omni.commons.exceptions.ScannerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalOperationExecutorTest {

    private LocalOperationExecutor localOperationExecutorUnderTest;
    private static boolean initializerMethodExecuted = false;

    @BeforeEach
    void setUp() {
        localOperationExecutorUnderTest = new LocalOperationExecutor();
        initializerMethodExecuted = false;
    }

    @Test
    void testExecuteLocalOperation() throws ScannerException {
        localOperationExecutorUnderTest.executeLocalOperation();
        assertTrue(initializerMethodExecuted);
    }

    @Initializer
    public static class InitializerClass {
        @InitializerMethod
        public static void initializerMethod() {
            initializerMethodExecuted = true;
        }
    }
}
