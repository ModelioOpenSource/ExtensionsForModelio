package org.modelio.module.javadesigner.generator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modelio.module.xmlreverse.IReportWriter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.progress.ProgressBar;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

public class GenerateProgressBar extends ProgressBar implements IRunnableWithProgress {
	private Collection<NameSpace> elementsToGenerate;

	private IReportWriter report;

	public GenerateProgressBar(final IModule module, final Collection<NameSpace> elementsToGenerate, final IReportWriter report) {
		super (module, elementsToGenerate.size ());
		this.elementsToGenerate = elementsToGenerate;
		this.report = report;
	}

	@Override
	public void run(final IProgressMonitor localMonitor) throws InterruptedException, InvocationTargetException {
		init (true);
		monitor = localMonitor;
		monitor.beginTask ("Generating", this.elementsToGenerate.size ());

		List<JavaMoveFileStruct> filesToMove = new ArrayList<> ();
		File tempDir;
		// Create a temporary directory
		try {
			tempDir = File.createTempFile (IJavaDesignerPeerModule.MODULE_NAME, ""); //$NON-NLS-1$ //$NON-NLS-2$
			tempDir.delete ();
			tempDir.mkdir ();
			tempDir.deleteOnExit ();
		} catch (IOException e) {
			this.module.getModuleContext().getLogService().error(e);
			// Should nether happen
			throw new InterruptedException (e.getMessage());
		}

		IModelingSession session = this.module.getModuleContext().getModelingSession ();

		ClassTemplate classTemplate = new ClassTemplate (this.report, session);

		// Create module parameter cache
		JavaConfiguration javaConfig = new JavaConfiguration (this.module.getModuleContext().getConfiguration ());

		// Generation
		for (NameSpace element : this.elementsToGenerate) {
			if ((element instanceof Package && !JavaDesignerUtils.isPackageAnnotated ((Package) element)) ||
					(element instanceof Component && JavaDesignerUtils.isAJavaComponent(element))) {
				// Nothing to do, the directory will be created later
			} else {
				// Compute the real file to generate
				File realFile = JavaDesignerUtils.getFilename (element, this.module);

				monitor.setTaskName (Messages.getString ("Info.ProgressBar.Generating", realFile)); //$NON-NLS-1$

				this.module.getModuleContext().getLogService().info (Messages.getString ("Info.ProgressBar.Generating", realFile));

				// Use a temporary file
				File tempFile = new File (tempDir, "tempFile" + filesToMove.size());
				filesToMove.add (new JavaMoveFileStruct(tempFile, realFile, element));

				// Generate the code
				generate(classTemplate, javaConfig, element, tempFile);

				updateProgressBar (null);
				if (monitor.isCanceled ()) {
					break;
				}
			}
		}

		// Move all temporary files towards their real destinations
		if (!this.report.hasErrors ()) {
			if (!filesToMove.isEmpty()) {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						ICoreSession coreSession = GProject.getProject(filesToMove.get(0).element).getSession();
						try (ITransaction t = coreSession.getTransactionSupport().createTransaction("Update generation dates")) {
							t.disableUndo();

							Set<Package> generatedPackages = new HashSet<>();

							for (JavaMoveFileStruct tempStruct : filesToMove) {
								// Save the file time on the element
								JavaDesignerUtils.updateDate (session, tempStruct.element, tempStruct.executeMove());

								if (tempStruct.element instanceof Package) {
									generatedPackages.add((Package) tempStruct.element);
								}

								ModelTree parent = tempStruct.element.getOwner();
								while (parent instanceof Package) {
									generatedPackages.add((Package) parent);
									parent = parent.getOwner();
								}
							}
							t.commit();
						}
					}
				});
			}
		} else {
			this.report.addError (Messages.getString ("Error.GenerationCanceled"), null, Messages.getString ("Error.GenerationCanceled.GenerationFailed")); //$NON-NLS-1$  //$NON-NLS-2$
		}

		this.module.getModuleContext().getLogService().info (this.elementsToGenerate.size () + " elements generated in " + formatTime (getElapsedTime ()));

		ProgressBar.monitor.done ();
	}

	private void generate(ClassTemplate classTemplate, JavaConfiguration javaConfig, NameSpace element, File targetFile) {
		try (FileOutputStream fos = new FileOutputStream (targetFile); PrintStream out = new PrintStream (new BufferedOutputStream (fos), false, javaConfig.ENCODING)) {
			// Call the template
			try {
				classTemplate.generate (element, out, javaConfig);

				// Flush the output in case the buffer is not empty
				out.flush ();

				out.close();
			} catch (TemplateException|CustomException e) {
				this.report.addError (e.getMessage (), element, "");
			} catch (Exception e) {
				this.module.getModuleContext().getLogService().error(e);
				this.report.addError (e.getMessage (), element, "");
			}
		} catch (IOException e) {
			this.report.addError (e.getMessage (), element, "");
		}
	}

	/**
	 * Simple file structure used to delay a file move related to a {@link NameSpace}.
	 */
	private static class JavaMoveFileStruct {
		private File sourceFile;

		private File targetFile;

		private NameSpace element;

		JavaMoveFileStruct(File sourceFile, File targetFile, NameSpace element) {
			this.sourceFile = sourceFile;
			this.targetFile = targetFile;
			this.element = element;
		}

		/**
		 * Move the source file to the target file.
		 * @return the modification date of the target file.
		 */
		long executeMove() {
			if (this.sourceFile.exists ()) {
				if (this.targetFile.exists ()) {
					this.targetFile.delete ();
				}

				if (!this.sourceFile.renameTo (this.targetFile)) {
					// If the rename fails, copy the file manually then delete the original...
					JavaDesignerUtils.copyFile (this.sourceFile, this.targetFile);
					this.sourceFile.delete ();
				}
			}
			return this.targetFile.lastModified ();
		}

	}

}
