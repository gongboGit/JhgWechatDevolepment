import com.jhg.marketing.web.AccurateStarter;
import com.jhg.marketing.web.admin.controller.MenuController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLEncoder;

/**
 * @author lxl
 * @since 2019/7/31 11:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccurateStarter.class)
public class MenuTest {

    @Autowired
    private MenuController menuController;

    @Test
    public void testGetMenu(){
        System.out.println(menuController.getMenuInfo());
    }

    @Test
    public void testGetCurrentSelfMenuInfo(){
        System.out.println(menuController.getCurrentSelfMenuInfo());
    }

    @Test
    public void testCreateMenu(){

//        System.out.println(menuController.createMenu("{\"button\":[{\"name\":\"百度一下\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://www.baidu.com\"},{\"name\":\"菜单\",\"sub_button\":[{\"name\":\"拍照发图\",\"sub_button\":[],\"type\":\"pic_photo_or_album\",\"key\":\"31\"},{\"name\":\"发送位置\",\"sub_button\":[],\"type\":\"location_select\",\"key\":\"32\"}]},{\"name\":\"个人中心\",\"sub_button\":[],\"type\":\"view\",\"url\":\"http://brzkqp.natappfree.cc/authorize\"}]}"));
    }

    @Test
    public void testCreateConditionalMenu(){
        System.out.println(menuController.createConditionalMenu("{\"button\":[{\"name\":\"BI\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://www.baidu.com\"},{\"name\":\"菜单\",\"sub_button\":[{\"name\":\"拍照发图\",\"sub_button\":[],\"type\":\"pic_photo_or_album\",\"key\":\"31\"},{\"name\":\"发送位置\",\"sub_button\":[],\"type\":\"location_select\",\"key\":\"32\"}]},{\"name\":\"个人中心\",\"sub_button\":[],\"type\":\"view\",\"url\":\"http://brzkqp.natappfree.cc/authorize\"}],\"matchrule\":{\"tag_id\":\"100\"}}"));
    }

    @Test
    public void testTryMachConditionalMenu(){
        System.out.println(menuController.tryMachConditionalMenu("L1914849571"));
    }

    @Test
    public void testParam(){
        String param = "{\"button\":[{\"name\":\"百度一下\",\"type\":\"view\",\"url\":\"https://www.baidu.com\"},{\"name\":\"菜单\",\"sub_button\":{\"list\":[{\"name\":\"拍照发图\",\"type\":\"pic_photo_or_album\",\"key\":\"31\"},{\"name\":\"发送位置\",\"type\":\"location_select\",\"key\":\"32\"}]}},{\"name\":\"个人中心\",\"type\":\"view\",\"url\":\"http://ykt.cqjhgyyxx.com\"}]}";
        System.out.println(URLEncoder.encode(param));
    }
}
