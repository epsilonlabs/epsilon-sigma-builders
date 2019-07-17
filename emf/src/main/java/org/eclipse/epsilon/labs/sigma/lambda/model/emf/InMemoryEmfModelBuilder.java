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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;

/**
 * An EMF model builder for InMemory EMF models.
 * 
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 */
public class InMemoryEmfModelBuilder extends AbstractEmfModelBuilder<InMemoryEmfModel, InMemoryEmfModelBuilder>
	implements IInMemoryEmfModelBuilder<InMemoryEmfModel, InMemoryEmfModelBuilder> {
	
	protected Resource resource;
	protected List<EPackage> ePackages;
    protected List<String> nsUris;
	protected boolean containerListener = true;
	
	
	
	public InMemoryEmfModelBuilder() {
		ePackages = new ArrayList<>();
		nsUris = new ArrayList<>();
	}

	@Override
	public InMemoryEmfModelBuilder self() {
		return this;
	}

	@Override
	public InMemoryEmfModel build() throws Exception {
		if ((!ePackages.isEmpty()) && (!nsUris.isEmpty())) {
			System.out.println("Both EPackages and nsURIs were providede, only EPackages will be used.");
		}
		if (name == null) {
			name = "Model";
		}
		if (!ePackages.isEmpty()) {
			return new InMemoryEmfModel(name, resource, ePackages.toArray(new EPackage[0]));
		}
		else {
			return new InMemoryEmfModel(name, resource, nsUris.toArray(new String[0]));
		}
	}

	@Override
	public InMemoryEmfModelBuilder withResource(Resource resource) {
		this.resource = resource;
		return this;
	}

	@Override
	public InMemoryEmfModelBuilder withEPackages(EPackage... ePackages) {
		this.ePackages.addAll(Arrays.asList(ePackages));
		return this;
	}

	@Override
	public InMemoryEmfModelBuilder withNsUris(String... nsUris) {
		this.nsUris.addAll(Arrays.asList(nsUris));
		return this;
	}

	@Override
	public InMemoryEmfModelBuilder withContainerListenerEnabled(boolean enabled) {
		this.containerListener  = enabled;
		return this;
	}
	
}
