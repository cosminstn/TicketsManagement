package com.stn.tickets.dao.engine;

import com.stn.tickets.utils.ConfUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Snippet here: https://gitlab.com/snippets/1858225
 */

@SuppressWarnings("Duplicates")
public class DbEngine {

    private String dbUrl;
    private String dbUser;
    private String dbPass;

    private static DbEngine instance;

    private DbEngine() {
        dbUrl   = ConfUtils.getInstance().getDbUrl();
        dbUser  = ConfUtils.getInstance().getDbUser();
        dbPass  = ConfUtils.getInstance().getDbPass();
    }

    public DbEngine getInstance() {
        if (instance == null)
            instance = new DbEngine();
        return instance;
    }

    /**
     * Executes an INSERT statement and returns the PK
     * @param sql The sql query
     * @return  Returns the primary key value
     * @throws Exception generated during db operations
     */
    public int insert(String sql) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            stmt = conn.createStatement();
            stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            if (!rs.next())
                throw new Exception("empty reader!");
            return rs.getInt(1);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception ignored) {}
        }
    }

    /**
     * Executes an INSERT statement and returns the PK
     * @param sql The sql query
     * @param params Prepared statement parameters
     * @return Returns the primary key value
     * @throws Exception Exception generated during db operations
     */
    public int insert(String sql, List<PreparedStatementParameter> params) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            stmt = conn.prepareStatement(sql);
            for (PreparedStatementParameter param : params)
                stmt.setObject(param.getIndex(), param.getValue(), param.getSqlType());

            stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            if (!rs.next())
                throw new Exception("empty reader!");
            return rs.getInt(1);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception ignored) {}
        }
    }

    public int update(String sql, List<PreparedStatementParameter> params) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            stmt = conn.prepareStatement(sql);
            for (PreparedStatementParameter param : params)
                stmt.setObject(param.getIndex(), param.getValue(), param.getSqlType());

            return stmt.executeUpdate();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
        }
    }

    public int update(String sql) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            stmt = conn.createStatement();

            return stmt.executeUpdate(sql);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
        }
    }

    public List<Object[]> query(String sql, List<PreparedStatementParameter> params) throws Exception {
        Connection conn 		   = null;
        PreparedStatement prepStmt = null;
        ResultSet rs 			   = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            prepStmt = conn.prepareStatement(sql);
            if (params == null || params.size() <= 0)
                throw new Exception("Empty list of params!");

            for (PreparedStatementParameter param : params)
                prepStmt.setObject(param.getIndex(), param.getValue(), param.getSqlType());

            rs = prepStmt.executeQuery();

            List<Object[]> records = new ArrayList<Object[]>();
            while (rs.next()) {
                int cols = rs.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 1; i <= cols; i++)
                    arr[i - 1] = rs.getObject(i);
                records.add(arr);
            }

            return records;
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception ignored) {}
            try {
                if (prepStmt != null)
                    prepStmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
        }
    }

    public List<Object[]> query(String sql) throws Exception {
        Connection conn 		   = null;
        Statement stmt = null;
        ResultSet rs 			   = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            List<Object[]> records = new ArrayList<Object[]>();
            while (rs.next()) {
                int cols = rs.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 1; i <= cols; i++)
                    arr[i - 1] = rs.getObject(i);
                records.add(arr);
            }

            return records;
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception ignored) {}
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
        }
    }

    public Object executeScalar(String sql, List<PreparedStatementParameter> params) throws Exception {
        return query(sql, params).get(0)[0];
    }

    public Object executeScalar(String sql) throws Exception {
        return query(sql).get(0)[0];
    }

    public boolean execute(String sql, List<PreparedStatementParameter> params) throws Exception {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            prepStmt = conn.prepareStatement(sql);
            for (PreparedStatementParameter param : params)
                prepStmt.setObject(param.getIndex(), param.getValue(), param.getSqlType());

            return prepStmt.execute();
        } finally {
            try {
                if (prepStmt != null)
                    prepStmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
        }
    }

}
