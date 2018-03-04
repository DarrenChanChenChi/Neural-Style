package cn.ybz21.hackthon.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	public void Response(HttpServletResponse response, String data) {
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.write(data);
		writer.flush();
		writer.close();
	}
}
