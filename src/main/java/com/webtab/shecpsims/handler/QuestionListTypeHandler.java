package com.webtab.shecpsims.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtab.shecpsims.model.entity.user.Question;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionListTypeHandler extends BaseTypeHandler<List<Question>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Question> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, java.sql.Types.VARCHAR);
        } else {
            try {
                // 将 List<Question> 转换为 JSON 字符串
                String json = objectMapper.writeValueAsString(parameter);
                ps.setString(i, json);
            } catch (Exception e) {
                throw new SQLException("Error converting List<Question> to JSON", e);
            }
        }
    }

    @Override
    public List<Question> getResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getString(columnName));
    }

    @Override
    public List<Question> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(rs.getString(columnIndex));
    }

    @Override
    public List<Question> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Question> parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public List<Question> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return List.of();
    }

    @Override
    public List<Question> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return List.of();
    }

    @Override
    public List<Question> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return List.of();
    }

    private List<Question> convert(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            // 从 JSON 字符串转换为 List<Question>
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to List<Question>", e);
        }
    }
}