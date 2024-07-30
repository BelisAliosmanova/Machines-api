package com.machines.machines_api.annotations;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class AnnotationInvocationHandler implements Annotation, InvocationHandler, Serializable {
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<>();
    private final Class<? extends Annotation> annotationType;
    private final Map<String, Object> values;
    private final int hashCode;

    public AnnotationInvocationHandler(Class<? extends Annotation> annotationType, Map<String, Object> values) throws AnnotationFormatException {
        Class<?>[] interfaces = annotationType.getInterfaces();
        if (annotationType.isAnnotation() && interfaces.length == 1 && interfaces[0] == Annotation.class) {
            this.annotationType = annotationType;
            this.values = Collections.unmodifiableMap(normalize(annotationType, values));
            this.hashCode = this.calculateHashCode();
        } else {
            throw new AnnotationFormatException(annotationType.getName() + " is not an annotation type");
        }
    }

    static Map<String, Object> normalize(Class<? extends Annotation> annotationType, Map<String, Object> values) throws AnnotationFormatException {
        Set<String> missing = new HashSet();
        Set<String> invalid = new HashSet();
        Map<String, Object> valid = new HashMap();
        Method[] var5 = annotationType.getDeclaredMethods();
        int var6 = var5.length;

        for (Method element : var5) {
            String elementName = element.getName();
            if (values.containsKey(elementName)) {
                Class<?> returnType = element.getReturnType();
                if (returnType.isPrimitive()) {
                    returnType = primitiveWrapperMap.get(returnType);
                }

                if (returnType.isInstance(values.get(elementName))) {
                    valid.put(elementName, values.get(elementName));
                } else {
                    invalid.add(elementName);
                }
            } else if (element.getDefaultValue() != null) {
                valid.put(elementName, element.getDefaultValue());
            } else {
                missing.add(elementName);
            }
        }

        if (!missing.isEmpty()) {
            throw new AnnotationFormatException("Missing value(s) for " + String.join(",", missing));
        } else if (!invalid.isEmpty()) {
            throw new AnnotationFormatException("Incompatible type(s) provided for " + String.join(",", invalid));
        } else {
            return valid;
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.values.containsKey(method.getName()) ? this.values.get(method.getName()) : method.invoke(this, args);
    }

    public Class<? extends Annotation> annotationType() {
        return this.annotationType;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!this.annotationType.isInstance(other)) {
            return false;
        } else {
            Annotation that = (Annotation)this.annotationType.cast(other);
            Iterator var3 = this.values.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> element = (Map.Entry)var3.next();
                Object value = element.getValue();

                Object otherValue;
                try {
                    otherValue = that.annotationType().getMethod((String)element.getKey()).invoke(that);
                } catch (ReflectiveOperationException var8) {
                    throw new RuntimeException(var8);
                }

                if (!Objects.deepEquals(value, otherValue)) {
                    return false;
                }
            }

            return true;
        }
    }

    public int hashCode() {
        return this.hashCode;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('@').append(this.annotationType.getName()).append('(');
        Set<String> sorted = new TreeSet(this.values.keySet());

        String elementName;
        String value;
        for(Iterator var3 = sorted.iterator(); var3.hasNext(); result.append(elementName).append('=').append(value).append(", ")) {
            elementName = (String)var3.next();
            if (this.values.get(elementName).getClass().isArray()) {
                value = Arrays.deepToString(new Object[]{this.values.get(elementName)}).replaceAll("^\\[\\[", "[").replaceAll("]]$", "]");
            } else {
                value = this.values.get(elementName).toString();
            }
        }

        if (this.values.size() > 0) {
            result.delete(result.length() - 2, result.length());
        }

        result.append(")");
        return result.toString();
    }

    private int calculateHashCode() {
        int hashCode = 0;

        Map.Entry element;
        for(Iterator var2 = this.values.entrySet().iterator(); var2.hasNext(); hashCode += 127 * ((String)element.getKey()).hashCode() ^ this.calculateHashCode(element.getValue())) {
            element = (Map.Entry)var2.next();
        }

        return hashCode;
    }

    private int calculateHashCode(Object element) {
        if (!element.getClass().isArray()) {
            return element.hashCode();
        } else if (element instanceof Object[]) {
            return Arrays.hashCode((Object[])element);
        } else if (element instanceof byte[]) {
            return Arrays.hashCode((byte[])element);
        } else if (element instanceof short[]) {
            return Arrays.hashCode((short[])element);
        } else if (element instanceof int[]) {
            return Arrays.hashCode((int[])element);
        } else if (element instanceof long[]) {
            return Arrays.hashCode((long[])element);
        } else if (element instanceof char[]) {
            return Arrays.hashCode((char[])element);
        } else if (element instanceof float[]) {
            return Arrays.hashCode((float[])element);
        } else if (element instanceof double[]) {
            return Arrays.hashCode((double[])element);
        } else {
            return element instanceof boolean[] ? Arrays.hashCode((boolean[])element) : Objects.hashCode(element);
        }
    }

    static {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
    }
}
