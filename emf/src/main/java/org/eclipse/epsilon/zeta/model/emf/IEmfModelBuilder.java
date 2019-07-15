/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.zeta.model.emf;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.zeta.model.ICachedModelBuilder;

import java.nio.file.Path;

/**
 * A ModelBuilder for EMF Models
 * @param <T> The type of model builder
 * @param <M> The type of model being built
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 * @since 1.6
 */
public interface IEmfModelBuilder<M extends EmfModel, T extends IEmfModelBuilder<M, T>> extends ICachedModelBuilder<M, T> {

    /**
     * Resolve proxies when loading the EMF model
     * @param expand 				Set to True to resolve proxies
     * @return the builder
     */
    T withExpand(boolean expand);

    /**
     * If set to <code>true</code> (the default), the model tries to reuse previously registered
     * file-based EPackages that have not been modified since the last time they were registered.
     * @param reuse 				Set to true to enable metamodel reuse
     * @return the builder
     */
    T reuseUnmodifiedFileBasedMetamodels(boolean reuse);

    /**
     * Use the given metamodel URI
     * @param metamodelUri 			the EMF URI to the metamodel
     * @return the builder
     */
    T withMetamodelUri(String metamodelUri);

    /**
     * Use the given metamodel URIs
     * @param metamodelUris 		the EMF URIs to the metamodels
     * @return the builder
     */
    T withMetamodelUris(String... metamodelUris);

    /**
     * Use the given metamodel Path  (file based)
     * @param metamodelFile 		the Path of the metamodel file
     * @return the builder
     */
    T withMetamodelPath(Path metamodelFile);

    /**
     * USe the given metamodel Paths (file based)
     * @param metamodelFiles 		the Paths of the metamodel files
     * @return the builder
     */
    T withMetamodelPaths(Path... metamodelFiles);
}
