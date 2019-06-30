package github.asan.bud.cli.parser.page;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/30 1:20 AM
 * @history: 1.2019/6/30 created by jianfeng.zheng
 */
public class PageLine {

    private String line;

    private boolean isPage = false;
    private boolean isFragment = false;
    private boolean isContainer = false;
    private boolean isComponent = false;
    private boolean isRemark = false;
    private boolean isSystemConfig = false;

    public PageLine(String line) {
        this.line = line;
        this.parse();
    }

    private void parse() {
        if (line.startsWith(">>")) {
            this.isFragment = true;
        } else if (line.startsWith(">")) {
            this.isPage = true;
        } else if (line.startsWith("#")) {
            this.isContainer = true;
        } else if (line.startsWith("```系统配置") || line.startsWith("```config")) {
            this.isSystemConfig = true;
        } else if (line.startsWith("```")) {
            this.isRemark = true;
        } else if (line.startsWith("-")) {
            this.isComponent = true;
        }
    }

    public String getLine() {
        return line;
    }

    public String getContent() {
        if (isFragment) {
            return line.substring(2).trim();
        }
        if (isPage || isContainer || isComponent) {
            return line.substring(1).trim();
        }
        return line;
    }

    public boolean isPage() {
        return isPage;
    }

    public boolean isFragment() {
        return isFragment;
    }

    public boolean isContainer() {
        return isContainer;
    }

    public boolean isComponent() {
        return isComponent;
    }

    public boolean isRemark() {
        return isRemark;
    }

    public boolean isSystemConfig() {
        return isSystemConfig;
    }

    public boolean isAnyComponet(){
        return this.isComponent||this.isComponent||this.isFragment||this.isContainer;
    }
}
