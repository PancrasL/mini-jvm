package indi.pancras.jvm.rtda.heap;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.Reference;

public class StringPool {
    private static final HashMap<String, JObject> map = new HashMap<>();

    public static JObject getJString(JClassLoader loader, String rawStr) {
        if (map.containsKey(rawStr)) {
            return map.get(rawStr);
        }
        byte[] bytes = rawStr.getBytes(StandardCharsets.UTF_8);
        JObject jChars = new JObject(loader.loadClass("[C"), bytes.length, bytes);

        JObject jStr = loader.loadClass("java.lang.String").newInstance();
        jStr.setRefVar("value", "[C", new Reference(jChars));
        map.put(rawStr, jStr);
        return jStr;
    }
}
