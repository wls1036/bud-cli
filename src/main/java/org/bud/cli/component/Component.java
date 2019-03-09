package org.bud.cli.component;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:23
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Component {
    private String name;
    private String code;
    private String attrExpression;
    private String src;
    private String type;

    public String getType() {
        return this.type == null ? this.getClass().getSimpleName() : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (code == null) {
            this.code = name;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAttrExpression() {
        return attrExpression;
    }

    public void setAttrExpression(String attrExpression) {
        this.attrExpression = attrExpression;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
