package org.modelio.module.javadesigner.reverse.retrieve;

import com.modelio.module.xmlreverse.IReportWriter;

public abstract class NoteData implements IRetrieveData {
    protected String noteType = null;

    protected String noteContent = null;

    protected IReportWriter report;

    public NoteData(final String noteType, final String noteContent, final IReportWriter report) {
        this.noteType = noteType;
        this.noteContent = noteContent;
        this.report = report;
    }

    public String getNoteType() {
        return this.noteType;
    }

    public void setNoteType(final String noteType) {
        this.noteType = noteType;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public void setNoteContent(final String noteContent) {
        this.noteContent = noteContent;
    }

    public IReportWriter getReport() {
        return this.report;
    }

}
