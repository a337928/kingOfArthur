package com.arthur.common.redis;


import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date: 13-10-17
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company:浙江鸿程 </p>
 *
 * @author SRH
 * @version 1.0
 */
public class RedisClearTask {
    public static final Logger log = Logger.getLogger(RedisClearTask.class);

    //预设每分钟执行一次
    public void clearTrafficStat() {
        log.info("clear redis sla data!");
        try {
            //删除 含当前1小时内的数据(冗余删)
            String mtime = "";
            mtime = this.addTime(Calendar.HOUR, -1, "yyyyMMddHH");
            Redis.delItem(Redis.ALL_TRAFFIC_KEY + mtime, Redis.MAIN_POOL);
            Redis.delItem(Redis.SI_TRAFFIC_KEY + mtime, Redis.MAIN_POOL);


        } catch (Exception e) {
            log.error("redis traffic_stat clear err!", e);
        }
    }

    /**
     * @FieldType int |Calendar.MINUTE Calendar.DATE Calendar.HOUR …
     */
    private String addTime(int FieldType, int incr, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(FieldType, incr);
        Date time = cal.getTime();
        DateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(time);
    }

    public static void main(String[] args) throws Exception {
        RedisClearTask obj = new RedisClearTask();
        String mtime = "";
        for (int i = 0; i <= 5; i++) {
            mtime = obj.addTime(Calendar.MINUTE, -i, "yyyyMMddHHmm");
            System.out.println(mtime);
        }
    }
}
