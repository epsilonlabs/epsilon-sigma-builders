/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.zeta.model.simulink;

import java.io.File;

import org.eclipse.epsilon.emc.simulink.model.SimulinkModel;
import org.eclipse.epsilon.zeta.model.ICachedModelBuilder;

/**
 * The ISimulinkModelBuilder interface provides additional configuration methods used by simulink
 * models
 * @param <T>	the specific type of Simulink model builder
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 *

 */
public interface ISimulinkModelBuilder<T extends ISimulinkModelBuilder<T>> extends ICachedModelBuilder<SimulinkModel, T> {

	T engineJarPath(String engineJarPath);

	T showMatlabEditor(boolean hiddenEditor);

	T file(File file);

	T libraryPath(String libraryPath);

}
