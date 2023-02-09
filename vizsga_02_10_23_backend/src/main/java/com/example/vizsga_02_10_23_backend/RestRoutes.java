package com.example.vizsga_02_10_23_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class RestRoutes {

    @GetMapping("/isServerUp")
    public boolean isServerUp() {
        return true;
    }

    @GetMapping("/getNapelemekList")
    public ArrayList<HashMap<String, String>> getNapelemekList() {

        ArrayList< HashMap<String, String> > napelemekObject = new ArrayList<>();

        String connectionUrl = "jdbc:mysql://localhost:3306/myexam?serverTimezone=UTC";

        try {
            DbConnect myDbConnection = new DbConnect(connectionUrl, "root", "");
            ResultSet queryResult = myDbConnection.sendQuery("SELECT * FROM `napelemek`");

            ResultSetMetaData queryMeta = queryResult.getMetaData();

            int columnCount = queryMeta.getColumnCount();
            ArrayList<String> columnNames = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add( queryMeta.getColumnName(i) );
            }

            while( queryResult.next() ) {

                HashMap<String, String> rowData = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(columnNames.get(i - 1), queryResult.getString(i));
                }

                napelemekObject.add(rowData);
            }

        } catch (SQLException e) {
            // throw new RuntimeException(e);
            System.out.println("SQL Exception...");
        }

        return napelemekObject;
    }
}
