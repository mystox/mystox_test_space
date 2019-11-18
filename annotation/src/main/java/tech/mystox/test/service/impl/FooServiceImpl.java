package tech.mystox.test.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import tech.mystox.test.service.FooService;
import tech.mystox.test.stereotype.OperaCode;
import tech.mystox.test.stereotype.Register;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by mystoxlol on 2019/8/23, 13:50.
 * company: kongtrolink
 * description:
 * update record:
 */
@Component
public class FooServiceImpl implements FooService, EnvironmentCapable {
    Environment environment;

    @Override
    public void foo() {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("E:/IdeaProjects/mystox_test_space/annotation/target/classes/tech/mystox/test/service/FooServiceImpl.class");
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        try {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
            ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
            sbd.setResource(resource);
            sbd.setSource(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bar() {
        System.out.println("bar");
    }

    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        return false;
    }

    protected String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(getEnvironment().resolveRequiredPlaceholders(basePackage));
    }

    public static void main(String[] args) {
        String DEFAULT_RESOURCE_PATTERN = "**/*.class";
        String resourcePattern = DEFAULT_RESOURCE_PATTERN;
        Environment environment = new StandardEnvironment();
        String basePackagePath = ClassUtils.convertClassNameToResourcePath(environment.resolveRequiredPlaceholders("tech.mystox.test.service"));
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                basePackagePath + '/' + resourcePattern;
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                boolean readable = resource.isReadable();
                if (!readable) continue;
                MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
                boolean b = annotationMetadata.hasAnnotation(Register.class.getName());
                if (b) {
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    String className = classMetadata.getClassName();
                    Class<?> aClass = Class.forName(className);
                    Method[] methods = aClass.getMethods();
                    for (Method method : methods) {
                        OperaCode annotation = method.getAnnotation(OperaCode.class);
                        if (annotation == null) continue;
                        String code = annotation.code();
                        if (StringUtils.isEmpty(code)) {
                            String name = method.getName();
                            System.out.println(name);
                        } else
                        System.out.println(code);
                    }
                    System.out.println(className);
                    Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
                    System.out.println(JSON.toJSONString(annotationTypes));
                }

                sbd.setResource(resource);
                sbd.setSource(resource);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Environment getEnvironment() {
        if (this.environment == null) {
            this.environment = new StandardEnvironment();
        }
        return this.environment;
    }


}
