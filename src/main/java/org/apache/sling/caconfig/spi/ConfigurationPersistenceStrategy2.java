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

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Defines how configuration data is stored in the configuration resource.
 * This SPI allows application to define their own content structure and node types to be used for configuration data storage.
 */
@ConsumerType
public interface ConfigurationPersistenceStrategy2 {

    /**
     * Allows the strategy to transform the given configuration resource according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param resource Singleton configuration resource
     * @return Transformed configuration resource. If null is returned this strategy does not support the given configuration resource.
     */
    @Nullable
    Resource getResource(@NotNull Resource resource);

    /**
     * Allows the strategy to transform the given configuration resource according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param resource Configuration collection parent resource
     * @return Transformed configuration resource. If null is returned this strategy does not support the given configuration resource.
     */
    @Nullable
    Resource getCollectionParentResource(@NotNull Resource resource);

    /**
     * Allows the strategy to transform the given configuration resource according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param resource Configuration collection item resource
     * @return Transformed configuration resource. If null is returned this strategy does not support the given configuration resource.
     */
    @Nullable
    Resource getCollectionItemResource(@NotNull Resource resource);

    /**
     * Allows the strategy to transform the given configuration resource path according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param resourcePath Configuration resource path (full path)
     * @return Transformed configuration resource path. If null is returned this strategy does not support the given configuration resource path.
     */
    @Nullable
    String getResourcePath(@NotNull String resourcePath);

    /**
     * Allows the strategy to transform the given configuration resource path according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param resourcePath Configuration collection parent resource path (full path)
     * @return Transformed configuration resource path. If null is returned this strategy does not support the given configuration resource path.
     */
    @Nullable
    String getCollectionParentResourcePath(@NotNull String resourcePath);

    /**
     * Allows the strategy to transform the given configuration resource path according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param resourcePath Configuration collection item resource path (full path)
     * @return Transformed configuration resource path. If null is returned this strategy does not support the given configuration resource path.
     */
    @Nullable
    String getCollectionItemResourcePath(@NotNull String resourcePath);

    /**
     * Allows the strategy to transform the given configuration name for nested configurations according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param configName Configuration name
     * @param relatedConfigPath Path of a configuration resource that was resolved in context of this configuration name.
     *     This can be used to detect if the persistence strategy supports the configuration location. If null it should be assumed that it matches.
     * @return Transformed configuration name. If null is returned this strategy does not support the given configuration resource path.
     */
    @Nullable
    String getConfigName(@NotNull String configName, @Nullable String relatedConfigPath);

    /**
     * Allows the strategy to transform the given configuration name for nested configurations according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param configName Configuration name
     * @param relatedConfigPath Path of a configuration resource that was resolved in context of this configuration name.
     *     This can be used to detect if the persistence strategy supports the configuration location. If null it should be assumed that it matches.
     * @return Transformed configuration name. If null is returned this strategy does not support the given configuration resource path.
     */
    @Nullable
    String getCollectionParentConfigName(@NotNull String configName, @Nullable String relatedConfigPath);

    /**
     * Allows the strategy to transform the given configuration name for nested configurations according to it's persistent strategies,
     * e.g. fetching the data from a child resource instead of the given resource.
     * @param configName Configuration name
     * @param relatedConfigPath Path of a configuration resource that was resolved in context of this configuration name.
     *     This can be used to detect if the persistence strategy supports the configuration location. If null it should be assumed that it matches.
     * @return Transformed configuration name. If null is returned this strategy does not support the given configuration resource path.
     */
    @Nullable
    String getCollectionItemConfigName(@NotNull String configName, @Nullable String relatedConfigPath);

    /**
     * Stores configuration data for a singleton configuration resource.
     * The changes are written using the given resource resolver. They are not committed, this is left to the caller.
     * @param resourceResolver Resource resolver
     * @param configResourcePath Path to store configuration data to. The resource (and it's parents) may not exist and may have to be created.
     * @param data Configuration data to be stored. All existing properties are erased and replaced with the new ones.
     * @return true if the data was persisted. false if persisting the data was not accepted by this persistence strategy
     *      (in case of error throw an exception).
     */
    boolean persistConfiguration(
            @NotNull ResourceResolver resourceResolver,
            @NotNull String configResourcePath,
            @NotNull ConfigurationPersistData data);

    /**
     * Stores configuration data for a configuration resource collection.
     * The changes are written using the given resource resolver. They are not committed, this is left to the caller.
     * @param resourceResolver Resource resolver
     * @param configResourceCollectionParentPath Parent path to store configuration collection data to.
     *      The resource (and it's parents) may not exist and may have to be created.
     * @param data Configuration collection data. All existing collection entries on this context path level are erased and replaced with the new ones.
     * @return true if the data was persisted. false if persisting the data was not accepted by this persistence strategy
     *      (in case of error throw an exception).
     */
    boolean persistConfigurationCollection(
            @NotNull ResourceResolver resourceResolver,
            @NotNull String configResourceCollectionParentPath,
            @NotNull ConfigurationCollectionPersistData data);

    /**
     * Delete configuration or configuration collection data from repository using the inner-most context path as reference.
     * @param resourceResolver Resource resolver
     * @param configResourcePath Path to store configuration data to. The resource (and it's parents) may not exist and may have to be created.
     * @return true if the data was delete. false if deleting the data was not accepted by this persistence strategy
     *      (in case of error throw an exception).
     */
    boolean deleteConfiguration(@NotNull ResourceResolver resourceResolver, @NotNull String configResourcePath);
}
