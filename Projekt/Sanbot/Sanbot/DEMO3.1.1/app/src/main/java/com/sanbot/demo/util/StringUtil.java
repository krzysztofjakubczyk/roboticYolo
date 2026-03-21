package com.sanbot.demo.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    protected static final String PHONE_REGEX = "^1[34578]\\d{9}$";
    protected static final String EMAIL_REGEX = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    protected static final String LETTER[] = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    protected static final String TEXT_REGEX = "[0-9A-Za-z\\u4E00-\\u9FBF]";

    /**
     * 关键字为红色的字符串， 配合Html.fromHtml()使用
     *
     * @param text    文本
     * @param keyword 关键字
     * @return 返回全新文本
     */
    public static String htmlRedKeyword(String text, String keyword) {
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(keyword)) {
            return text;
        }
        String htmlKeyword = "<font color=\"red\">" + keyword + "</font>";
        return text.replaceAll(matterSpecialText(keyword), matterSpecialText(htmlKeyword));
    }

    public static String htmlGreenKeyword(String text, String keyword) {
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(keyword)) {
            return text;
        }
        String htmlKeyword = "<font color=\"#4CC25C\">" + keyword + "</font>";
        return text.replaceAll(matterSpecialText(keyword), matterSpecialText(htmlKeyword));
    }


    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByREG(String text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(text).find();

    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean checkText(String text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        Pattern pattern = Pattern.compile(TEXT_REGEX);
        return pattern.matcher(text).find();
    }

    /**
     * 特殊字符处理
     */
    private static String matterSpecialText(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        return text.replaceAll("\\*", "\\\\\\*");
    }

    /**
     * @param theString s
     * @return String
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * utf-8 转unicode
     *
     * @param inStr
     * @return String
     */
    public static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(myBuffer[i]);
            if (ub == Character.UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }


    public static boolean checkPhone(String phone) {
        if (phone.startsWith("+"))  return true;

        Pattern p = Pattern.compile(PHONE_REGEX);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean checkEmail(String mobiles) {
        Pattern p = Pattern.compile(EMAIL_REGEX);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static void integerToString(StringBuilder builder, int num) {
        if (num < 0 || builder == null) {
            return;
        }
        int i = num / LETTER.length;
        if (i > 0) {
            integerToString(builder, i - 1);
        }
        builder.append(LETTER[(num) % LETTER.length]);
    }

    /**
     * 半角转换为全角
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("，", ",")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * json解析的空字符串转可识别的字符串
     */
    public static String toString(String text){
        return text == null || "null".equals(text) ? "" : text;
    }


}
