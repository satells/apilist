package apilist.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class JavaVersionInfoContributor implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		Map<String, String> javaInfo = new HashMap<>();
		javaInfo.put("version", System.getProperty("java.version"));
		javaInfo.put("vendor", System.getProperty("java.vendor"));
		builder.withDetail("java", javaInfo);
	}
}