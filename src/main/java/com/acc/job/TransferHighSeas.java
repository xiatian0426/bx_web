package com.acc.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component("transferHighSeas")
public class TransferHighSeas {
	
	
	/**
	 * 执行转移公海的任务 0 0 1 * * ? 每天1点执行
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void execute () {
		System.out.println("===> 执行转移公海任务");
	}
}
