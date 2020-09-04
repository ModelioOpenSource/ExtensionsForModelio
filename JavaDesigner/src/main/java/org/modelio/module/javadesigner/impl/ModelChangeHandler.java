package org.modelio.module.javadesigner.impl;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.event.IElementDeletedEvent;
import org.modelio.api.modelio.model.event.IElementMovedEvent;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerParameters.AccessorsGenerationMode;
import org.modelio.module.javadesigner.api.JavaDesignerParameters.InterfaceImplementationMode;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.automation.AccessorManager;
import org.modelio.module.javadesigner.automation.InterfaceImplementer;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class ModelChangeHandler implements IModelChangeHandler {
	protected AccessorsGenerationMode ACCESSORSGENERATIONMODE;

	protected AccessorManager accessorManager;

	protected InterfaceImplementer interfaceManager;

	private CreatedElementVisitor createVisitor;

	private UpdateElementVisitor updateVisitor;

	public ModelChangeHandler() {
		this.createVisitor = new CreatedElementVisitor ();
		this.updateVisitor = new UpdateElementVisitor ();
	}

	/**
	 * Update model accessors for Attributes and AssociationEnds.
	 */
	@Override
	public void handleModelChange(final IModelingSession session, final IModelChangeEvent event) {
		try (ITransaction t = session.createTransaction("handle model change")) {
			init(session);

			boolean hasDoneWork = false;

			// Initialize a set for updated elements
			Set<MObject> updatedElements = new HashSet<> ();
			updatedElements.addAll (event.getUpdateEvents ());

			// Move events are handled as update events
			for (IElementMovedEvent moveEvent : event.getMoveEvents ()) {
				updatedElements.add (moveEvent.getMovedElement ());
				updatedElements.add (moveEvent.getOldParent());
			}

			// Deleted events : check tag, and tag parameter suppression to
			// update the parent Attribute - AssocEnd
			for(IElementDeletedEvent deletedEvent : event.getDeleteEvents()) {
				MObject deletedElement = deletedEvent.getDeletedElement ();
				if (deletedElement instanceof TaggedValue) {
					updatedElements.add (deletedEvent.getOldParent ());
				} else if (deletedElement instanceof TagParameter) {
					// The parent might be a local tagged value
					if (deletedEvent.getOldParent () instanceof TaggedValue) {
						TaggedValue oldParent = (TaggedValue) deletedEvent.getOldParent ();
						updatedElements.add (oldParent.getAnnoted ());
					}
				} else if (deletedElement instanceof Parameter) {
					Operation oldParent = (Operation) deletedEvent.getOldParent ();
					updatedElements.add (oldParent);
				} else if (deletedElement instanceof InterfaceRealization) {
					MObject oldParent = deletedEvent.getOldParent ();
					// For deleted InterfaceRealization, update all redefine
					// links on the implementing classifiers
					if (oldParent instanceof Classifier) {
						if (JavaDesignerUtils.isJavaElement (oldParent)) {
							boolean newResult = this.interfaceManager.updateRedefineLinks ((Classifier) oldParent);
							hasDoneWork = hasDoneWork || newResult;
						}
					}
				} else if (deletedElement instanceof Attribute) {
					MObject oldParent = deletedEvent.getOldParent ();
					updatedElements.add (oldParent);
				} else if (deletedElement instanceof AssociationEnd) {
					MObject oldParent = deletedEvent.getOldParent ();
					updatedElements.add (oldParent);
				} else if (deletedElement instanceof Note) {
					MObject oldParent = deletedEvent.getOldParent ();
					updatedElements.add (oldParent);
				}
			}

			// Creation events
			for (MObject createdElement : event.getCreationEvents ()) {
				// Check element status before update
				if ((createdElement != null) && createdElement.getStatus ().isModifiable ()) {
					if (createdElement instanceof TaggedValue) {
						// For a tagged value creation, add the tagged element
						// in the update set
						updatedElements.add (((TaggedValue) createdElement).getAnnoted ());
					} else if (createdElement instanceof TagParameter) {
						// For a tag parameter creation, add the tagged element
						// in the update set
						TaggedValue annoted = ((TagParameter) createdElement).getAnnoted ();
						if (annoted != null) {
							updatedElements.add (annoted.getAnnoted ());
						}
					} else if (createdElement instanceof Operation) {
						updatedElements.add (((Operation) createdElement).getOwner ());
					} else if (createdElement instanceof Parameter) {
						Operation owner = ((Parameter) createdElement).getReturned ();
						if (owner == null) {
							owner = ((Parameter) createdElement).getComposed ();
						}
						if (owner != null) {
							updatedElements.add (owner);
						}
					} else if (createdElement instanceof InterfaceRealization) {
						updatedElements.add (createdElement);
					} else if (createdElement instanceof Note) {
						Note theNote = (Note) createdElement;
						NoteType theNoteType = theNote.getModel ();
						if (theNoteType != null) {
							String noteName = theNoteType.getName ();
							String toMatch = Messages.getString ("NoteCreation.EnterNoteBody");

							if (JavaDesignerNoteTypes.OPERATION_JAVACODE.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultCodeNote"));
									hasDoneWork = true;
								}
							} else if (JavaDesignerNoteTypes.OPERATION_JAVARETURNED.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultReturnCodeNote"));
									hasDoneWork = true;
								}
							} else if (JavaDesignerNoteTypes.CLASS_JAVADOC.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultDocNote"));
									hasDoneWork = true;
								}
							} else if (JavaDesignerNoteTypes.CLASS_JAVABOTTOM.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultCodeNote"));
									hasDoneWork = true;
								}
							} else if (JavaDesignerNoteTypes.CLASS_JAVAHEADER.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultCodeNote"));
									hasDoneWork = true;
								}
							} else if (JavaDesignerNoteTypes.CLASS_JAVAMEMBERS.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultCodeNote"));
									hasDoneWork = true;
								}
							} else if (JavaDesignerNoteTypes.CLASS_JAVATOP.equals (noteName)) {
								String noteContent = theNote.getContent ();
								if (toMatch.equals (noteContent)) {
									theNote.setContent (Messages.getString ("NoteCreation.DefaultCodeNote"));
									hasDoneWork = true;
								}
							}
						}
					} else { // Else, launch the creation visitor on the element
						// Initialize the visitor's field
						this.createVisitor.hasDoneWork = false;
						// Launch the visitor
						createdElement.accept (this.createVisitor);
						// Check if the visitor has created something
						hasDoneWork = hasDoneWork || this.createVisitor.hasDoneWork;
					}
				}
			}

			if (updatedElements.size () > 0) {
				// Update events
				for (MObject updatedElement : updatedElements) {
					// Check element status before update
					if ((updatedElement != null) && updatedElement.getStatus ().isModifiable ()) {
						// Initialize the visitor's field
						this.updateVisitor.hasDoneWork = false;
						// Launch the visitor
						this.updateVisitor.visit(updatedElement);
						// Check if the visitor has created something
						hasDoneWork = hasDoneWork || this.updateVisitor.hasDoneWork;
					}
				}
			}
			t.commit();
		} catch (Exception e) {
			// An unhandled error happened
			JavaDesignerModule.getInstance().getModuleContext().getLogService().error("An error occured during the automatic model update");
			JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
		}
	}

	/**
	 * Handle Attribute update.
	 * @throws ElementNotUniqueException
	 */
	boolean handleUpdate(final Attribute theAttribute) throws CustomException, ElementNotUniqueException, ExtensionNotFoundException {
		Classifier owner = theAttribute.getOwner ();
		if (JavaDesignerUtils.isJavaElement (owner)
				&& !(owner instanceof Component)) {
			if (theAttribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
				return this.accessorManager.deleteAccessors(theAttribute);
			} else {
				this.accessorManager.updateAccessors (theAttribute, isAccessorGenerated (theAttribute));
				return true;
			}
		}
		return true;
	}

	/**
	 * Handle AssociationEnd update. All AssociationEnds in the same Association are updated.
	 * @throws ElementNotUniqueException
	 */
	boolean handleUpdate(final AssociationEnd theAssociationEnd) throws CustomException, ElementNotUniqueException, ExtensionNotFoundException {
		boolean ret = true;

		AssociationEnd otherAssociationEnd = theAssociationEnd.getOpposite();
		Classifier otherEnd = otherAssociationEnd.getSource ();
		if (otherEnd instanceof GeneralClass) {
			Classifier owner = otherAssociationEnd.getSource ();
			if (JavaDesignerUtils.isJavaElement (owner)
					&& !(owner instanceof Component)) {
				if (otherAssociationEnd.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
					ret = ret && this.accessorManager.deleteAccessors (otherAssociationEnd);
				} else {
					ret = true;
					this.accessorManager.updateAccessors (otherAssociationEnd, isAccessorGenerated (otherAssociationEnd));
				}
			}
		}
		return ret;
	}

	/**
	 * Accessors are generated in three cases: The generation mode parameter is set to Always. The feature is in an interface. The generation mode
	 * parameter is set to Smart and the feature is not public.
	 * @param theFeature The feature to generate the accessors from.
	 * @return True if accessors must be generated.
	 */
	boolean isAccessorGenerated(final Feature theFeature) {
		if (this.ACCESSORSGENERATIONMODE.equals (AccessorsGenerationMode.Always) || theFeature.getCompositionOwner() instanceof Interface) {
			return true;
		} else if (this.ACCESSORSGENERATIONMODE.equals (AccessorsGenerationMode.Smart) &&
				(!theFeature.getVisibility ().equals (VisibilityMode.PUBLIC) ||
						theFeature.getCompositionOwner() instanceof Interface)) {
			return true;
		}
		return false;
	}

	private void init(final IModelingSession session) {
		IModuleUserConfiguration javaConfig = JavaDesignerModule.getInstance().getModuleContext().getConfiguration ();

		try {
			this.ACCESSORSGENERATIONMODE = AccessorsGenerationMode.valueOf (javaConfig.getParameterValue (JavaDesignerParameters.ACCESSORSGENERATION));
		} catch (IllegalArgumentException e) {
			this.ACCESSORSGENERATIONMODE = AccessorsGenerationMode.Smart;
		}

		// Initialize the accessor manager
		if (this.accessorManager == null) {
			this.accessorManager = new AccessorManager (session);
		}

		this.accessorManager.init (javaConfig);

		// Initialize the interface manager
		if (this.interfaceManager == null) {
			this.interfaceManager = new InterfaceImplementer (session);
		}

		// Set the parameter in the interface manager
		try {
			this.interfaceManager.INTERFACEIMPLEMENTATION = InterfaceImplementationMode.valueOf (javaConfig.getParameterValue (JavaDesignerParameters.INTERFACEIMPLEMENTATION));
		} catch (IllegalArgumentException e) {
			this.interfaceManager.INTERFACEIMPLEMENTATION = InterfaceImplementationMode.Ask;
		}
	}

	/**
	 * Visitor for element creation. - For Attributes and AssociationEnds, generates the accessors - For Classes,
	 * DataTypes, Enumerations & Interfaces, applies the <<JavaClass>> stereotype if necessary - For Packages, applies
	 * the <<JavaPackage>> stereotype if necessary
	 */
	private class CreatedElementVisitor extends DefaultModelVisitor {
		public boolean hasDoneWork;

		private IMetamodelExtensions extensions;

		public CreatedElementVisitor() {
			this.extensions = JavaDesignerModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions();
		}

		/**
		 * Create accessors for this Attribute. Set the visibility to private.
		 */
		@Override
		public Object visitAttribute(final Attribute theAttribute) {
			Classifier owner = theAttribute.getOwner ();
			if (owner == null) {
				return theAttribute.getQualified ().accept (this);
			}
			if (JavaDesignerUtils.isJavaElement (owner) &&
					!(owner instanceof Component)) {
				try {
					this.hasDoneWork = true;
					ModelChangeHandler.this.accessorManager.updateAccessors (theAttribute, isAccessorGenerated (theAttribute));
				} catch (ExtensionNotFoundException e) {
					JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
					this.hasDoneWork = false;
				} catch (CustomException e) {
					JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
					this.hasDoneWork = false;
				}
			}
			return null;
		}

		/**
		 * Create accessors for this AssociationEnd. Set the visibility to private.
		 */
		@Override
		public Object visitAssociationEnd(final AssociationEnd theAssociationEnd) {
			Classifier owner = theAssociationEnd.getSource ();
			if (JavaDesignerUtils.isJavaElement (owner) &&
					!(owner instanceof Component)) {
				try {
					this.hasDoneWork = true;
					ModelChangeHandler.this.accessorManager.updateAccessors (theAssociationEnd, isAccessorGenerated (theAssociationEnd));
				} catch (ExtensionNotFoundException e) {
					JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
					this.hasDoneWork = false;
				} catch (CustomException e) {
					JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
					this.hasDoneWork = false;
				}
			}
			return null;
		}

		/**
		 * If the parent is handled by java, add the <<JavaClass>> stereotype on the Class.
		 */
		@Override
		public Object visitClass(final Class theClass) {
			ModelTree owner = theClass.getOwner ();
			if (JavaDesignerUtils.isJavaElement (owner)) {
				if (!JavaDesignerUtils.isJavaElement (theClass)) {
					theClass.getExtension().add(this.extensions.getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACLASS, theClass.getMClass()));
					this.hasDoneWork = true;
				}
			}
			return null;
		}

		/**
		 * Nothing to do for components, just avoid to call the super
		 */
		@Override
		public Object visitComponent(final Component theComponent) {
			// Return null to avoid calling visitClass
			return null;
		}

		/**
		 * If the parent is handled by java, add the <<JavaClass>> stereotype on the DataType.
		 */
		@Override
		public Object visitDataType(final DataType theDataType) {
			ModelTree owner = theDataType.getOwner ();
			if (JavaDesignerUtils.isJavaElement (owner)) {
				if (!JavaDesignerUtils.isJavaElement (theDataType)) {
					theDataType.getExtension().add(this.extensions.getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADATATYPE, theDataType.getMClass()));
					this.hasDoneWork = true;
				}
			}
			return null;
		}

		/**
		 * If the parent is handled by java, add the <<JavaClass>> stereotype on the Enumeration.
		 */
		@Override
		public Object visitEnumeration(final Enumeration theEnumeration) {
			ModelTree owner = theEnumeration.getOwner ();
			if (JavaDesignerUtils.isJavaElement (owner)) {
				if (!JavaDesignerUtils.isJavaElement (theEnumeration)) {
					theEnumeration.getExtension().add(this.extensions.getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAENUMERATION, theEnumeration.getMClass()));
					this.hasDoneWork = true;
				}
			}
			return null;
		}

		/**
		 * If the parent is handled by java, add the <<JavaClass>> stereotype on the Interface.
		 */
		@Override
		public Object visitInterface(final Interface theInterface) {
			ModelTree owner = theInterface.getOwner ();
			if (JavaDesignerUtils.isJavaElement (owner)) {
				if (!JavaDesignerUtils.isJavaElement (theInterface)) {
					theInterface.getExtension().add(this.extensions.getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINTERFACE, theInterface.getMClass()));
					this.hasDoneWork = true;
				}
			}
			return null;
		}

		/**
		 * If the parent is handled by java, add the <<JavaClass>> stereotype on the Package.
		 */
		@Override
		public Object visitPackage(final Package thePackage) {
			ModelTree owner = thePackage.getOwner ();
			if (JavaDesignerUtils.isJavaElement (owner)) {
				if (!JavaDesignerUtils.isJavaElement (thePackage)) {
					thePackage.getExtension().add(this.extensions.getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPACKAGE, thePackage.getMClass()));
					this.hasDoneWork = true;
				}
			}
			return null;
		}

	}

	private class UpdateElementVisitor extends DefaultModelVisitor {
		public boolean hasDoneWork;

		private static final int UPDATE = 0;

		private static final int UPDATE_ALL = 1;

		private static final int KEEP = 2;

		private static final int KEEP_ALL = 3;

		public UpdateElementVisitor() {
			super();
			this.infrastructureVisitor = new DefaultInfrastructureVisitor() {
				@Override
				public Object visitTaggedValue(final TaggedValue theTaggedValue) {
					ModelElement taggedElement = theTaggedValue.getAnnoted ();
					if (taggedElement != null) {
						return visit(taggedElement);
					} else {
						return null;
					}
				}

				@Override
				public Object visitTagParameter(final TagParameter theTagParameter) {
					// Launch the visitor again to handle the tag value
					TaggedValue theTag = theTagParameter.getAnnoted ();
					if (theTag != null) {
						return visit(theTag);
					} else {
						return null;
					}
				}

				@Override
				public Object visitNote(Note theNote) {
					ModelElement annotedElement = theNote.getSubject();
					if (annotedElement != null) {
						return visit(annotedElement);
					} else {
						return null;
					}
				}
			};
		}

		@Override
		public Object visitAttribute(final Attribute theAttribute) {
			Classifier owner = theAttribute.getOwner ();
			if (owner == null) {
				return theAttribute.getQualified ().accept (this);
			}
			try {
				boolean newResult = handleUpdate (theAttribute);
				this.hasDoneWork = this.hasDoneWork || newResult;
			} catch (ExtensionNotFoundException|ElementNotUniqueException e) {
				JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
				this.hasDoneWork = false;
			} catch (CustomException e) {
				JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
				this.hasDoneWork = false;
			}
			// Launch the visitor on the parent, to update Std Methods
			return theAttribute.getCompositionOwner().accept(this);
		}

		@Override
		public Object visitAssociationEnd(final AssociationEnd theAssociationEnd) {
			try {
				boolean newResult = handleUpdate (theAssociationEnd);
				this.hasDoneWork = this.hasDoneWork || newResult;
			} catch (ExtensionNotFoundException|ElementNotUniqueException e) {
				JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
				this.hasDoneWork = false;
			} catch (CustomException e) {
				JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
				this.hasDoneWork = false;
			}
			return theAssociationEnd.getCompositionOwner().accept(this);
		}

		@Override
		public Object visitOperation(final Operation theOperation) {
			// For getter/setter, update the attached attribute/association
			if (theOperation.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER) ||
					theOperation.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
				for (Dependency theDependency : theOperation.getDependsOnDependency()) {
					ModelElement dependentElement = theDependency.getDependsOn();

					// Check stereotypes only for operations
					if (dependentElement instanceof Feature) {
						if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER) ||
								theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
							dependentElement.accept(this);
						}
					}
				}
			}

			// The dependency accept could have deleted the operation
			if (!theOperation.isDeleted()) {
				Classifier theOwner = theOperation.getOwner ();
				if (theOwner != null) {
					return theOwner.accept (this);
				}
			}
			return null;
		}

		@Override
		public Object visitParameter(final Parameter theParameter) {
			Operation owner = theParameter.getReturned ();
			if (owner == null) {
				owner = theParameter.getComposed ();
			}
			if (owner != null) {
				return owner.accept (this);
			}
			return null;
		}

		@Override
		public Object visitInterfaceRealization(final InterfaceRealization theInterfaceRealization) {
			Interface theInterface = theInterfaceRealization.getImplemented ();
			if (theInterface != null) {
				return theInterface.accept (this);
			}
			return null;
		}

		@Override
		public Object visitClass(final Class theClass) {
			if (JavaDesignerUtils.isJavaElement (theClass)) {
				boolean newResult = ModelChangeHandler.this.accessorManager.deleteAccessors (theClass);
				this.hasDoneWork = this.hasDoneWork || newResult;

				return super.visitClass(theClass);
			}
			return null;
		}

		@Override
		public Object visitComponent(final Component theComponent) {
			return null;
		}

		@Override
		public Object visitDataType(final DataType theDataType) {
			if (JavaDesignerUtils.isJavaElement (theDataType)) {
				boolean newResult = ModelChangeHandler.this.accessorManager.deleteAccessors (theDataType);
				this.hasDoneWork = this.hasDoneWork || newResult;

				return super.visitDataType(theDataType);
			}
			return null;
		}

		@Override
		public Object visitInterface(final Interface theInterface) {
			if (JavaDesignerUtils.isJavaElement (theInterface)) {
				boolean newResult = ModelChangeHandler.this.accessorManager.deleteAccessors (theInterface);
				this.hasDoneWork = this.hasDoneWork || newResult;

				if (theInterface.getImplementedLink().size() > 0 && theInterface.getOwnedOperation().size() > 0) {
					switch (ModelChangeHandler.this.interfaceManager.INTERFACEIMPLEMENTATION) {
					case Never: // Automatic implementation deactivated
						return null;
					case Ask: // Ask the user before continuing
						int res = getAutomationBehavior (theInterface);
						switch (res) {
						case UPDATE: // Just continue
							break;
						case UPDATE_ALL: // Continue and set the mode to
							// "Always" for later uses
							ModelChangeHandler.this.interfaceManager.INTERFACEIMPLEMENTATION = InterfaceImplementationMode.Always;
							break;
						case KEEP_ALL: // Stop and set the mode to "Never" for
							// later uses
							ModelChangeHandler.this.interfaceManager.INTERFACEIMPLEMENTATION = InterfaceImplementationMode.Never;
							//$FALL-THROUGH$
						case KEEP: // Just stop, no update
						default:
							return null;
						}
						break;
					case Always: // Just continue
					default:
					}

					newResult = ModelChangeHandler.this.interfaceManager.updateImplementingClassifiers (theInterface);
					this.hasDoneWork = this.hasDoneWork || newResult;
				}

				return super.visitInterface(theInterface);
			}
			return null;
		}

		private int getAutomationBehavior(final Interface theInterface) {
			int result = 0;
			MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), Messages.getString("Gui.AutomaticInterfaceImplementation.Title"), null, Messages.getString("Gui.AutomaticInterfaceImplementation.Message", JavaDesignerUtils.getFullJavaName(JavaDesignerModule.getInstance().getModuleContext().getModelingSession(), theInterface)), MessageDialog.QUESTION, new String[] { //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString ("Gui.UpdateFromInterfaceButton"), //$NON-NLS-1$
					Messages.getString ("Gui.UpdateFromAllInterfacesButton"), //$NON-NLS-1$
					Messages.getString ("Gui.NoUpdateFromInterfaceButton"), //$NON-NLS-1$
					Messages.getString ("Gui.NoUpdateFromAllInterfacesButton") }, 0); //$NON-NLS-1$
			result = dialog.open ();
			return result;
		}

		public Object visit(MObject updatedElement) {
			if (updatedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
				return updatedElement.accept (this);
			} else {
				return updatedElement.accept(this.infrastructureVisitor);
			}
		}

	}

}
