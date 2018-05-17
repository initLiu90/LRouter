package com.lzp.router.compiler;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

public class TypeUtils {

    public static TypeMirror getActivityTypeMirror(Elements elementUtils) {
        return elementUtils.getTypeElement(Constants.ACTIVITY).asType();
    }

    public static TypeMirror getServiceTypeMirror(Elements elementUtils) {
        return elementUtils.getTypeElement(Constants.SERVICE).asType();
    }

    public static TypeMirror getFragmentTypeMirror(Elements elementUtils) {
        return elementUtils.getTypeElement(Constants.FRAGMENT).asType();
    }

    public static TypeMirror getFragmentV4TypeMirror(Elements elementUtils) {
        return elementUtils.getTypeElement(Constants.FRAGMENT_V4).asType();
    }
}
