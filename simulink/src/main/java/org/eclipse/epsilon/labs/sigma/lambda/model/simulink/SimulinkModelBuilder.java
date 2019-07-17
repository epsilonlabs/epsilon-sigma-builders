/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.labs.sigma.lambda.model.simulink;

import java.io.File;

import org.eclipse.epsilon.emc.simulink.model.SimulinkModel;
import org.eclipse.epsilon.labs.sigma.lambda.model.CachedModelBuilder;

/**
 * Implementation of the {@link SimulinkModelBuilder} interface.
 * 
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 *
 */
public class SimulinkModelBuilder extends CachedModelBuilder<SimulinkModel, SimulinkModelBuilder>
        implements ISimulinkModelBuilder<SimulinkModelBuilder> {

    private String engineJarPath;
	private String libraryPath;
	private File file;
	private boolean showEditor = true;

    public SimulinkModelBuilder() {}

    @Override
	public SimulinkModelBuilder engineJarPath(String engineJarPath) {
        this.engineJarPath = engineJarPath;
        return self();
    }

    @Override
    public SimulinkModelBuilder showMatlabEditor(boolean hiddenEditor) {
        this.showEditor = hiddenEditor;
        return self();
    }

    @Override
    public SimulinkModelBuilder libraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
        return self();
    }

    @Override
	public SimulinkModelBuilder file(File file) {
        this.file = file;
        return self();
    }

    @Override
    public SimulinkModel build() throws Exception {
        SimulinkModel model = new SimulinkModel();
        model.setName(name);
        model.setReadOnLoad(this.readOnLoad);
        model.setStoredOnDisposal(this.storeOnDisposal);
        model.setCachingEnabled(this.useCache);
        model.setEngineJarPath(this.engineJarPath);
        model.setLibraryPath(this.libraryPath);
        model.setShowInMatlabEditor(this.showEditor);
        model.setFile(this.file);
        return model;
    }

	@Override
	public SimulinkModelBuilder self() {
		return this;
	}


}
