package com.fireduptech.spring.rest.jwt.athlete.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.fireduptech.spring.rest.jwt.athlete.domain.AthleteAccount;


@Repository
public class AthleteAccountDaoImpl implements AthleteAccountDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public AthleteAccount getAthleteAccount( int athleteAccountId ) {

        final String sql = "SELECT * FROM athlete_account WHERE id = :athleteAccountId";

        SqlParameterSource namedParameters = new MapSqlParameterSource( "athleteAccountId", athleteAccountId );

        return namedParameterJdbcTemplate.query(sql, namedParameters,
                new ResultSetExtractor<AthleteAccount>() {

                    AthleteAccount acd = new AthleteAccount();

                    @Override
                    public AthleteAccount extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        while ( resultSet.next() ) {
                            acd.setId( Integer.parseInt( resultSet.getString( "id" ) ) );
                            acd.setAccountType( resultSet.getString( "first_name" ) );
                            acd.setFirstName( resultSet.getString( "first_name" ) );
                            acd.setLastName( resultSet.getString( "last_name" ) );
                            acd.setEmail( resultSet.getString( "email" ) );
                            acd.setPrimaryActivity( resultSet.getString( "primary_activity" ) );
                        }
                        return acd;
                    }
                });
    }


}