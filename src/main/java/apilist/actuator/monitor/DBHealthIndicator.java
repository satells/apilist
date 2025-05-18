package apilist.actuator.monitor;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DBHealthIndicator implements HealthIndicator {
	private final DataSource dataSource;

	public DBHealthIndicator(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Health health() {
		try (Connection connection = dataSource.getConnection()) {
			if (connection != null && connection.isValid(1)) {
				return Health.up().build();
			}
			return Health.down().withDetail("message", "Conexão com o banco de dados inválida.").build();
		} catch (SQLException e) {
			return Health.down().withDetail("message", "Erro ao conectar com o banco de dados.").build();
		}
	}

}
