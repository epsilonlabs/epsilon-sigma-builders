/*********************************************************************
* Copyright (c) 2019 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.zeta.model.emf.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;


/**
 * Set of utility methods for working with EMF models.
 *
 * @author Horacio Hoyos Rodriguez
 */
public class EmfModelAssistant {

	private static XMLParserPool parserPool = new XMLParserPoolImpl();
	
	private static Map<Object, Object> emfLoadOptions;

    static {
    	HashMap<Object, Object> aMap = new HashMap<>();
    	aMap.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
    	aMap.put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
        emfLoadOptions = Collections.unmodifiableMap(aMap);
    }

	/**
	 * Register the XMI resource factory locally into a specific ResourceSet. The ResourceSet
	 * should be created via <code>ResourceSet rs = new ResourceSetImpl();</code>
	 *
	 * @param rs the rs
	 */
	public static void registerXMIFactoryLocally(ResourceSet rs) {
		registerResourceFactoryLocally("xmi", new XMIResourceFactoryImpl(), rs);
	}
	
	/**
	 * Register the XMI resource factory globally.
	 */
	public static void registerXMIFactoryGlobally() {
		registerResourceFactoryGlobally("xmi", new XMIResourceFactoryImpl());
	}
	
	/**
	 * Register the Ecore resource factory locally into a specific ResourceSet. The ResourceSet
	 * should be created via <code>ResourceSet rs = new ResourceSetImpl();</code>
	 *
	 * @param rs the rs
	 */
	public static void registerEcoreFactoryLocally(ResourceSet rs) {
		registerResourceFactoryLocally("ecore", new EcoreResourceFactoryImpl(), rs);
	}
	
	/**
	 * Register the Ecore resource factory globally.
	 */
	public static void registerEcoreFactoryGlobally() {
		registerResourceFactoryGlobally("ecore", new EcoreResourceFactoryImpl());
	}
	
	/**
	 * Register the ".model" extension as XMI resources locally into a specific ResourceSet. The
	 * ResourceSet should be created via <code>ResourceSet rs = new ResourceSetImpl();</code>
	 *
	 * @param rs the rs
	 */
	public static void registerModelExtensionLocally(ResourceSet rs) {
		registerResourceFactoryLocally("model", new XMIResourceFactoryImpl(), rs);
	}
	
	/**
	 * Register the ".model" extension as XMI resources globally
	 */
	public static void registerModelExtensionGlobally() {
		registerResourceFactoryGlobally("model", new XMIResourceFactoryImpl());
	}
	
	/**
	 * Register a Factory for a specific extension locally.
	 *
	 * @param extension 			the file extension
	 * @param factory 			the factory to handle files with the given extension
	 * @param rs 				the resource set where the factory is registered
	 */
	public static void registerResourceFactoryLocally(String extension, Factory factory, ResourceSet rs) {
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(extension, factory);
	}
	
	/**
	 * Register a Factory for a specific extension blobally.
	 *
	 * @param extension 			the file extension
	 * @param factory 			the factory to handle files with the given extension
	 */
	public static void registerResourceFactoryGlobally(String extension, Factory factory) {
		Registry registry = Resource.Factory.Registry.INSTANCE;
		registry.getExtensionToFactoryMap().put(extension, factory);
	}
	
	/** The matcher. */
	public static PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.ecore");
	
	
	/**
	 * Register all the packages found in the given Ecore metamodel locally.
	 *
	 * @param location 				the metamodel location, must be a valid file system path
	 * @param rs 					the Resource Set
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerEcoreMetamodelLocally(Path location, ResourceSet rs) throws IOException {
		registerEcoreMetamodelLocally(location, rs, emfLoadOptions);
	}
	
	/**
	 * Register all the packages found in the given Ecore metamodel locally.
	 *
	 * @param location 				the metamodel location, must be a valid file system path
	 * @param rs 					the Resource Set
	 * @param options 				the options
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerEcoreMetamodelLocally(
	    Path location,
	    ResourceSet rs,
	    Map<?,?> options) throws IOException {
		if (!matcher.matches(location)) {
			System.out.println("Attempting to load a metamodel without an *.ecore name, make sure the appropiate facotry has been registered.");
		}
		if (location.toString().contains("!")) {
			throw new IllegalStateException("The provided path is jar resource, you need to copy the"
					+ " contents to a file or use the InputStream alternative method.");
		}
		Resource r = rs.createResource(URI.createFileURI(location.toString()));
		r.load(options);
		for (EObject eo : r.getContents()) {
			if (eo instanceof EPackage) {
				registerEPackage((EPackage) eo, rs.getPackageRegistry());
			}
		}
	}
	
	/**
	 * Register all the packages found in the given Ecore metamodel locally.
	 *
	 * @param location 				the metamodel location, must be a valid file system path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerEcoreMetamodelGlobally(Path location) throws IOException {
		registerEcoreMetamodelGlobally(location, emfLoadOptions);
	}
	
	/**
	 * Register all the packages found in the given Ecore metamodel globally
	 *
	 * @param location 				the metamodel location, must be a valid file system path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerEcoreMetamodelGlobally(Path location, Map<?,?> options) throws IOException {
		if (!matcher.matches(location)) {
			System.out.println("Attempting to load a metamodel without an *.ecore name, make sure the appropiate facotry has been registered.");
		}
		if (location.toString().contains("!")) {
			throw new IllegalStateException("The provided path is jar resource, you need to copy the"
					+ " contents to a file or use the InputStream alternative method.");
		}
		ResourceSet rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createFileURI(location.toString()));
		r.load(options);
		org.eclipse.emf.ecore.EPackage.Registry registry = EPackage.Registry.INSTANCE;
		for (EObject eo : r.getContents()) {
			if (eo instanceof EPackage) {
				registerEPackage((EPackage) eo, registry);
			}
		}
	}
	
	
	/**
	 * Register all the packages found in the given Ecore metamodel locally. The metamodel is read
	 * from an input stream. This method is useful when loading metamodel from Class resources.
	 *
	 * @param name 					the name of the metamodel
	 * @param stream 				the input stream from with the metamodel can be loaded
	 * @param rs 					the Resource Set
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerEcoreMetamodelLocally(
		String name,
		InputStream stream,
		ResourceSet rs) throws IOException {
		registerEcoreMetamodelLocally(name, stream, rs, emfLoadOptions);
	}
	
	/**
	 * Register all the packages found in the given Ecore metamodel locally. The metamodel is read
	 * from an input stream. This method is useful when loading metamodel from Class resources.
	 *
	 * @param name 					the name of the metamodel
	 * @param stream 				the input stream from with the metamodel can be loaded
	 * @param rs 					the Resource Set
	 * @param options 				the options
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void registerEcoreMetamodelLocally(
		String name,
		final InputStream stream,
	    final ResourceSet rs,
	    final Map<?,?> options) throws IOException {
	    boolean append = false;
	    try {
			String ext = name.substring(name.lastIndexOf("."));
			if (ext.equals(".ecore")) {
				append = true;
			}
	    }
	    catch (IndexOutOfBoundsException ex) {
	    	append = true;
	    }
	    if (append) {
	    	name = name + ".ecore";
	    }
	    Resource r = rs.createResource(URI.createFileURI(name));
		r.load(options);
		for (EObject eo : r.getContents()) {
			if (eo instanceof EPackage) {
				registerEPackage((EPackage) eo, rs.getPackageRegistry());
			}
		}
	}
	
		
	/**
	 * Register the EPackage and its nested packages in the given EPackage.Registry
	 * @param ep					The EPackage
	 * @param registry				The Registry
	 */
	public static void registerEPackage(EPackage ep, org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(ep.getNsURI(), ep);
		for (EPackage nep : ep.getESubpackages()) {
			registerEPackage(nep, registry);
		} 
	}
	
	
	/**
	 * Register the EPackage and its nested packages in the given EPackage.Registry
	 * @param ep					The EPackage
	 */
	public static void registerEPackageGlobally(EPackage ep) {
		org.eclipse.emf.ecore.EPackage.Registry.INSTANCE.put(ep.getNsURI(), ep);
		for (EPackage nep : ep.getESubpackages()) {
			registerEPackageGlobally(nep);
		} 
	}
	
	
}
