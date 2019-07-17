/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.labs.sigma.lambda.model.spreadsheets.excel;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.epsilon.emc.spreadsheets.excel.ExcelModel;
import org.eclipse.epsilon.labs.sigma.lambda.model.spreadsheets.ISpreadsheetModelBuilder;


/**
 * The IExcelModelBuilder interface provides additional configuration methods used by excel
 * models
 * 
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 *
 * @param <T>
 */
public interface IExcelModelBuilder<T extends IExcelModelBuilder<T>> extends ISpreadsheetModelBuilder<ExcelModel, T> {

	T setWorkbook(Workbook workbook);
	
	T setSpreadsheetFile(File spreadsheetFile);
	
	T setConfgurationFile(File configurationFile);
	
	T setPassword(String password);
	
}
