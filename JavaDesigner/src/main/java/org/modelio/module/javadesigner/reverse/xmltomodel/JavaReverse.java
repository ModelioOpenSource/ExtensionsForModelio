package org.modelio.module.javadesigner.reverse.xmltomodel;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.XMLReverse;
import com.modelio.module.xmlreverse.model.IVisitorElement;
import com.modelio.module.xmlreverse.model.JaxbReversedData;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.ReverseStrategyConfiguration;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaAssociationEndStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaAttributeStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaClassStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaDataTypeStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaElementImportStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaEnumerationLiteralStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaEnumerationStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaGroupStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaInterfaceStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaModelStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaNoteStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaOperationStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaPackageStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaParameterStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaReportItemStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaReturnParameterStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaStereotypeStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaTaggedValueStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.strategy.JavaTemplateParameterStrategy;

public class JavaReverse {
    public void reverseModel(final IModelingSession session, final File file, final NameSpace root, final IReportWriter report, final ReverseStrategyConfiguration config) {
        XMLReverse revers = initReverse(session, root, report, config);
        
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Start of reverse core at " + LocalDateTime.now());
        
        revers.reverse (file, root, config.ENCODING);
        
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("End of reverse core at " + LocalDateTime.now());
    }

    protected XMLReverse initReverse(final IModelingSession session, final NameSpace root, final IReportWriter report, final ReverseStrategyConfiguration config) {
        XMLReverse revers = new XMLReverse (session);
        
        JavaNameSpaceFinder finder = new JavaNameSpaceFinder(root);
        
        JavaModelElementDeleteStrategy deleteStrategy = new JavaModelElementDeleteStrategy ();
        if (config.DESCRIPTIONASJAVADOC) {
            deleteStrategy.addJavaNoteType ("description");
        }
        revers.setModelElementDeleteStrategy (deleteStrategy);
        
        revers.addAttributeStrategy (new JavaAttributeStrategy (session));
        revers.addAssociationEndStrategy (new JavaAssociationEndStrategy (session));
        revers.addClassStrategy (new JavaClassStrategy (session, config));
        revers.addDataTypeStrategy (new JavaDataTypeStrategy (session, config));
        revers.addElementImportStrategy (new JavaElementImportStrategy (session, config));
        revers.addEnumerationStrategy (new JavaEnumerationStrategy (session, config));
        revers.addEnumerationLiteralStrategy (new JavaEnumerationLiteralStrategy (session));
        revers.addGroupStrategy (new JavaGroupStrategy (session));
        revers.addInterfaceStrategy (new JavaInterfaceStrategy (session, config));
        revers.addModelStrategy (new JavaModelStrategy (finder, config));
        // revers.addModelElementStrategy (new JavaModelElementStrategy ());
        revers.addNoteStrategy (new JavaNoteStrategy (session, config));
        revers.addOperationStrategy (new JavaOperationStrategy (session, config, deleteStrategy));
        revers.addPackageStrategy (new JavaPackageStrategy (session, finder));
        revers.addParameterStrategy (new JavaParameterStrategy (session, config));
        revers.addReportItemStrategy (new JavaReportItemStrategy ());
        revers.addReturnParameterStrategy (new JavaReturnParameterStrategy (session));
        revers.addStereotypeStrategy(new JavaStereotypeStrategy (session, config, deleteStrategy));
        revers.addTaggedValueStrategy(new JavaTaggedValueStrategy (session, config));
        revers.addTemplateParameterStrategy (new JavaTemplateParameterStrategy (session));
        
        revers.setReportWriter (report);
        revers.setNameSpaceFinder (finder);
        return revers;
    }

    public void reverseModel(final IModelingSession session, final JaxbReversedData model, final List<IVisitorElement> filteredElements, final NameSpace root, final IReportWriter report, final ReverseStrategyConfiguration config) {
        XMLReverse revers = initReverse(session, root, report, config);
        
        JavaModelStrategy strategy = new JavaModelStrategy ((JavaNameSpaceFinder) revers.getNameSpaceFinder(), config);
        strategy.setElementsToKeep(filteredElements);
        revers.addModelStrategy (strategy);
        
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Start of reverse core at " + LocalDateTime.now());
        
        revers.reverse (model, root);
        
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("End of reverse core at " + LocalDateTime.now());
    }

}
