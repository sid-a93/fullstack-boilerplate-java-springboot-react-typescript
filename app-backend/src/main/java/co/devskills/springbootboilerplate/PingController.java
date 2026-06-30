package co.devskills.springbootboilerplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private static final String HARD_CODED_API_KEY = "sk-dev-1234567890";

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck(){
        return "pong";
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public String search(@RequestParam String name) {
        String query = "SELECT * FROM users WHERE name = '" + name + "'";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlitestorage.db")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return "User found: " + resultSet.getString("name");
            }
            return "No user found";
        } catch (Exception ex) {
            return "Query failed: " + ex.getMessage();
        }
    }

    @GetMapping(value = "/exec")
    @ResponseStatus(HttpStatus.OK)
    public String execCommand(@RequestParam String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            return "Command started with pid " + process.pid();
        } catch (Exception ex) {
            return "Execution failed: " + ex.getMessage();
        }
    }

    @GetMapping(value = "/secret")
    @ResponseStatus(HttpStatus.OK)
    public String getSecret() {
        return "The hard-coded secret is " + HARD_CODED_API_KEY;
    }
}
