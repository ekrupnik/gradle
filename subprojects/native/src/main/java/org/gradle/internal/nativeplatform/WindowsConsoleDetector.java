/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.nativeplatform;

import org.fusesource.jansi.WindowsAnsiOutputStream;
import org.gradle.internal.console.ConsoleMetaData;
import org.gradle.internal.console.FallbackConsoleMetaData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WindowsConsoleDetector implements ConsoleDetector {
    public ConsoleMetaData getConsole() {
        // Use Jansi's detection mechanism
        try {
            new WindowsAnsiOutputStream(new ByteArrayOutputStream());
            return new FallbackConsoleMetaData();
        } catch (IOException ignore) {
            // Not attached to a console
            return null;
        }
    }
}