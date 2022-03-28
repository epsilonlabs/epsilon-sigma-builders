/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.labs.sigma.lambda.model;


/**
 * Signals that the model being built could not be built.
 */
public class ModelBuilderException extends Exception {
	
	private static final String MSG_FORMAT = "Error building %s model: %s";
	private static final long serialVersionUID = 7722911050596571409L;

	/**
	 * Constructs a ModelBuilderException with the specified detail message.
	 * A detail message is a String that describes this particular exception.
	 *
	 * @param type the type of model
	 * @param message the message
	 */
	public ModelBuilderException(String type, String message) {
		super(String.format(MSG_FORMAT, type, message));
	}
	
	/**
	 * Constructs a ModelBuilderException with the specified detail message and a cause
	 *
	 * @param type the type of model
	 * @param message the message
	 * @param cause the cause
	 */
	public ModelBuilderException(String type, String message, Throwable cause) {
		super(String.format(MSG_FORMAT, type, message), cause);
	}
	
	/**
	 * Constructs a new exception with the specified cause and a detail message of 
	 * <code>(cause==null ? "Exception" : cause.toString())</code>
	 *
	 * @param type the type of model
	 * @param cause the cause
	 */
	public ModelBuilderException(String type, Throwable cause) {
		super(String.format(MSG_FORMAT, type, cause==null ? "Exception" : cause.toString()), cause);
	}

}
