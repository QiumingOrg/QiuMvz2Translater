package top.genouka.autr.Layer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
//import top.genouka.autr.Cache;

import java.util.ArrayList;

public class ParatranzLayer implements SpecLayer {

    @Override
    public String spec2tjson(String content) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        String[] lines = content.split("\\r?\\n|\\r");
        StringBuilder d1 = new StringBuilder();
        boolean notfirstFor = false;
        int count1 = 0, count2 = 0;
        String key = "";
//        try (Cache cache = new Cache()) {
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (line.startsWith("[") && line.endsWith("]")) {
                    if (notfirstFor) {
                        JSONObject jb = new JSONObject();
                        jb.put("key", key);
                        String orgistr = d1.toString();
                        jb.put("original", orgistr);
//                        String qr = cache.query(orgistr + "\r\n");
//                        if (qr != null) {
//                            qr = qr.substring(0, qr.length() - 2);
//                        }
                        String qr = "";
                        jb.put("translation", qr == null ? "" : qr);
                        arrayList.add(jb);
                        d1 = new StringBuilder();
                        notfirstFor = false;
                    }
                    key = line.substring(1, line.length() - 1);
                    continue;
                } else if (notfirstFor) {
                    d1.append("\r\n");
                } else {
                    notfirstFor = true;
                }
                d1.append(line);
            }
            JSONObject jb = new JSONObject();
            jb.put("key", key);
            String orgistr = d1.toString();
            jb.put("original", orgistr);
//            String trstr = cache.query(orgistr + "\r\n");
//            jb.put("translation", trstr == null ? "" : trstr);
            jb.put("translation", "");
            arrayList.add(jb);
            d1 = new StringBuilder();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        JSONArray jsonArray = new JSONArray(arrayList);
        return jsonArray.toJSONString();
    }

    @Override
    public String tjson2original(String json) {
        JSONArray jsonArray = JSON.parseArray(json);
        StringBuilder sb = new StringBuilder();
        for (Object o : jsonArray) {
            JSONObject jo = (JSONObject) o;
            sb
                    .append("[")
                    .append(jo.getString("key"))
                    .append("]\r\n")
                    .append(jo.getString("original"))
                    .append("\r\n");
        }
        return sb.toString();
    }

    @Override
    public String tjson2translation(String json) {
        JSONArray jsonArray = JSON.parseArray(json);
        StringBuilder sb = new StringBuilder();
        for (Object o : jsonArray) {
            JSONObject jo = (JSONObject) o;
            sb
                    .append("[")
                    .append(jo.getString("key"))
                    .append("]\r\n")
                    .append(jo.getString("translation"))
                    .append("\r\n");
        }
        return sb.toString();
    }
}
