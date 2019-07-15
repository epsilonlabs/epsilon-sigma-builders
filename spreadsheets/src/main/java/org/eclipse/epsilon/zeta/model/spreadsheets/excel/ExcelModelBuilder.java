/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.zeta.model.spreadsheets.excel;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetReference;
import org.eclipse.epsilon.emc.spreadsheets.SpreadsheetWorksheet;
import org.eclipse.epsilon.emc.spreadsheets.excel.ExcelModel;
import org.eclipse.epsilon.zeta.model.spreadsheets.SpreadsheetModelBuilder;


/**
 * Implementation of {@link IExcelModelBuilder}
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 * @since 1.6
 *
 */
public class ExcelModelBuilder extends SpreadsheetModelBuilder<ExcelModel, ExcelModelBuilder>
		implements IExcelModelBuilder<ExcelModelBuilder> {

	protected Workbook workbook;
	private File spreadsheetFile;
	private File configurationFile;
	private String password;

	public ExcelModelBuilder() {
		super();
	}
	
	@Override
	public ExcelModel build() throws Exception {
		ExcelModel model = new ExcelModel();
		// From Model
		model.setName(this.name);
		model.setReadOnLoad(this.readOnLoad);
		model.setStoredOnDisposal(this.storeOnDisposal);
		// From Spreadsheet Model
		for (SpreadsheetReference reference : this.references) {
			model.addReference(reference);
		}
		for (SpreadsheetWorksheet worksheet : this.worksheets) {
			model.addWorksheet(worksheet);
		}
		// From Excel Spreadsheet Model
		model.setConfigurationFile(this.configurationFile.getAbsolutePath());
		model.setPassword(this.password);
		model.setSpreadsheetFile(this.spreadsheetFile.getAbsolutePath());
		
		return model;
	}

	@Override
	public ExcelModelBuilder setWorkbook(Workbook workbook) {
		this.workbook = workbook;
		return self();
	}

	@Override
	public ExcelModelBuilder setSpreadsheetFile(File spreadsheetFile) {
		this.spreadsheetFile = spreadsheetFile;
		return self();
	}

	@Override
	public ExcelModelBuilder setConfgurationFile(File configurationFile) {
		this.configurationFile = configurationFile;
		return self();
	}

	@Override
	public ExcelModelBuilder setPassword(String password) {
		this.password = password;
		return self();
	}

	@Override
	public ExcelModelBuilder self() {
		return this;
	}
}
