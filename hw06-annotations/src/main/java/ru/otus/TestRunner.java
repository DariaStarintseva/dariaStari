package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"java:S106", "java:S1118", "java:S112"})
public class TestRunner {

    public static void runTests(String className) {
        try {
            Class<?> testClass = Class.forName(className);

            Method[] methods = testClass.getDeclaredMethods();

            Method beforeMethod = findAnnotatedMethod(methods, Before.class);
            Method afterMethod = findAnnotatedMethod(methods, After.class);
            List<Method> testMethods = findAnnotatedMethods(methods, Test.class);

            executeTests(testClass, beforeMethod, afterMethod, testMethods);

        } catch (Exception e) {
            System.out.println("Error while running tests: " + e.getMessage());
        }
    }

    private static <T extends Annotation> Method findAnnotatedMethod(Method[] methods, Class<T> annotation) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                return method;
            }
        }
        return null;
    }

    private static <T extends Annotation> List<Method> findAnnotatedMethods(Method[] methods, Class<T> annotation) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

    private static void executeTests(
            Class<?> testClass, Method beforeMethod, Method afterMethod, List<Method> testMethods) {
        int total = 0;
        int passed = 0;
        int failed = 0;

        for (Method testMethod : testMethods) {
            total++;
            Object testInstance = null;
            try {
                testInstance = testClass.getDeclaredConstructor().newInstance();
                if (beforeMethod != null) {
                    invokeMethod(beforeMethod, testInstance);
                }
                invokeMethod(testMethod, testInstance);
                passed++;
            } catch (Exception e) {
                failed++;
                System.out.println("Test " + testMethod.getName() + " failed: " + e.getCause());
            } finally {
                if (afterMethod != null) {
                    try {
                        invokeMethod(afterMethod, testInstance);
                    } catch (Exception e) {
                        System.out.println("Error in @After method: " + e.getCause());
                    }
                }
            }
        }
        printStatistics(total, passed, failed);
    }

    private static void invokeMethod(Method method, Object instance) throws Exception {
        method.invoke(instance);
    }

    private static void printStatistics(int total, int passed, int failed) {
        System.out.println("Total tests: " + total);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}
