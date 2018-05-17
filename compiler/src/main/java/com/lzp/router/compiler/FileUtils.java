package com.lzp.router.compiler;

import java.io.File;
import java.net.URI;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

public class FileUtils {

    public static File getTableFile(ProcessingEnvironment processingEnv, String modelName) {
        File tablesDir = new File(getAssetsPath(processingEnv) + "/" + Constants.TABLEFILE_DIR_NAME);
        if (!tablesDir.exists()) {
            tablesDir.mkdirs();
        }
        if (!tablesDir.exists())
            return null;

        File tableFile = new File(tablesDir, Constants.TABLEFILE_PRENAME + Constants.SEPERATRO + modelName + Constants.SEPERATRO + Constants.TABLEFILE_ENNAME);
        if (tableFile.exists()) {
            tableFile.delete();
        }
        return tableFile;
    }

    public static String getAssetsPath(ProcessingEnvironment processingEnv) {
        Filer filer = processingEnv.getFiler();
        try {
            JavaFileObject dummySourceFile = filer.createSourceFile("dummy" + System.currentTimeMillis());
            String dummySourceFilePath = dummySourceFile.toUri().toString();
//            mMessage.printMessage(Diagnostic.Kind.NOTE, "dummySourceFilePath=" + dummySourceFilePath);
            if (dummySourceFilePath.startsWith("file:")) {
                if (!dummySourceFilePath.startsWith("file://")) {
                    dummySourceFilePath = "file://" + dummySourceFilePath.substring("file:".length());
                }
            } else {
                dummySourceFilePath = "file://" + dummySourceFilePath;
            }

            URI cleanURI = new URI(dummySourceFilePath);

            File dummyFile = new File(cleanURI);

            File projectRoot = dummyFile.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();

            return projectRoot.getAbsolutePath() + "/src/main/assets";

        } catch (Exception e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "*********getAssetsPath error*********" + e.toString());
        }
        return null;
    }
}
