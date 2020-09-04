package org.modelio.module.javadesigner.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

public class JConsoleWithDialog {
    /**
     * Initial queue size for keystrokes.
     */
    private static final int QUEUE_SIZE = 256;

    /**
     * Keystroke list - insert/delete keystroke.
     */
    protected List<Character> typingList = new ArrayList<>();

    private final Color ERROR_COLOR;

    protected InfoDialog dialog;

    /**
     * Queue of input lines.
     */
    protected BlockingQueue<Character> lineQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    /**
     * Main constructor for JConsoleWithDialog.
     * @param dialog the info dialog to write messages into. Might be <code>null</code>.
     */
    public JConsoleWithDialog(final InfoDialog dialog) {
        super ();
        this.dialog = dialog;    
        this.ERROR_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_RED);
        
        if (dialog != null) {
            /* Anomalie #19:
             * Description: Define key listener for dialog.text widget.
             */
            dialog.getText().addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(final KeyEvent ke) {
                    super.keyPressed(ke);
        
                    /*
                     * It's not possible to modify the text characters outside
                     * the writable area, which begins at:
                     * 
                     * dialog.getTextString().length() - typingList.size()
                     *
                     */
                    int charPos = dialog.getText().getCaretOffset() + JConsoleWithDialog.this.typingList.size() - dialog.getTextString().length();
        
                    if (ke.character > 0){
                        if( charPos < 0) {
                            /*
                             * If the caret is outside writable area, go to last character in text. 
                             * append statement can be replaced also with setSelection:
                             *    dialog.getText().setSelection( dialog.getTextString().length() );
                             */
                            dialog.getText().append("");
                            // update character position 
                            charPos = JConsoleWithDialog.this.typingList.size();
                        }
                    }
        
                    // in case of BACKSPACE, remove previous character in the list
                    if (ke.character == SWT.BS) {
                        if (JConsoleWithDialog.this.typingList.size() > 0 && charPos > 0 ) {
                            JConsoleWithDialog.this.typingList.remove(charPos-1);
                        } else {
                            // suppress event: don't delete character from text component
                            // if it's not deleted from the list too
                            ke.doit = false;
                        }
                        // in case of DELETE, remove next character in the list    
                    } else if (ke.character == SWT.DEL) {
                        if (JConsoleWithDialog.this.typingList.size() > 0 && charPos >= 0 ) {
                            JConsoleWithDialog.this.typingList.remove(charPos);
                        } else {
                            // suppress event: don't delete character from text component
                            // if it's not deleted from the list too
                            ke.doit = false;
                        }
                    } else if (ke.character == SWT.CR || ke.character == SWT.LF) {                        
                        /* Windows CMD.EXE and Swing JTextArea returns LF (\n) when ENTER key is pressed,
                         * SWT Text widget returns CR (\r), and this causes troubles when reading input
                         * with java.util.Scanner. CR should be replaced with LF to work correctly.
                         */
                        JConsoleWithDialog.this.typingList.add(SWT.LF );                        
        
                        JConsoleWithDialog.this.lineQueue.addAll(JConsoleWithDialog.this.typingList);
                        JConsoleWithDialog.this.typingList.clear();
        
                        /* all new line characters, even if they are in the middle 
                         * of the writable area will be treated as the new line for 
                         * the whole input.
                         * use call to append, to show properly the line break at the 
                         * end of the input
                         */                         
                        dialog.getText().append("");                        
                    } else if( ke.character > 0 ) {
                        // don't read control keys, and insert characters
                        // inside the list relatively to the caret position
                        // NOTE: checking the value of charPos is not necessary
                        JConsoleWithDialog.this.typingList.add(charPos, ke.character);
                    }
                }
            });
        }
    }

    /**
     * Write an error message in the dialog and the console.
     * @param msg the message to print.
     */
    public void writeError(final String msg) {
        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(msg);
        
        if (this.dialog != null) {
            this.dialog.addText (msg, this.ERROR_COLOR);            
        }
    }

    /**
     * Write an info message in the dialog and the console.
     * @param msg the message to print.
     */
    public void writeInfo(final String msg) {
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info(msg);
        
        if (this.dialog != null) {
            this.dialog.addText (msg, null);
        }
    }

    /**
     * @return the next key typed in the console.
     */
    public int readInt() {
        Integer key = null;
        try {
            key = (int)this.lineQueue.take();
        } catch (InterruptedException ex) {
            // Exit case
            key = -1;
        }
        return key;
    }

    public void addDisposeListener(final DisposeListener listener) {
        if (this.dialog != null) {
            this.dialog.getText().addDisposeListener(listener);
        }
    }

}
