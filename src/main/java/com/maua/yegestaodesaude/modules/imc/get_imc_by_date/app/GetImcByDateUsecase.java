package com.maua.yegestaodesaude.modules.imc.get_imc_by_date.app;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetImcByDateUsecase {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public ResponseEntity<Object> execute(Long clientId, String date){
        Date sqlDate = Date.valueOf(date);
        var result = findLastItemInserted(clientId, sqlDate);
        if(result == null){
            return ResponseEntity.status(204).body("{\"message\": \"Sem conteúdo\"}");
        }
        return ResponseEntity.status(200).body(result);
    }

    private Object findLastItemInserted(Long clientId, Date date){
        String sql = "SELECT * FROM imc WHERE client_id = " + clientId +" AND date = '" + date + "'";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            GetImcByDateViewmodel item = new GetImcByDateViewmodel(
                rs.getLong("id"),
                rs.getDouble("weight"),
                rs.getDouble("height"),
                rs.getString("imc"),
                rs.getString("level"),
                rs.getString("date")
            );
            item.setId(rs.getLong("id"));
            return item;
        });
    }
}
