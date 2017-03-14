package com.arthur.annotationProcess;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.lang.model.SourceVersion;



/**
 * Created by wangtao on 17/3/14.
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_6 )
@SupportedAnnotationTypes({
        // Set of full qullified annotation type names
        "com.arthur.annotation.Query"
})
public class AnnotationProcessorFactory extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annoations,
                           RoundEnvironment env) {
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add("com.arthur.annotation.Query");
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }
}
