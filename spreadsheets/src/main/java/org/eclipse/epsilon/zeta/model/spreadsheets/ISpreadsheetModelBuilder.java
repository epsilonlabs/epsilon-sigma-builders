/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.zeta.model.spreadsheets;

import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetReference;
import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetWorksheet;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.zeta.model.IModelBuilder;

/**
 * The ISpreadsheetModelBuilder interface provides additional configuration methods used by
 * spreadsheet models
 *
 * @param <M>  The type of model returned by the builder
 * @param <T>  The specialized model builder
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 *

 */
public interface ISpreadsheetModelBuilder<M extends IModel, T extends ISpreadsheetModelBuilder<M, T>>
		extends IModelBuilder<M, T> {

	T addWorksheet(final SpreadsheetWorksheet worksheet);

	T addReference(final SpreadsheetReference reference);

}
