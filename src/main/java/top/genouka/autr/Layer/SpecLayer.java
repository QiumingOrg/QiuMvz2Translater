package top.genouka.autr.Layer;

import com.alibaba.fastjson2.JSONObject;

public interface SpecLayer {
    String spec2tjson(String content);
    String tjson2original(String json);
    String tjson2translation(String json);
}
