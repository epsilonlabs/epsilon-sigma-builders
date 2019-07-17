/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.labs.sigma.lambda.model.emf;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.labs.sigma.lambda.model.ICachedModelBuilder;

/**
 * A ModelBuilder for EMF Models.
 * 
 * @param <T> The type of model builder
 * @param <M> The type of model being built
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 */
public interface IInMemoryEmfModelBuilder<M extends EmfModel, T extends IInMemoryEmfModelBuilder<M, T>> 
		extends ICachedModelBuilder<M, T> {
    
    /**
     * Use the given resource with the model
     *
     * @param resource the resource
     * @return the t
     */
    T withResource(Resource resource);

    /**
     * Register the provided EPackages in the model's registry
     *
     * @param ePackages the e packages
     * @return the t
     */
    T withEPackages(EPackage... ePackages);

    /**
     * Use the provided NS URIs from the global registry in the model's registry. If both NS URIs
     * and EPackages are provided, the EPackages list will be preferred.
     *
     * @param nsUris the NS URIs
     * @return the t
     */
    T withNsUris(String... nsUris);
    
    /**
     * Add a notification adapter to all objects in the model so that they get moved when their
     * containment changes. Default true.
     *
     * @param enabled the enabled
     * @return the t
     */
    T withContainerListenerEnabled(boolean enabled);
    
}
