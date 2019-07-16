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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.epsilon.emc.emf.EmfModel;

/**
 * Implementation of the Emf Model Builder. By default metamodels are reused (@see {@link #reuseMetamodels}).
 *
 * @author Horacio Hoyos Rodriguez
 * @author Beatriz Sanchez Piña
 */
public class EmfModelBuilder extends AbstractEmfModelBuilder<EmfModel, EmfModelBuilder>
            implements IEmfModelBuilder<EmfModel, EmfModelBuilder> {

    protected List<String> meteamodelUris;
    protected List<String> fileBasedMetamodelUris;
    protected boolean reuseMetamodels = true;

    public EmfModelBuilder() {
        meteamodelUris = new ArrayList<>();
        fileBasedMetamodelUris = new ArrayList<>();
    }

    @Override
    public String getName() {
        return "Emf";
    }

    @Override
    public EmfModelBuilder self() {
        return this;
    }

    @Override
    public EmfModelBuilder withName(String name) {
        this.name = name;
        return self();
    }

    @Override
    public EmfModelBuilder withModelPath(Path modelpath) {
        this.modelUri = modelpath.toString();
        return self();
    }

    @Override
    public EmfModelBuilder reuseUnmodifiedFileBasedMetamodels(boolean reuse) {
        this.reuseMetamodels = reuse;
        return self();
    }

    @Override
    public EmfModelBuilder withMetamodelUri(String metamodelUri) {
        this.meteamodelUris.add(metamodelUri);
        return self();
    }

    @Override
    public EmfModelBuilder withMetamodelUris(String... metamodelUris) {
        this.meteamodelUris.addAll(Arrays.asList(metamodelUris));
        return self();
    }

    @Override
    public EmfModelBuilder withMetamodelPath(Path metamodelFile) {
        this.fileBasedMetamodelUris.add(metamodelFile.toString());
        return self();
    }

    @Override
    public EmfModelBuilder withMetamodelPaths(Path... metamodelFiles) {
        Arrays.stream(metamodelFiles).map(Path::toString).forEach(fileBasedMetamodelUris::add);
        return self();
    }

    // Add validation
    @Override
    public EmfModel build() {
        // Xcore needs to be setup for standalone
        // Epsilon EMF needs to play nicer for this to work.
//        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
//        Injector injector = new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
//        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
//        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
//        //load genmodel from class org.eclipse.emf.ecore
//        URL ecoreGenmodelResource = getClass().getResource("Ecore.genmodel");
//        URI uri = URI.createFileURI(ecoreGenmodelResource.getPath());
//        GenModel genmodel = (GenModel)resourceSet.getResource(uri, true).getContents().get(0);
//        EPackage.Registry.INSTANCE.put("platform:/resource/org.eclipse.emf.ecore/model/Ecore.genmodel", genmodel);

        EmfModel model = new EmfModel();
        model.setName(name);
        model.setReadOnLoad(this.readOnLoad);
        model.setStoredOnDisposal(this.storeOnDisposal);
        model.setCachingEnabled(this.useCache);
        model.setExpand(this.expand);
        model.setModelFile(this.modelUri);
        model.setMetamodelUris(this.meteamodelUris);
        model.setMetamodelFiles(this.fileBasedMetamodelUris);
        model.setReuseUnmodifiedFileBasedMetamodels(this.reuseMetamodels);
        return model;
    }

}
