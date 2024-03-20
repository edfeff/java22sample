import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jep459_E4 {
    record QueryBuilder(Connection conn) implements StringTemplate.Processor<PreparedStatement, SQLException> {
        public PreparedStatement process(StringTemplate st) throws SQLException {
            //1. 使用 ? 占位符连接sql片段
            String query = String.join("?", st.fragments());
            //2. 构造PreparedStatement对象，防止sql注入问题
            PreparedStatement ps = conn.prepareStatement(query);
            //3. 按照类型填充参数
            int index = 1;
            for (Object value : st.values()) {
                switch (value) {
                    case Integer i -> ps.setInt(index++, i);
                    case Float f -> ps.setFloat(index++, f);
                    case Double d -> ps.setDouble(index++, d);
                    case Boolean b -> ps.setBoolean(index++, b);
                    default -> ps.setString(index++, String.valueOf(value));
                }
            }
            //4. 返回PreparedStatement对象
            return ps;
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = null;//todo
        var DB = new QueryBuilder(conn);
        String name = "wppcafe";
        PreparedStatement ps = DB."SELECT * FROM Person p WHERE p.last_name = \{name}";
        ResultSet rs = ps.executeQuery();
    }
}
