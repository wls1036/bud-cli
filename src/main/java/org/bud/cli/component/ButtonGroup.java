package org.bud.cli.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午12:57
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class ButtonGroup extends Component{
    private List<Button> buttons=new ArrayList<>();

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void addButton(Button button){
        this.buttons.add(button);
    }

    @Override
    public String getType() {
        return null;
    }
}
