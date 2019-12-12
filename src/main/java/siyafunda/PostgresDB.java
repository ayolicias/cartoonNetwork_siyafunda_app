package siyafunda;

import java.sql.Connection;

public class PostgresDB {

    private final Connection connection;

    public PostgresDB(Connection connection){
        this.connection = connection;
    }


}
