import com.render.cache.CacheKit;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;

/**
 * Created by Administrator on 2017/8/31.
 */
public class EhCacheTest {

    @Test
    public void test() {

        String userHome = System.getProperty("user.home");
        String userDir = System.getProperty("user.dir");
        String tmpDir = System.getProperty("java.io.tmpdir");

        System.out.println("userHome:"+userHome);
        System.out.println("userDir:"+userDir);
        System.out.println("tmpDir:"+tmpDir);

        Cache memoryOnlyCache = new Cache("testCache", 5000, false, false, 0, 200);
        CacheKit cacheKit = CacheKit.getInstance();
        CacheManager manager = CacheKit.getCacheManager();
        manager.addCache(memoryOnlyCache);
        Cache test = manager.getCache("testCache");
        System.out.println("===========cache name:"+test.getName());

        cacheKit.put("testCache","name","gangan");
        String value = cacheKit.get("testCache","name");
        System.out.println("===========cache value:"+value);

        //CacheManager.getInstance().shutdown();
    }
}
