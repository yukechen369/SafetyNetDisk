package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class ShowShareInfo implements Runnable {
    HashMap<String,String> map = null;
    public ShowShareInfo(String date){
        map = StringToMap(date);
    }
    @Override
    public void run() {
        String password = map.get("password");
        String hash = map.get("hash");
        String url = "http://127.0.0.1:8088/share?hash="+hash;
        new ShareWindow(url,password);
    }
    private   HashMap<String,String> StringToMap(String mapString)
    {
        HashMap map = new HashMap();
        java.util.StringTokenizer items;
        for (StringTokenizer entrys = new StringTokenizer(mapString, "^"); entrys.hasMoreTokens();
             map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
            items = new StringTokenizer(entrys.nextToken(), "¡¢");
        return map;
    }
}
