package com.lzp.router.compiler.processore;

import com.google.auto.service.AutoService;
import com.google.gson.reflect.TypeToken;
import com.lzp.router.annotation.Router;
import com.lzp.router.compiler.Constants;
import com.lzp.router.compiler.FileUtils;
import com.lzp.router.compiler.RouterMeta;
import com.lzp.router.compiler.TypeUtils;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedOptions(Constants.KEY_MODULE_NAME)
@SupportedAnnotationTypes(Constants.ANNOTATION_TYPE_ROUTE)
public class RouterProcessor extends AbstractProcessor {

    Messager mMessage;
    File mTableFile;
    Types mTypeUtils;
    Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessage = processingEnvironment.getMessager();
        mTableFile = FileUtils.getTableFile(processingEnvironment, getModelName());
        mTypeUtils = processingEnvironment.getTypeUtils();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Router.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        int size = roundEnvironment.getElementsAnnotatedWith(Router.class).size();
        mMessage.printMessage(Diagnostic.Kind.NOTE, size + " annotation with router");

        List<RouterMeta> routers = processRouterAnnotation(roundEnvironment.getElementsAnnotatedWith(Router.class));
        mMessage.printMessage(Diagnostic.Kind.NOTE, "router size=" + routers.size());
        generateRouterTableFile(routers);
        return true;
    }

    private List<RouterMeta> processRouterAnnotation(Set<? extends Element> elements) {
        List<RouterMeta> routers = new ArrayList<>();

        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            Router router = typeElement.getAnnotation(Router.class);
            String fullClassName = typeElement.getQualifiedName().toString();
            String path = router.path();
            String group = getGroupName(router);

            RouterMeta.Builder routerMetaBuilder = new RouterMeta.Builder().destination(fullClassName)
                    .path(path).group(group);

            //annotate in Activity
            if (mTypeUtils.isSubtype(typeElement.asType(), TypeUtils.getActivityTypeMirror(mElementUtils))) {
                routerMetaBuilder.type(RouterMeta.RouterType.ACTIVITY);
            }

            //annotate in Service
            if (mTypeUtils.isSubtype(typeElement.asType(), TypeUtils.getServiceTypeMirror(mElementUtils))) {
                routerMetaBuilder.type(RouterMeta.RouterType.SERVICE);
            }

            //annotate in Fragment
            if (mTypeUtils.isSubtype(typeElement.asType(), TypeUtils.getFragmentTypeMirror(mElementUtils))) {
                routerMetaBuilder.type(RouterMeta.RouterType.FRAGMENT);
            }

            //annotate in Fragment V4
            if (mTypeUtils.isSubtype(typeElement.asType(), TypeUtils.getFragmentV4TypeMirror(mElementUtils))) {
                routerMetaBuilder.type(RouterMeta.RouterType.FRAGMENT_V4);
            }

            routers.add(routerMetaBuilder.build());
        }
        return routers;
    }


    private String getGroupName(Router router) {
        String path = router.path();
        String group = router.group();

        if (group.equals("")) {
            int index = path.indexOf("/") + 1;
            group = path.substring(index, path.indexOf("/", index));
        }
        return group;
    }

    /**
     * @param routers
     */
    private void generateRouterTableFile(List<RouterMeta> routers) {
        try {
            FileWriter writer = new FileWriter(mTableFile, true);

            for (RouterMeta meta : routers) {
                writer.write(meta.toString());
                writer.write("\r\n");
            }

            writer.close();
        } catch (Exception e) {
            mMessage.printMessage(Diagnostic.Kind.NOTE, "********* generateRouterTableFile error *********" + e.toString());
        }
    }

    private String getModelName() {
        // Attempt to get user configuration [moduleName]
        String moduleName = null;
        Map<String, String> options = processingEnv.getOptions();
        if (options != null && options.size() > 0) {
            moduleName = options.get(Constants.KEY_MODULE_NAME);
        }
        if (moduleName == null || moduleName.equals("")) {
            moduleName = "default" + System.currentTimeMillis();
        }
        return moduleName;
    }

//    private void generateRouterClass(LinkedHashMap<String, String> routers) {
//        if (routers == null || routers.size() == 0)
//            return;
//
//        TypeName hashMapOfTable = ParameterizedTypeName.get(HashMap.class, String.class, String.class);
//
//        FieldSpec routermap = FieldSpec.builder(hashMapOfTable, "table")
//                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                .build();
//
//        CodeBlock.Builder codeBlockVuilder = CodeBlock.builder();
//        codeBlockVuilder.addStatement("table = new $T()", hashMapOfTable);
//        for (Map.Entry<String, String> entry : routers.entrySet()) {
//            mMessage.printMessage(Diagnostic.Kind.NOTE, "url=" + entry.getKey() + ",class=" + entry.getValue());
//            try {
//                codeBlockVuilder.addStatement("table.put($S,$S)", entry.getKey(), entry.getValue());
//            } catch (Exception e) {
//                mMessage.printMessage(Diagnostic.Kind.NOTE, "***********generateRouterClass error*************");
//                mMessage.printMessage(Diagnostic.Kind.NOTE, e.toString());
//            }
//        }
//
//
//        TypeSpec routerClazz = TypeSpec.classBuilder(Constants.TABLE_CLASS_PRE_NAME + Constants.SEPERATRO + getModelName())
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                .addField(routermap)
//                .addStaticBlock(codeBlockVuilder.build())
//                .build();
//
//        JavaFile javaFile = JavaFile.builder(Constants.PACKAGE_NAME, routerClazz)
//                .build();
//        try {
//            javaFile.writeTo(processingEnv.getFiler());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
