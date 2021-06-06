package com.example.touragency.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {


    T extractFromResultSet(ResultSet rs) throws SQLException;
}
