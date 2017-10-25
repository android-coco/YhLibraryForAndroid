package org.yh.library;

import com.google.gson.annotations.SerializedName;

import org.junit.Test;
import org.yh.library.utils.JsonUitl;

import java.util.List;

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
        String jsonData = "{\"result\":0,\"datas\":[{\"id\":2,\"week\":\"1234\",\"start_hour\":7," +
                "\"start_minute\":15,\"end_hour\":12,\"end_minute\":20,\"times\":5}]}";
        Student students = JsonUitl.stringToTObject(jsonData,
                Student.class);
        System.out.println(students);
    }

    class Student
    {
        @SerializedName("result")
        protected String resultCode;//结果Code
        @SerializedName("datas")
        private List<Grade> grade; // 因为grade是个数组，所以要定义成List

        public class Grade
        {
            @SerializedName("id")
            private String id;//数据ID
            @SerializedName("week")
            private String week;//星期数据，周一为1， 周日为7，最多7天共存(1-7)
            @SerializedName("start_hour")
            private String startHour;//开始时间的小时
            @SerializedName("start_minute")
            private String startMinute;// 开始时间的分钟
            @SerializedName("end_hour")
            private String endHour;// 结束时间的小时
            @SerializedName("end_minute")
            private String endMinute; //结束时间的分钟
            @SerializedName("time")
            private String time;//定位上报的周期，秒，默认300（5分钟）

            public String getId()
            {
                return id;
            }

            public void setId(String id)
            {
                this.id = id;
            }

            public String getWeek()
            {
                return week;
            }

            public void setWeek(String week)
            {
                this.week = week;
            }

            public String getStartHour()
            {
                return startHour;
            }

            public void setStartHour(String startHour)
            {
                this.startHour = startHour;
            }

            public String getStartMinute()
            {
                return startMinute;
            }

            public void setStartMinute(String startMinute)
            {
                this.startMinute = startMinute;
            }

            public String getEndHour()
            {
                return endHour;
            }

            public void setEndHour(String endHour)
            {
                this.endHour = endHour;
            }

            public String getEndMinute()
            {
                return endMinute;
            }

            public void setEndMinute(String endMinute)
            {
                this.endMinute = endMinute;
            }

            public String getTime()
            {
                return time;
            }

            public void setTime(String time)
            {
                this.time = time;
            }

            @Override
            public String toString()
            {
                return "DwSdModel{" +
                        "id='" + id + '\'' +
                        ", week='" + week + '\'' +
                        ", startHour='" + startHour + '\'' +
                        ", startMinute='" + startMinute + '\'' +
                        ", endHour='" + endHour + '\'' +
                        ", endMinute='" + endMinute + '\'' +
                        ", time='" + time + '\'' +
                        '}';
            }

        }

        public String getResultCode()
        {
            return resultCode;
        }

        public void setResultCode(String resultCode)
        {
            this.resultCode = resultCode;
        }

        public List<Grade> getGrade()
        {
            return grade;
        }

        public void setGrade(List<Grade> grade)
        {
            this.grade = grade;
        }

        @Override
        public String toString()
        {
            return "Student{" +
                    "resultCode='" + resultCode + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }
}