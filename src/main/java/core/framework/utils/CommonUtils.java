package core.framework.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CommonUtils extends Init {

	private CommonUtils() {
	}

	public static InputStream getResourceFile(String resourceName) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream resourceStream = loader.getResourceAsStream(resourceName);
		return resourceStream;
	}

	public static String getTimeStamp() {
		return getDate()+"-"+getTime();
		
	}

	public static String createDir(String directoryName) {
		// TODO Auto-generated method stub
		String folderPath = new File(directoryName).getAbsolutePath();
		File file = new File(folderPath);
		if (file.isDirectory()) {
			System.out.println("Directory " + directoryName + " already exists");
		} else {
			boolean flag = file.mkdirs();
			if (flag) {
				System.out.println("Directory" + ":::" + directoryName + " Created Successfully");
			} else {
				System.out.println("Can not Create Directory" + ":::" + directoryName);
			}
		}
		return folderPath;
	}
}