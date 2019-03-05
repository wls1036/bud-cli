package org.bud.cli.parser;

import org.bud.cli.component.Component;

import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午2:12
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public interface IComponentParser {
    public Component parse(List<String> lines);
}
