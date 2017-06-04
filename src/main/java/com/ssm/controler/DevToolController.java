package com.ssm.controler;

import com.render.kit.DBHelper;
import com.render.kit.StrKit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yg on 2017/4/28.
 */
public class DevToolController implements Controller{

    private StringBuilder content = new StringBuilder();
    private StringBuilder headerImport = new StringBuilder();

    private String parseClassName(String tableName) {
        if(StrKit.isBlank(tableName))
            return "";
        char[] chars = tableName.toCharArray();
        int where = 0;
        for(int i=0; i<chars.length; i++) {
            if(i == 0) {
                chars[i] -= 32;
            } else {
                if(chars[i] == '_') {
                    where = i+1;
                    chars[where] -= 32;
                }
            }
        }

        return String.valueOf(chars).replaceAll("_", "");

    }

    private String parsePropertiesName(String fieldName) {
        return parsePropertiesName(fieldName, true);
    }

    private String parsePropertiesName(String fieldName, boolean upperFirstChar) {
        if(StrKit.isBlank(fieldName))
            return "";
        char[] chars = fieldName.toCharArray();
        int where = 0;
        for(int i=0; i<chars.length; i++) {
            if(i == 0 && upperFirstChar) {
                chars[i] -= 32;
            } else {
                if(chars[i] == '_') {
                    where = i+1;
                    chars[where] -= 32;
                }
            }
        }

        return String.valueOf(chars).replaceAll("_", "");
    }

    private String getType(String typeInDbs, boolean isFunction) {
        if(StrKit.isBlank(typeInDbs)) {
            return "unknown";
        }
        if(typeInDbs.toLowerCase().contains("bigint")) {
            return "Long";
        }
        if(typeInDbs.toLowerCase().contains("tinyint")) {
            return "Boolean";
        }
        if(typeInDbs.toLowerCase().contains("int")) {
            if(isFunction) {
                return "Int";
            }
            return  "Integer";
        }
        if(typeInDbs.toLowerCase().contains("double")) {
            return "Double";
        }
        if(typeInDbs.toLowerCase().contains("float")) {
            return "Float";
        }
        if(typeInDbs.toLowerCase().contains("bit")) {
            return "Boolean";
        }
        if(typeInDbs.toLowerCase().contains("char") || typeInDbs.toLowerCase().contains("text")) {
            if(isFunction) {
                return "Str";
            }
            return "String";
        }
        if(typeInDbs.toLowerCase().contains("datetime")) {
            if (headerImport.indexOf("import java.util.Date") == -1) {
                headerImport.append("import java.util.Date;\r\n\r\n");
            }
            return "Date";
        }
        if(typeInDbs.toLowerCase().contains("date")) {
            return "Date";
        }
        if(typeInDbs.toLowerCase().contains("enum")) {
            if (isFunction) {
                return "Str";
            }
            return "String";
        }

        return "unknown";
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView();

        ResultSet rs;
        String sql;
        String tableName = request.getParameter("tn");
        try {

            DBHelper dbhelper = new DBHelper();
            Connection conn = dbhelper.getConnInstance();
            Statement stm = conn.createStatement();
            if (StrKit.notBlank(tableName)) {

                System.out.print("------------");
                String colName = "";
                String colComment = "";
                String type = "";
                String tableComment = "";
                String className = parseClassName(tableName);

                headerImport.append("import com.jfinal.ext.plugin.tablebind.TableBind;\r\n");
                headerImport.append("import com.jfinal.plugin.activerecord.Model;\r\n\r\n");

                sql = "show TABLE status LIKE '" + tableName + "'";
                rs = stm.executeQuery(sql);
                if(rs.next()) {
                    tableComment = rs.getString("comment");
                    content.append("/**\r\n");
                    content.append(" * ").append(tableComment).append("\r\n");
                    content.append(" */\r\n");
                    content.append("@TableBind(tableName = \"" + tableName +"\")\r\n");
                    content.append("public class ").append(className).append(" extends ").append("Model").append("<").append(className).append("> {");
                    content.append("\r\n\r\n");
                    content.append("    private static final long serialVersionUID = 1L;");
                    content.append("\r\n\r\n");
                    content.append("    public static final ").append(className).append(" dao = new ").append(className).append("();");
                    content.append("\r\n\r\n");
                }

                sql = "show FULL columns FROM `" + tableName + "`";

                rs = stm.executeQuery(sql);

                while (rs.next()) {
                    colName = rs.getString("field");
                    colComment = rs.getString("comment");
                    type = rs.getString("type");
                    content.append("    /**\r\n");
                    content.append("     * ").append(colComment).append("  ").append(type).append("\r\n");
                    content.append("     */\r\n");
                    content.append("    public " + getType(type, false) + " get" + parsePropertiesName(colName));

                    content.append("() {");
                    content.append("\n");
                    content.append("        return get" + getType(type, true) + "(\""+ colName  +"\");");
                    content.append("\r\n    }");
                    content.append("\r\n\r\n");

                    content.append("    public void set" + parsePropertiesName(colName));
                    content.append("("+ getType(type, false) + " " + parsePropertiesName(colName, false) +")");
                    content.append(" {");
                    content.append("\n");
                    content.append("        set(\""+ colName  +"\", "+parsePropertiesName(colName, false)+");");
                    content.append("\r\n    }");
                    content.append("\r\n\r\n");
                }
                content.append("}");

                content = headerImport.append(content);
                mv.addObject("codeStr", content.toString());
            }
            List<String> tableNames = new ArrayList<String>();
            sql = "show tables";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
            mv.addObject("tableNames", tableNames);
            System.out.print("tableNames："+tableNames);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mv.setViewName("dev");
        return mv;
    }
}
