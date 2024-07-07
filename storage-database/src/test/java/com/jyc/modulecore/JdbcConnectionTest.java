package com.jyc.modulecore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ModuleCoreApplicationTests.class)
@ActiveProfiles("local")
public class JdbcConnectionTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcConnection() {
        //given
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test_table (id INT, name VARCHAR(255))");
        jdbcTemplate.update("INSERT INTO test_table VALUES (1, 'Kim')");

        //when
        String result = jdbcTemplate.queryForObject("SELECT name FROM test_table WHERE id = 1", String.class);

        //then
        assertTrue("Kim".equals(result));
    }
}