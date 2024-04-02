package com.maua.yegestaodesaude.modules.imc.get_latest_imc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetLatestImcUsecase {
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
        String sql = "SELECT * FROM imc WHERE client_id = " + clientId + " ORDER BY date DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            GetLatestImcViewmodel item = new GetLatestImcViewmodel(
                rs.getLong("id"),
                rs.getDouble("weight"),
                rs.getDouble("height"),
                rs.getDouble("imc"),
                rs.getString("level"),
                rs.getString("date")
            );
            item.setId(rs.getLong("id"));
            return item;
        });
    }
}
