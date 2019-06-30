package github.asan.bud.cli.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 5:28 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class RegxAttrPattern extends AttrPattern {

    public static final Map<String, Pattern> REGX_PATTERNS = new HashMap<>();

    public RegxAttrPattern(String regx) {
        Pattern pt = REGX_PATTERNS.get(regx);
        if (pt == null) {
            REGX_PATTERNS.put(regx, Pattern.compile(regx, Pattern.DOTALL));
        }
    }


    @Override
    public boolean match(String attr) {
        Pattern pt = REGX_PATTERNS.get(attr);
        return pt != null && pt.matcher(attr).find();
    }
}
