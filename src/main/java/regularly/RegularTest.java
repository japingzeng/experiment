package regularly;


import org.springframework.util.StopWatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author japing
 * @Date 2017/3/14 9:59
 * @Description:
 */
public class RegularTest {

    private static final Pattern p = Pattern.compile("(mweb_img_text_answer)");

    public static void main(String[] args) {
        String str = new String("{\"code\":1,\"errorMsg\":null,\"msg\":null,\"data\":{\"type\":\"show\",\"content\":\"{\\\"answers\\\":{\\\"answer\\\":\\\"<p openid=\\\\\\\"256\\\\\\\"></p><p>ss</p><p></p>\\\",\\\"answer_note\\\":0,\\\"code_answer\\\":\\\"\\\",\\\"code_note\\\":0,\\\"ext_property\\\":{\\\"closeFlag\\\":false,\\\"interactiveId\\\":\\\"e82db7a604554d91ac38f1b7d1c37130\\\",\\\"inviteFlag\\\":false,\\\"isKefuAnswer\\\":false,\\\"messageid\\\":\\\"7dc87450085911e7a5ecb8ca3a5efec4\\\",\\\"module_chinse_name\\\":\\\"开头语\\\",\\\"nodeInfo\\\":\\\"节点名=开头语应答节点,节点编码=WelcomeClassifyReplyEngineService,答案规则=上午 06:00~11:00\\\",\\\"opensentenceUUID\\\":\\\"2d7c408c-f6b4-4b59-9c61-60391b22a67f\\\",\\\"refreshContent\\\":false,\\\"responseCode\\\":1,\\\"resultType\\\":\\\"common\\\",\\\"userGuideFlag\\\":false}},\\\"ptype\\\":\\\"mweb_plain_text_answer\\\"}\",\"invitingFlag\":false,\"changeToManual\":false,\"changeToAnswer\":null,\"changeToURL\":null,\"answer\":null,\"answer_title\":null,\"welcomeCardAnswer\":\"{\\\"answers\\\":{\\\"answer\\\":{\\\"id\\\":\\\"19\\\",\\\"img\\\":[{\\\"href\\\":\\\"//www.jd.com\\\",\\\"id\\\":\\\"c9b0094ad3564552a98a4259c62615c0\\\",\\\"position\\\":\\\"1\\\",\\\"src\\\":\\\"//img10.360buyimg.com/test/jfs/t34/250/374061180/2763/885df067/58c0366eN0fe44c3e.png\\\"}],\\\"questiontip\\\":\\\" [{\\\\\\\"postion\\\\\\\":1,\\\\\\\"question\\\\\\\":\\\\\\\"能不能快点啊\\\\\\\",\\\\\\\"questionId\\\\\\\":\\\\\\\"lf_tips_141\\\\\\\",\\\\\\\"sid\\\\\\\":\\\\\\\"9E1BF7AC6FE75C090FF17C677E8BD1E8\\\\\\\",\\\\\\\"type\\\\\\\":5,\\\\\\\"userPin\\\\\\\":\\\\\\\"vitual_user_id_1748\\\\\\\"},{\\\\\\\"postion\\\\\\\":2,\\\\\\\"question\\\\\\\":\\\\\\\"7天无理由退换货是什么\\\\\\\",\\\\\\\"questionId\\\\\\\":\\\\\\\"lf_tips_141\\\\\\\",\\\\\\\"sid\\\\\\\":\\\\\\\"9E1BF7AC6FE75C090FF17C677E8BD1E8\\\\\\\",\\\\\\\"type\\\\\\\":5,\\\\\\\"userPin\\\\\\\":\\\\\\\"vitual_user_id_1748\\\\\\\"},{\\\\\\\"postion\\\\\\\":3,\\\\\\\"question\\\\\\\":\\\\\\\"订单取消了，什么时候能把款返回账户？\\\\\\\",\\\\\\\"questionId\\\\\\\":\\\\\\\"lf_tips_141\\\\\\\",\\\\\\\"sid\\\\\\\":\\\\\\\"9E1BF7AC6FE75C090FF17C677E8BD1E8\\\\\\\",\\\\\\\"type\\\\\\\":5,\\\\\\\"userPin\\\\\\\":\\\\\\\"vitual_user_id_1748\\\\\\\"}] \\\"},\\\"ext_property\\\":{\\\"closeFlag\\\":false,\\\"interactiveId\\\":\\\"43890fd0106748c3a6022fdb9802ecaf\\\",\\\"inviteFlag\\\":false,\\\"isKefuAnswer\\\":false,\\\"messageid\\\":\\\"7dc87450085911e7a5ecb8ca3a5efec4\\\",\\\"module_chinse_name\\\":\\\"开头语\\\",\\\"nodeInfo\\\":\\\"节点名=开头语应答节点,节点编码=WelcomeClassifyReplyEngineService,答案规则=首问语-m_web-m\\\",\\\"opensentenceUUID\\\":\\\"80f4dbff-d69d-4236-82ac-621b95b86f2e\\\",\\\"refreshContent\\\":false,\\\"responseCode\\\":1,\\\"resultType\\\":\\\"common\\\",\\\"userGuideFlag\\\":false}},\\\"ptype\\\":\\\"mweb_img_text_answer\\\"}\",\"cardInfo\":{\"cid\":\"9E1BF7AC6FE75C090FF17C677E8BD1E8\",\"userPin\":\"vitual_user_id_1748\",\"source\":\"m_web\"}}}");
        StopWatch watch = new StopWatch();
        watch.start();
        long start1 = System.currentTimeMillis();
        long end1 = 0;
        Matcher m = p.matcher(str);
        if (m.find()) {
            end1 = System.currentTimeMillis();
            watch.stop();
            System.out.println(m.group(1));
        }
        long diff1 = end1 - start1;
        System.out.println(String.valueOf(diff1));
        System.out.println(watch.prettyPrint());
        long start = System.currentTimeMillis();
        long end = 0;
        if (str.contains("mweb_img_text_answer")) {
            end = System.currentTimeMillis();
        }
        long diffrence = end - start;
        System.out.println(String.valueOf(diffrence));
    }
}
