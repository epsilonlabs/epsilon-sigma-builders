/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.labs.sigma.lambda.model.spreadsheets;

import java.util.List;

import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetModel;
import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetReference;
import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetWorksheet;
import org.eclipse.epsilon.labs.sigma.lambda.model.ModelBuilder;

/**
 * Base class for SpreadsheetModelBuilders
 * @param <M>  The type of model returned by the builder
 * @param <T>  The specialized model builder
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 *
 */
public abstract class SpreadsheetModelBuilder<M extends SpreadsheetModel, T extends SpreadsheetModelBuilder<M, T>>
		extends ModelBuilder<M, T> implements ISpreadsheetModelBuilder<M, T> {

	protected List<SpreadsheetWorksheet> worksheets;
	protected List<SpreadsheetReference> references;
	
	@Override
	public T addWorksheet(SpreadsheetWorksheet worksheet) {
		this.worksheets.add(worksheet);
		return self();
	}
	
	@Override
	public T addReference(SpreadsheetReference reference) {
		this.references.add(reference);
		return self();
	}
	
}
