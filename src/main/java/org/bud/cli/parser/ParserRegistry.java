package org.bud.cli.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午2:15
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public final class ParserRegistry {
    private Map<String, IComponentParser> parsers = new HashMap<>();

    private static ParserRegistry instance;

    private ParserRegistry() {

    }

    public static ParserRegistry getInstance() {
        if (instance == null) {
            instance = new ParserRegistry();
        }
        return instance;
    }

    public void registry(String type, IComponentParser parser) {
        parsers.put(type.toUpperCase(), parser);
    }

    public IComponentParser findParser(String type) {
        return parsers.get(type.toUpperCase());
    }
}
