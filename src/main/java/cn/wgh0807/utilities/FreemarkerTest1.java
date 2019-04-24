package cn.wgh0807.utilities;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.wgh0807.utilities.SmallUtil.firstCharToUpper;
import static cn.wgh0807.utilities.SmallUtil.firstCharToLower;

/**
 * 文件路径组成为：
 * -src/main
 * --java
 * ---{basePackage}
 * ----{daoMidPackage}
 * -----{daoOutPutPackage}
 * ------XXXXDao.java
 * ----{modelMidPackage}
 * -----{modelOutputPackage}
 * ------XXXType.java
 * --resources
 * ---freemarker.code
 * ----XXX.ftl
 */
public class FreemarkerTest1 {
//    自用常量
    private final static String developer = "Wgh0807";
    private final static String dbName = "db_a";
    private final static String basePackage = "cn.wgh0807";
    private final static String daoMidPackage = "dao";
    private final static String daoOutputPackage = "center.dao";
    private final static String modelMidPackage = "model";
    private final static String[] modelOutputPackage = new String[]{"model","param","vo"};

    private final static String modelFtl = "model.ftl";
    private final static String daoFtl = "dao.ftl";

//    文件路径
    private final static String javaPath = "src/main/java/";
    private final static String ftlPath = "src/main/resources/freemarker/code/";

//    格式配置
    private final static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

    //    freemarker Configuration
    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
    static {
        try {
            cfg.setDirectoryForTemplateLoading(new File(ftlPath));
            cfg.setDefaultEncoding("utf-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        generic();
    }


    public static void generic()  {
        try {
            List<TableObject> tableObjectList = GetSqlModel.getAllObjectInDB(dbName);
            for (TableObject t : tableObjectList) {
                genericModel(t);
                genericDao(t);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void writeWithFtlFile(String ftlFileName, Map map, Writer out)  {
        try {
            Template template = cfg.getTemplate(ftlFileName);
            Map root = getRootMap();
            root.putAll(map);
            if(null==out){
                out = new OutputStreamWriter(System.out);
            }
            template.process(root, out);
            System.out.println("模版生成成功");
        } catch (Exception e) {
            System.err.println("模版生成失败"+e.getMessage());
        }
    }


    public static void genericModel(TableObject t) throws IOException {
        List<String> desFilePath = new LinkedList<>();
        Map<String,Object> map = new HashMap<>();
        TableObject tempTable = new TableObject(t);

        for (String s:modelOutputPackage) {
            tempTable.setClassName(t.getClassName() + ("model".equalsIgnoreCase(s)?"":firstCharToUpper(s)));
            String desFile = javaPath + basePackage.replace(".","/") + "/"
                    + modelMidPackage.replace(".","/")
                    +"/"+s.replace('.','/')+"/"+tempTable.getClassName()+".java";



            map.put("package", basePackage);
            map.put("midPackage", modelMidPackage);
            map.put("lastPackage", s);
            map.put("tableObject", tempTable);

            writeWithFtlFile(modelFtl,map,new FileWriter(desFile));
        }
    }

    public static void genericDao(TableObject t) throws IOException {
        List<String> desFilePath = new LinkedList<>();
        Map<String,Object> map = new HashMap<>();
        TableObject tempTable = new TableObject(t);

        tempTable.setClassName(t.getClassName() + firstCharToUpper("dao"));
        String desFile = javaPath + basePackage.replace(".","/") + "/"
                + daoMidPackage.replace(".","/")
                +"/"+daoOutputPackage.replace('.','/')+"/"+tempTable.getClassName()+".java";

        map.put("package", basePackage);
        map.put("midPackage", daoMidPackage);
        map.put("detailPackage", daoOutputPackage);
        map.put("modelMidPackage",modelMidPackage);


//        List<String> temlist = Arrays.asList(modelOutputPackage);
//        for (int i = 0; i < temlist.size(); i++) {
//            temlist.set(i, firstCharToUpper(temlist.get(i)));
//        }
        map.put("modelDetailPackage",modelOutputPackage );
        map.put("developer", developer);
        map.put("current_date", sdf.format(new Date()));

        map.put("tableObject", t);
        map.put("urlClass", firstCharToLower(t.getClassName()));



        writeWithFtlFile(daoFtl,map,new FileWriter(desFile));

    }


    public static void modelTest(){
        try {
            Template template = cfg.getTemplate("model.ftl");
            Map<String,Object> root = new HashMap();
            root.put("package", basePackage);
            root.put("developer", developer);
            root.put("current_date", sdf.format(new Date()));
            root.put("midPackage", "model");
            root.put("lastPackage", "model");


            List<TableObject> list = GetSqlModel.getAllObjectInDB(dbName);
            root.put("tableObject", list.get(0));
            root.put("tableObjList", list.get(0).getcolumnObjects());
            Writer writer = new OutputStreamWriter(System.out);
            template.process(root, writer);
        } catch (Exception e) {
            System.err.println("模版生成失败");
        }
    }

    public static void daoTest(){
        try {
            Template template = cfg.getTemplate("dao.ftl");
            Map root = new HashMap();

            root.put("package", basePackage);
            root.put("detailPackage", "model.model");
//            root.put("detailPackage", "model.param");
//            root.put("detailPackage", "model.vo");
            root.put("daoPackage", "dao");
            root.put("modelPackage", "model.model");
            root.put("paramPackage", "model.param");
            root.put("developer", developer);
            root.put("current_date", sdf.format(new Date()));

            List<TableObject> list = GetSqlModel.getAllObjectInDB(dbName);
            root.put("tableObject", list.get(0));
            root.put("urlClass", firstCharToLower(list.get(0).getClassName()));
            Writer writer = new OutputStreamWriter(System.out);
            template.process(root, writer);
            System.out.println("模版生成成功");
        } catch (Exception e) {
            System.err.println("模版生成失败");
        }
    }


    private static Map getRootMap() {
        Map root = new HashMap();
        root.put("package", basePackage);
        root.put("developer", developer);
        root.put("current_date", sdf.format(new Date()));

        return root;
    }


}
