package cn.wgh0807.utilities;

public class SmallUtil {
    /**
     * 以大驼峰规则格式化字符串
     * 将'aBc_dEF'转换为 "AbcDef"
     * @param s 待操作的字符串
     * @return 大驼峰命名后的字符串
     */
    public static String classNameFormat(String s){
        String[] slist = s.split("_");
        StringBuilder res = new StringBuilder();
        for (String s1:slist) {
            res.append(firstCharToUpper(s1));
        }
        return res.toString();
    }

    /**
     * 小驼峰规范命名
     * 将'aBc_dEF'转换为 "abcDef"
     * @param s 待操作的字符串
     * @return 小驼峰命名后的字符串
     */
    public static String columnNameFormat(String s){
        String[] slist = s.split("_");
        StringBuilder res = new StringBuilder();
        res.append(firstCharToLower(slist[0]));
        for (int i = 1; i < slist.length; i++) {
            res.append(firstCharToUpper(slist[i]));
        }
        return res.toString();
    }

    /**
     * 将首字母变大，不操作其他部分
     * @param s 待操作的字符串
     * @return 首字母大写的操作后的字符串
     */
    public static String firstCharToUpper(String s){
//        s = s.toLowerCase();
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        return s;
    }

    /**
     * 将首字母变小,不管其他
     * @param s 需要被操作的字符串
     * @return 首字母变小后的字符串
     */
    public static String firstCharToLower(String s){
        s = s.substring(0, 1).toLowerCase() + s.substring(1);
        return s;
    }
}
