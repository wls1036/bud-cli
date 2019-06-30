package github.asan.bud.cli.pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/30 2:57 PM
 * @history: 1.2019/6/30 created by jianfeng.zheng
 */
public class AttrPatternFactory {

    private static AttrPatternFactory instance;

    private Map<String, AttrPattern> patterns = new HashMap<>();

    public static AttrPatternFactory getInstance() {
        if (instance == null) {
            instance = new AttrPatternFactory();
        }
        return instance;
    }

    public AttrPattern registStaticListPattern(String value) {
        AttrPattern pt = patterns.get(value);
        if (pt == null) {
            pt = new StaticListAttrPattern(value);
            patterns.put(value, pt);
        }
        return pt;
    }

    public AttrPattern registRegxPattern(String value) {
        AttrPattern pt = patterns.get(value);
        if (pt == null) {
            pt = new RegxAttrPattern(value);
            patterns.put(value, pt);
        }
        return pt;
    }
}
