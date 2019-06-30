package github.asan.bud.cli.pattern;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 5:21 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class StaticListAttrPattern extends AttrPattern {

    public Set<String> words = new HashSet<>();

    public StaticListAttrPattern(String words) {
        String[] ws = words.split("|");
        this.words.addAll(Arrays.asList(ws));
    }

    @Override
    public boolean match(String attr) {
        for (String w : this.words) {
            if (w.equalsIgnoreCase(attr)) {
                return true;
            }
        }
        return false;
    }
}
