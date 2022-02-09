package indi.pancras.jvm.rtda.heap;

import java.util.HashMap;

import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.Reference;

public class StringPool {
    private static final HashMap<String, Reference> map = new HashMap<>();

    public static Reference getJString(JClassLoader loader, String rawStr) {
        if (map.containsKey(rawStr)) {
            return map.get(rawStr);
        }
        char[] chars = rawStr.toCharArray();
        JObject charArr = new JObject(loader.loadClass("[C"), chars.length, ArrayType.CHAR, chars);

        JObject jStr = loader.loadClass("java.lang.String").newInstance();
        jStr.setFieldValue("value", "[C", new Reference(charArr));
        Reference ref = new Reference(jStr);
        map.put(rawStr, ref);
        return ref;
    }

    public static Reference internString(JObject jStr) {
        String s = new String(jStr.bytes());
        if (map.containsKey(s)) {
            return map.get(s);
        }
        map.put(s, new Reference(jStr));
        return map.get(s);
    }
}
