package com.maua.yegestaodesaude.modules.bloodPressure.get_latest_blood_pressure.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetLatestBloodPressureUsecase {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity<Object> execute(Long clientId){
        var result = findLastItemInserted(clientId);
        if(result == null){
            return ResponseEntity.status(204).body("{\"message\": \"Sem conteúdo\"}");
        }
        return ResponseEntity.status(200).body(result);
    }

    private Object findLastItemInserted(Long clientId){
        String sql = "SELECT * FROM blood_pressure WHERE client_id = " + clientId + " ORDER BY date DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            GetLatestBloodPressureViewmodel item = new GetLatestBloodPressureViewmodel(
                rs.getLong("id"),
                rs.getDate("date"),
                rs.getString("measure"),
                rs.getString("level")
            );
            item.setId(rs.getLong("id"));
            return item;
        });
    }
}
