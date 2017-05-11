package org.yh.yhframe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);

        Student student1 = new Student();
        student1.setId(1);
        student1.setName("李坤");
        student1.setBirthDay(new Date());

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("曹贵生");
        student2.setBirthDay(new Date());

        Student student3 = new Student();
        student3.setId(3);
        student3.setName("柳波");
        student3.setBirthDay(new Date());

        List<Student> stulist = new ArrayList<Student>();
        stulist.add(student1);
        stulist.add(student2);
        stulist.add(student3);

        Teacher teacher1 = new Teacher();
        teacher1.setId(1);
        teacher1.setName("米老师");
        teacher1.setTitle("教授");

        Teacher teacher2 = new Teacher();
        teacher2.setId(2);
        teacher2.setName("丁老师");
        teacher2.setTitle("讲师");
        List<Teacher> teacherList = new ArrayList<Teacher>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("students", stulist);
        map.put("teachers", teacherList);

        Gson gson = new Gson();
        String s = gson.toJson(map);
        System.out.println(s);


        System.out.println("----------------------------------");

        String t = "{\"result\":0,\"students\":[{\"id\":1,\"name\":\"李坤\",\"birthDay\":\"May 11, 2017 1:33:55 PM\"},{\"id\":2,\"name\":\"曹贵生\",\"birthDay\":\"May 11, 2017 1:33:55 PM\"},{\"id\":3,\"name\":\"柳波\",\"birthDay\":\"May 11, 2017 1:33:55 PM\"}],\"teachers\":[{\"id\":1,\"name\":\"米老师\",\"title\":\"教授\"},{\"id\":2,\"name\":\"丁老师\",\"title\":\"讲师\"}]}";

        Map<String, Object> retMap = gson.fromJson(t,
                new TypeToken<Map<String, Object>>()
                {
                }.getType());

        for (String key : retMap.keySet())
        {
            System.out.println("key:" + key + " values:" + retMap.get(key));

            if (key.equals("students"))
            {
                List<Student> stuList = (List<Student>) retMap.get(key);
                System.out.println(stuList);
            } else if (key.equals("teachers"))
            {
                List<Teacher> tchrList = (List<Teacher>) retMap.get(key);
                System.out.println(tchrList);
            }
        }


    }

    @Test
    public void b() throws Exception
    {
        Gson gson = new Gson();
        String x = " [\n" +
                "    {\n" +
                "        \"tableName\": \"students\",\n" +
                "        \"tableData\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"李坤\",\n" +
                "                \"birthDay\": \"Jun 22, 2012 9:54:49 PM\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 2,\n" +
                "                \"name\": \"曹贵生\",\n" +
                "                \"birthDay\": \"Jun 22, 2012 9:54:49 PM\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 3,\n" +
                "                \"name\": \"柳波\",\n" +
                "                \"birthDay\": \"Jun 22, 2012 9:54:49 PM\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"tableName\": \"teachers\",\n" +
                "        \"tableData\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"米老师\",\n" +
                "                \"title\": \"教授\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 2,\n" +
                "                \"name\": \"丁老师\",\n" +
                "                \"title\": \"讲师\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        // 将json转为数据-->start
        List<Table> tableDatas2 = gson.fromJson(x,
                new TypeToken<List<Table>>()
                {
                }.getType());
        System.out.println(tableDatas2);

        for (int i = 0; i < tableDatas2.size(); i++)
        {
            Table entityData = tableDatas2.get(i);
            String tableName = entityData.getName();
            List tableData = entityData.getDataList();
            String s2 = gson.toJson(tableData);
            System.out.println(tableName);
            //System.out.println(entityData.getData());
            if (tableName.equals("students"))
            {
                System.out.println("students");
                List<Student> retStuList = gson.fromJson(s2,
                        new TypeToken<List<Student>>()
                        {
                        }.getType());
                for (int j = 0; j < retStuList.size(); j++)
                {
                    System.out.println(retStuList.get(j));
                }

            } else if (tableName.equals("teachers"))
            {
                System.out.println("teachers");
                List<Teacher> retTchrList = gson.fromJson(s2,
                        new TypeToken<List<Teacher>>()
                        {
                        }.getType());
                for (int j = 0; j < retTchrList.size(); j++)
                {
                    System.out.println(retTchrList.get(j));
                }
            }
        }
        // Json转为对象-->end
    }
}