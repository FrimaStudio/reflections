package org.reflections.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jpmorin
 */
public class NumericUtils {
    private static Map<Class, Integer> score = null;

    static {
        score = new HashMap<>();
        score.put(byte.class, 0);
        score.put(short.class, 1);
        score.put(int.class, 2);
        score.put(long.class, 3);
        score.put(float.class, 4);
        score.put(double.class, 5);
    }

    /**
     * Determines if a value of type 'from', a 'java.lang.Number' subclass or PrimitiveType, can be assigned to a field or
     * parameter of type 'to', another 'java.lang.Number' subclass or PrimitiveType.
     *
     * @param from 'java.lang.Number' subclass or PrimitiveType to assign.
     * @param to   'java.lang.Number' subclass or PrimitiveType to assign to.
     * @return Returns true if a value of type 'from' can be assigned to a field of type 'to'. Otherwise returns false.
     */
    public static <F extends Number, T extends Number> boolean isAssignableFrom(Class<F> from, Class<T> to) {
        from = getPrimitiveType(from);
        to = getPrimitiveType(to);

        int scoreFrom = score.get(from);
        int scoreTo = score.get(to);

        return scoreFrom <= scoreTo;
    }

    /**
     * Retrieve the primitive type of any 'java.lang.Number' subclass.
     *
     * @param type A 'java.lang.Number' subtype.
     * @param <T>  Enforce generic type is a 'java.lang.Number' subtype.
     * @return If the specified type is a primitive type, returns it. Otherwise return the primitive type of the current Number subtype.
     */
    public static <T extends Number> Class<T> getPrimitiveType(Class<T> type) {
        if (!type.isPrimitive()) {
            try {
                type = (Class<T>) type.getField("TYPE").get(null);
            } catch (Exception exception) {
                throw new RuntimeException(String.format("The type '%s' is a 'java.lang.Number' subclass, but it does have a 'TYPE' field.", exception));
            }
        }

        return type;
    }

    /**
     * Determines if a given type is numeric or not.
     *
     * @param type The type to analyse.
     * @return Returns true if the given type is a numeric type.
     */
    public static boolean isNumericType(Class type) {
        boolean result = false;

        if (type.isPrimitive()) {
            if (type != boolean.class && type != char.class) {
                result = true;
            }
        } else if (Number.class.isAssignableFrom(type)) {
            result = true;
        }

        return result;
    }
}
