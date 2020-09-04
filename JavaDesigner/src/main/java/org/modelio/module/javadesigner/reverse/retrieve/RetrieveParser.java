package org.modelio.module.javadesigner.reverse.retrieve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modelio.module.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

public class RetrieveParser {
    private Pattern endPattern;

    private Pattern startPattern;

    private IReportWriter report;

    public RetrieveParser(final IReportWriter report) {
        this.report = report;
        
        // Compile the two patterns
        this.startPattern = Pattern.compile("\\s*//begin of modifiable zone(\\((.*)\\))?\\.*([TC])/(.*)");
        this.endPattern = Pattern.compile("\\s*//end of modifiable zone(\\(.*\\))?\\.*E/(.+)");
    }

    public List<IRetrieveData> retrieve(final File fileToRetrieve, final String charsetName) {
        List<IRetrieveData> zones = new ArrayList<>();
        NoteData zone = null;
        
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(fileToRetrieve), charsetName))) {
            while (true) {
                String line = inputStream.readLine();
                if (line != null) {
                    if (zone == null) {
                        // First, we need to match the start marker
                        zone = findStartMarkerZone(line);
                    } else {
                        // Start marker already found, match the end marker for this zone
                        if (isEndZone(line)) {
                            // Match found, this zone is finished
                            zones.add(zone);
                            zone = null;
                        } else {
                            // No matching end marker found, add the line as zone content
                            String currentContent = zone.getNoteContent();
                            if (!currentContent.isEmpty()) {
                                currentContent += "\n";    
                            }
                            zone.setNoteContent(currentContent + line);
                        }
                    }
                } else {
                    break;
                }
            }
        
            inputStream.close ();
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        return zones;
    }

    private NoteData findStartMarkerZone(final String line) {
        NoteData ret = null;
        
        Matcher startMatcher = this.startPattern.matcher(line);
        boolean matchFound = startMatcher.find();
        if (matchFound) { // Get all groups for this match
            
            int matchCount = startMatcher.groupCount();
            if (matchCount == 4) {
                // This should be a new note to create:
                String noteType = startMatcher.group(2);
                String marker = startMatcher.group(3);
                String id = startMatcher.group(4);
        
                if (marker.equals("C")) { // New note
                    ret = new NewNote(noteType, "", id, this.report);                    
                } else if (marker.equals("T")) { // Existing note
                    ret = new ExistingNote(noteType, "", id, this.report);
                }
            } else if (matchCount == 2) {
                // This should be a note to update:
                String marker = startMatcher.group(1);
                String id = startMatcher.group(2);
                
                if (marker.equals("T")) { // Existing note
                    ret = new ExistingNote(null, "", id, this.report);
                }
            }
        }
        return ret;
    }

    private boolean isEndZone(final String line) {
        Matcher endMatcher = this.endPattern.matcher(line);
        boolean matchFound = endMatcher.find();
        if (matchFound) { // Get all groups for this match
            return true;
        }
        return false;
    }

}
