package com.maua.yegestaodesaude.modules.glucose.get_latest_glucose.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetLatestGlucoseUsecase {

    @Autowired
    private DateUtils dateUtils;

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
        String sql = "SELECT * FROM glucose WHERE client_id = " + clientId + " ORDER BY date DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            GetLatestGlucoseViewmodel item = new GetLatestGlucoseViewmodel(
                rs.getLong("id"),
                dateUtils.RevertDate(rs.getDate("date")),
                rs.getString("measure"),
                rs.getString("level")
            );
            item.setId(rs.getLong("id"));
            return item;
        });
    }
}
