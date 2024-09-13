package core.framework.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Configuration {

	private Configuration() {
	}

	private static final Properties prop = new Properties();

	private static void load(InputStream stream) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
			prop.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}

	public static String get(String propertyKey) {
		String value = null;
		try {
			load(CommonUtils.getResourceFile("config.properties"));
			value=prop.getProperty(propertyKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			prop.clear();
		}
		return value;
	}
	
	public static long timeout() {
		String value=get("timeout");
		if(value.trim().equals("")) {
			return 1L;
		}
		return Long.parseLong(value);
	}
}