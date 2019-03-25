package lesson7.test_framework;

import lesson7.TestEx;
import lesson7.classes_for_tests.TestExJU4TestCase;
import lesson7.test_framework.my_annotations.AfterSuite;
import lesson7.test_framework.my_annotations.BeforeSuite;
import lesson7.test_framework.my_annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestManager {
    public static void main(String[] args) {
        Class clazz = TestExJU4TestCase.class;
        start(clazz);
    }

    public static void start(Class clazz) {
        doIt(clazz);
    }

    public static void start(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        doIt(clazz);
    }

    private static void doIt(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        checkAnnotations(methods);
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        startMethodWithBeforeSuiteAnnotation(clazz, methods);
        startMethodWithTestAnnotation(clazz, methods);
        startMethodWithAfterSuiteAnnotation(clazz, methods);
    }

    private static void checkAnnotations(Method[] methods) {
        int beforeCount = 0;
        int afterCount = 0;
        for (Method method : methods) {
//            BeforeSuite annotation = method.getAnnotation(BeforeSuite.class);
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
//                System.out.println("method " + method + " annotation: " + annotation);
                if (annotation instanceof BeforeSuite) {
                    beforeCount++;
                }
                if (annotation instanceof AfterSuite) {
                    afterCount++;
                }
            }
            if (beforeCount > 1 || afterCount > 1) {
                throw new RuntimeException();
            }
        }
    }

    private static void startMethodWithAfterSuiteAnnotation(Class clazz, Method[] methods) {


        for (Method method : methods) {
            AfterSuite asAnnotaion = method.getAnnotation(AfterSuite.class);
            if (asAnnotaion != null) {
                try {
//                    Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
                    Constructor<TestExJU4TestCase> defaultConstructor = clazz.getDeclaredConstructor();
                    TestExJU4TestCase testClass = defaultConstructor.newInstance();
                    method.invoke(testClass);
//                    method.invoke(clazz.newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void startMethodWithTestAnnotation(Class clazz, Method[] methods) {
        for (Method method : methods) {
            Test testAnnotaion = method.getAnnotation(Test.class);
            if (testAnnotaion != null) {
                try {
//                    Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
                    Constructor<TestExJU4TestCase> defaultConstructor = clazz.getDeclaredConstructor();
                    TestExJU4TestCase testClass = defaultConstructor.newInstance();
                    method.invoke(testClass);
//                    method.invoke(clazz.newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void startMethodWithBeforeSuiteAnnotation(Class clazz, Method[] methods) {
        for (Method method : methods) {
            BeforeSuite bsAnnotaion = method.getAnnotation(BeforeSuite.class);
            if (bsAnnotaion != null) {
                try {
//                    Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
                    Constructor<TestExJU4TestCase> defaultConstructor = clazz.getDeclaredConstructor();
                    TestExJU4TestCase testClass = defaultConstructor.newInstance();
                    method.invoke(testClass);
//                    method.invoke(clazz.newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
