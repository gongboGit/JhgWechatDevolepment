import com.alibaba.fastjson.JSON;
import com.jhg.marketing.web.AccurateStarter;
import com.jhg.marketing.web.admin.controller.MenuController;
import com.jhg.marketing.web.controller.WeChatPayController;
import com.jhg.marketing.web.util.weChatPay.WXPayConfigImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * @author lxl
 * @since 2019/7/31 11:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccurateStarter.class)
public class WeChatPayTest {

    @Autowired
    private WeChatPayController weChatPayController;
    @Autowired
    private WXPayConfigImpl wxPayConfig;
    @Test
    public void testGetSandBoxSignKey(){
//        try {
//            System.out.println(weChatPayController.getPayParam(null,null));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            wxPayConfig.setKey("11");
            System.out.println(JSON.toJSONString(wxPayConfig));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
