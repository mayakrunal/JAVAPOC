/*
 *
 */
package com.jdbctest.jdbctest;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

/** Hello world! */
@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(final String args[]) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Override
    public void run(final String... args) throws Exception {
        // Assumes connection is an active Connection object.

        // Create an in-memory data table.
        final SQLServerDataTable sourceDataTable = new SQLServerDataTable();

        // Define metadata for the data table.
        sourceDataTable.addColumnMetadata("CategoryID", java.sql.Types.INTEGER);
        sourceDataTable.addColumnMetadata("CategoryName", java.sql.Types.NVARCHAR);

        // Populate the data table.
        sourceDataTable.addRow(15, "ksefglkafdn");
        sourceDataTable.addRow(16, "ieroiajenfdsgfk");
        sourceDataTable.addRow(8, "ieroiajsfdgenfk");
        sourceDataTable.addRow(9, "ieroiajenfdsgfk");
        sourceDataTable.addRow(10, "ieroiajenfk");
        sourceDataTable.addRow(11, "ieroiajfdsgenfk");
        sourceDataTable.addRow(12, "ierfdsgoiajenfk");
        sourceDataTable.addRow(13, "fdg");
        sourceDataTable.addRow(14, "ieroiajenfk");

        // Pass the data table as a table-valued parameter using a prepared
        // statement.

        final Connection connection = dataSource.getConnection();

        if (connection.isWrapperFor(SQLServerConnection.class)) {
            final SQLServerConnection sqlconnection = connection.unwrap(SQLServerConnection.class);
            final PreparedStatement pStmt = sqlconnection.prepareStatement("INSERT INTO dbo.Categories SELECT * FROM ?;");

            final SQLServerPreparedStatement sqlStatement = (SQLServerPreparedStatement) pStmt;
            sqlStatement.setStructured(1, "dbo.CategoryTableType", sourceDataTable);
            sqlStatement.execute();

        }

    }
}
