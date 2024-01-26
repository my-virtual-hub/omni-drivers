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

package br.com.myvirtualhub.omni.drivers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * The OmniDriversSpringBootApplication class is the main class of the OmniDrivers Spring Boot application.
 * It is responsible for starting the application by calling the SpringApplication.run method with the appropriate arguments.
 * This class is annotated with @SpringBootApplication, which enables the Spring Boot features and configurations.
 * Example usage:
 *     public static void main(String[] args) {
 *         SpringApplication.run(OmniDriversSpringBootApplication.class, args);
 *     }
 * Note: The @SpringBootApplication annotation scans the package and its sub-packages for components, configurations, and
 * services to be used by Spring. It also enables the auto-configuration feature of Spring Boot, reducing the need for
 * explicit configuration.
 */
@SpringBootApplication
@Profile("local")
public class OmniDriversSpringBootApplication {

    /**
     * The main method is the entry point for the OmniDrivers Spring Boot application.
     * It starts the application by calling the SpringApplication.run method with the provided arguments.
     *
     * @param args The command line arguments passed to the main method.
     *             These arguments are used to configure and customize the application.
     *             Typically, these arguments include properties files, program arguments, or system properties.
     */
    public static void main(String[] args) {
        SpringApplication.run(OmniDriversSpringBootApplication.class, args);
    }
}