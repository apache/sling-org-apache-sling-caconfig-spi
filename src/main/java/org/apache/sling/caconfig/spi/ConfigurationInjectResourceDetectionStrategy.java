/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.caconfig.spi;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Defines how to look up the current context resource to be used for getting the context-aware configurations
 * for injecting into Sling Models or for Sling Scripting (BindingsValuesProvider).
 */
@ConsumerType
public interface ConfigurationInjectResourceDetectionStrategy {

    /**
     * Detects the resource that is associated with the given request.
     * @param request Sling request
     * @return Detected Resource or {@code null} if this strategy did not detect an applicable resources.
     */
    @Nullable
    Resource detectResource(@NotNull SlingHttpServletRequest request);
}
