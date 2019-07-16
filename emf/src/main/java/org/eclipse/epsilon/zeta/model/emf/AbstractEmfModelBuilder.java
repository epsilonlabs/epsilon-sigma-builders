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
import org.eclipse.epsilon.zeta.model.CachedModelBuilder;
import org.eclipse.epsilon.zeta.model.ICachedModelBuilder;

/**
 * Base class for EMF model builder. By default EMF models are expanded.
 * @param <T> The type of model builder
 * @param <M> The type of model being built
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 */
public abstract class AbstractEmfModelBuilder<M extends EmfModel, T extends AbstractEmfModelBuilder<M, T>>
        extends CachedModelBuilder<M, T> implements ICachedModelBuilder<M, T> {

    protected boolean expand = true;

    public T withExpand(boolean expand) {
        this.expand = expand;
        return self();
    }
}
