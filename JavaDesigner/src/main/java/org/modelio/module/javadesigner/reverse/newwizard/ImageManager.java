package org.modelio.module.javadesigner.reverse.newwizard;

import java.util.HashMap;
import org.eclipse.swt.graphics.Image;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Singleton class used to load images. Images are loaded from the Modelio path,
 * and stored in a map to avoid multiple loadings.
 */
public class ImageManager {
    private static String modulePath = null;

    private static ImageManager INSTANCE;

    private HashMap<String, Image> map;

    public static void setModulePath(final String cxxPath) {
        ImageManager.modulePath = cxxPath;
    }

    private ImageManager() {
        this.map = new HashMap<>();
        
        // Here, you can define images for buttons...
        this.map.put("up", new Image(null, modulePath + "/res/bmp/up.png"));
        this.map.put("down", new Image(null, modulePath + "/res/bmp/down.png"));
        this.map.put("delete", new Image(null, modulePath + "/res/bmp/delete.png"));
        this.map.put("java", new Image(null, modulePath + "/res/bmp/javaFile.png"));
        this.map.put("class", new Image(null, modulePath + "/res/bmp/classFile.png"));
        this.map.put("jarfile", new Image(null, modulePath + "/res/bmp/jarfile.png"));
        this.map.put("jar", new Image(null, modulePath + "/res/bmp/jar.png"));
        this.map.put("directory", new Image(null, modulePath + "/res/bmp/directory.png"));
        this.map.put("missing", new Image(null, modulePath + "/res/bmp/missing.png"));
        
        this.map.put("javaclass", new Image(null, modulePath + "/res/bmp/class.png"));
        this.map.put("javadatatype", new Image(null, modulePath + "/res/bmp/datatype.png"));
        this.map.put("javaenumeration", new Image(null, modulePath + "/res/bmp/enumeration.png"));
        this.map.put("javainterface", new Image(null, modulePath + "/res/bmp/interface.png"));
        this.map.put("javapackage", new Image(null, modulePath + "/res/bmp/package.png"));
    }

    public static ImageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageManager();
        }
        return INSTANCE;
    }

    /**
     * Get the Image corresponding to the String.
     * @param element The element to use for choosing the image.
     * @return An Image.
     */
    public Image getIcon(final String element) {
        Image ret;
        
        ret = this.map.get(element);
        return ret;
    }

    /**
     * Get the Image corresponding to the object.
     * @param element The element to use for choosing the image.
     * @return An Image.
     */
    public Image getIcon(final MObject element) {
        
        return JavaDesignerModule.getInstance().getModuleContext().getModelioServices().getImageService().getStereotypedImage(element, null, false);
    }

}
