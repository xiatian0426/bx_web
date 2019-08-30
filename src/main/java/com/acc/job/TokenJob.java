package com.acc.job;

import com.acc.model.BxToken;
import com.acc.service.IBxTokenService;
import com.acc.util.weChat.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时更新token
 *
 */
@Component("tokenJob")
public class TokenJob {

    @Autowired
    private IBxTokenService bxTokenService;

	/**
	 * 每一个小时执行一次
	 */
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void execute () {
	    try{
            System.out.println("===> 开始更新token");
            String access_token = WechatUtil.getDDToken();
            BxToken bxToken = new BxToken();
            bxToken.setAccessToken(access_token);
            bxTokenService.updateToken(bxToken);
            System.out.println("===> 结束更新token");
        }catch (Exception e){
            System.out.println("===> 更新token出错");
            e.printStackTrace();
        }

	}
}
