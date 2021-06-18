package com.dragontalker.blob;

import com.dragontalker.bean.Customer;
import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @Description 测试使用PreparedStatement操作Blob类型的数据
 */

public class BlobTest {

    //向数据表customers中插入Blob类型的字段
    @Test
    public void testInsert() throws Exception{
        Connection conn = JDBCUtils.getConnection();

        String sql = "insert into customers(name, email, birth, photo) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, "LiuYan");
        ps.setObject(2, "ly@email.com");
        ps.setObject(3, "1992-08-07");

        FileInputStream is = new FileInputStream(new File("C:\\Users\\richa\\Desktop\\GitHubRepos\\JDBC-study-notes\\Notes\\src\\liuyan.jpg"));
        ps.setBlob(4, is);

        ps.execute();

        JDBCUtils.closeResource(conn, ps);
    }

    //查询数据表customers中Blob类型的字段
    @Test
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth, photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 21);

            is = null;
            fos = null;

            rs = ps.executeQuery();
            if (rs.next()) {
                //方式一:
    //            int id = rs.getInt(1);
    //            String name = rs.getString(2);
    //            String email = rs.getString(3);
    //            Date birth = rs.getDate(4);

                //方式二:
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer cust = new Customer(id, name, email, birth);
                System.out.println(cust);

                //将Blob类型的字段下载下来, 以文件的方式保存在本地
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("liuyan.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
