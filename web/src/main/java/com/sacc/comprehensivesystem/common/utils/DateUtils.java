package com.sacc.comprehensivesystem.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间相关的工具类
 * @author yu.jinbiao
 */
public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static final DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final DateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final DateFormat simpleDateTimeMilliSecFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private static final DateFormat simpleParseDate = new SimpleDateFormat("yyyyMMdd");

    private static final DateFormat simpleParseDateTime = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final DateFormat simpleParseDateTimeMilliSecFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static final int BEGIN_YEAR = Calendar.getInstance().get(Calendar.YEAR) - 50;

    private static final int END_YEAR = Calendar.getInstance().get(Calendar.YEAR) + 50;

    /**
     * 生成当前的日期字符串
     *
     * @return 当前系统日期的字符串(格式为：yyyy-MM-dd)
     */
    public static String getCurrentDate() {
        return simpleDateFormat.format(new Date());
    }


    /**
     * 生成Date对象对应的日期字符串
     * @param date 要处理的日期字符串
     * @return 对应的日期字符串(格式为：yyyy-MM-dd)
     */
    public static String getDateStr(Date date){
        return simpleDateFormat.format(date);
    }

    /**
     * 生成当前的日期时间字符串
     *
     * @return 当前系统时间的字符串(格式为： 'yyyy-MM-dd HH:mm:ss')
     */
    public static String getCurrentDateTime() {
        return simpleDateTimeFormat.format(new Date());
    }

    /**
     * 生成当前带有毫秒的字符串
     *
     * @return 当前的时间字符串 (格式为：'yyyy-MM-dd HH:mm:ss:SSS')
     */
    public static String getCurrentDateTimeWithMilliSec() {
        return simpleDateTimeMilliSecFormat.format(new Date());
    }

    /**
     * 读取时间字符串为Java Date对象
     *
     * @param dateStr 日期时间字符串，支持的格式包括：<br />
     *                <ul>
     *                <li>yyyyMMdd</li>
     *                <li>yyyy.MM.dd</li>
     *                <li>yyyy-MM-dd</li>
     *                <li>yyyyMMddHHmmss</li>
     *                <li>yyyy-MM-dd HH:mm:ss</li>
     *                <li>yyyy.MM.dd HH:mm:ss</li>
     *                <li>yyyy.MM.dd HH.mm.ss</li>
     *                <li>yyyyMMddHHmmssSSS</li>
     *                <li>yyyy-MM-dd HH:mm:ss:SSS</li>
     *                <li>yyyy.MM.dd HH.mm.ss.SSS</li>
     *                </ul>
     * @return 字符串对应的Date对象
     * @throws ParseException 字符串解析错误
     */
    public static Date parseDate(String dateStr) throws ParseException {
        return parseDate(dateStr, BEGIN_YEAR, END_YEAR);
    }


    /**
     * 将时间字符串解析为Java Date对象
     *
     * @param dateStr 要解析的时间字符串
     * @param pattern 时间字符串格式
     * @return 字符串对应的Date对象
     * @throws ParseException 日期解析错误
     */
    public static Date parseDate(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateStr);
    }


    /**
     * 读取时间字符串为Java Date对象
     *
     * @param dateStr   日期时间字符串，支持的格式包括：<br />
     *                  <ul>
     *                  <li>yyyyMMdd</li>
     *                  <li>yyyy.MM.dd</li>
     *                  <li>yyyy-MM-dd</li>
     *                  <li>yyyyMMddHHmmss</li>
     *                  <li>yyyy-MM-dd HH:mm:ss</li>
     *                  <li>yyyy.MM.dd HH:mm:ss</li>
     *                  <li>yyyy.MM.dd HH.mm.ss</li>
     *                  <li>yyyyMMddHHmmssSSS</li>
     *                  <li>yyyy-MM-dd HH:mm:ss:SSS</li>
     *                  <li>yyyy.MM.dd HH.mm.ss.SSS</li>
     *                  </ul>
     * @param beginYear 有效的日期区间年份开始时间
     * @param endYear   有效的日期区间年份结束时间
     * @return 字符串对应的Date对象
     * @throws ParseException 解析错误
     */
    public static Date parseDate(String dateStr, int beginYear, int endYear) throws ParseException {
        String resultStr = dateStr.replace(" ", "")
                .replace(":", "")
                .replace("-", "")
                .replace("-", "")
                .replace(".", "");
        Date result = null;
        if (resultStr.length() == 17) {  // 17位，按照 yyyyMMddHHmmssSSS进行解析
            try {
                result = simpleParseDateTimeMilliSecFormat.parse(resultStr);
            } catch (ParseException e) {
                logger.info("cannot parse {} as yyyyMMddHHmmssSSS: {}\n {}", dateStr,
                        e.getMessage(), e.getStackTrace());
            }
            if (null != result && valid(result, beginYear, endYear)) {
                return result;
            } else {
                throw new ParseException("error parse: " + dateStr, 0);
            }
        } else if (resultStr.length() == 14) { // 14位按照yyyyMMddHHmmss进行解析
            try {
                result = simpleParseDateTime.parse(resultStr);
            } catch (ParseException e) {
                logger.info("cannot parse {} as yyyyMMddHHmmss: {}\n {}", dateStr,
                        e.getMessage(), e.getStackTrace());
            }
            if (null != result && valid(result, beginYear, endYear)) {
                return result;
            } else {
                throw new ParseException("error parse: " + dateStr, 0);
            }
        } else if (resultStr.length() == 8) { // 8位，按照yyyyMMdd进行解析
            try {
                result = simpleParseDate.parse(resultStr);
            } catch (ParseException e) {
                logger.info("cannot parse {} as yyyyMMdd: {}\n {}", dateStr,
                        e.getMessage(), e.getStackTrace());
            }
            if (null != result && valid(result, beginYear, endYear)) {
                return result;
            } else {
                throw new ParseException("error parse: " + dateStr, 0);
            }
        } else {
            throw new ParseException("error parse: " + dateStr, 0);
        }

    }

    /**
     * 验证时间是否在合理的日期区间内
     *
     * @param date      Date对象
     * @param beginYear 年份去年的开始年份
     * @param endYear   年份区间的结束年份
     * @return 是否在此区间内
     */
    private static boolean valid(Date date, int beginYear, int endYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        if (year < beginYear || year > endYear)
            return false;

        return true;
    }

}