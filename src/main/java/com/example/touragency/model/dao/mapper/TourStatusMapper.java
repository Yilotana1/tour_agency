package com.example.touragency.model.dao.mapper;

import com.example.touragency.constants.Fields;
import com.example.touragency.model.entity.TourStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourStatusMapper implements EntityMapper<TourStatus> {



    @Override
    public TourStatus extractFromResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt(Fields.TOUR_STATUS_ID);

        if (id == TourStatus.BURNING.getId()){
            return TourStatus.BURNING;
        }

        if (id == TourStatus.NON_BURNING.getId()){
            return TourStatus.NON_BURNING;
        }

        return null;
    }
}
