/*
 * Copyright 2011 Modeliosoft
 *
 * This file is part of Modelio.
 *
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.togaf.profile.ihm.composite;

import java.util.List;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class defines the popup of proposed results that appears when user uses
 * the 'Ctrl+Space' shortcut and there are several valid results.
 */
public class ResultsProposalPopup extends PopupDialog {
	protected boolean loop;

	protected List<MObject> elements;

	protected MObject selected;

	protected boolean acceptNullValue = false;

	protected Composite parent;

	protected org.eclipse.swt.widgets.List list;

	protected Rectangle listRectangle;

	public ResultsProposalPopup(Control control, List<MObject> elements, boolean acceptNullValue) {
		super(control.getShell(), INFOPOPUPRESIZE_SHELLSTYLE,
		/* take focus */true,
		/* persist size */false,
		/* persist location */false,
		/* show dialog menu */false,
		/* show persist action */false,
		/* info title */org.modelio.togaf.i18n.Messages.getString("ResultsProposalPopup.title"), //$NON-NLS-1$
				/* info description */org.modelio.togaf.i18n.Messages.getString("ResultsProposalPopup.description")); //$NON-NLS-1$

		this.parent = control.getParent();
		this.elements = elements;
		this.selected = null;
		this.loop = false;

		this.acceptNullValue = acceptNullValue;

		Rectangle textRect = control.getBounds();
		Rectangle tableRect = this.parent.getBounds();

		int posX = textRect.x;
		int posY = textRect.y;
		int width = textRect.width;
		int height = (tableRect.height - textRect.y);

		if (width < 100)
			width = 100;
		if (height < 100)
			height = 100;
		this.listRectangle = Display.getDefault().map(this.parent, null, new Rectangle(posX, posY, width, height));
	}

	@Override
	protected Point getInitialSize() {
		return new Point(10, 10);
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		return new Point(this.listRectangle.x, this.listRectangle.y);
	}

	@Override
	protected Control createDialogArea(Composite area) {
		Composite composite = (Composite) super.createDialogArea(area);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		this.list = new org.eclipse.swt.widgets.List(composite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);

		// getShell().setSize(200, 30);
		return composite;
	}

	public Object getChoice() {
		Display display = Display.getDefault();

		open();
		this.setTitleText(org.modelio.togaf.i18n.Messages.getString("ResultsProposalPopup.choose", "" + this.elements.size())); //$NON-NLS-1$ //$NON-NLS-2$
		// getShell().pack();
		if (getShell().getSize().y < 100)
			getShell().setSize(getShell().getSize().x, 100);

		if (this.acceptNullValue) {
			this.list.add(org.modelio.togaf.i18n.Messages.getString("None")); //$NON-NLS-1$ 
		}

		for (MObject e : this.elements) {
			if (e instanceof ModelElement) {
				ModelElement me = (ModelElement) e;
				String item = me.getName();
				MObject owner = me.getCompositionOwner();
				if (owner instanceof ModelElement) {
					item = item + "  (" + org.modelio.togaf.i18n.Messages.getString("From") + " " + ((ModelElement) me.getCompositionOwner()).getName() + ")";
				}
				this.list.add(item);
			}
		}

		// Validate on double click
		this.list.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				int selectedIndex = ResultsProposalPopup.this.list.getSelectionIndex();
				if (ResultsProposalPopup.this.acceptNullValue) {
					if (selectedIndex < 1) {
						ResultsProposalPopup.this.selected = null;
					} else {
						ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex - 1);
					}
				} else {
					ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex);
				}
				ResultsProposalPopup.this.loop = false;
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});

		// Validate on pressing <enter> key
		this.list.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.character == SWT.CR) {
					int selectedIndex = ResultsProposalPopup.this.list.getSelectionIndex();

					if (selectedIndex > -1) {
						if (ResultsProposalPopup.this.acceptNullValue) {
							if (selectedIndex == 0) {
								ResultsProposalPopup.this.selected = null;
							} else {
								ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex - 1);
							}
						} else {
							ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex);
						}
						ResultsProposalPopup.this.loop = false;
					}
				} else if (e.character == SWT.DEL) {
					ResultsProposalPopup.this.loop = false;
				}
			}

		});

		// Run a local event loop : the popup is modal.
		this.loop = true;
		while (this.loop) {
			try {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		close();
		return this.selected;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
	}

	@Override
	protected void adjustBounds() {
		this.getShell().setBounds(this.listRectangle);
	}

}
