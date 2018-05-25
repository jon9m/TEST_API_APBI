import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//cpInspectionDetailsApp
public class CpInspectionDetailsApp implements ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;

	public String execute() throws Exception {
		String file;
		try {
			InputStreamReader isr = null;
			BufferedReader reader = null;

			file = "C:/Users/malaka.sam/eclipse-workspace/inspector-app/JSON/cpInspectionDetailsApp.html";
			InputStream ins = new FileInputStream(file);
			HttpServletResponse servletResponse = getServletResponse();
			servletResponse.setHeader("content-type", "text/html");
			servletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			servletResponse.setHeader("Access-Control-Allow-Origin", "*");

			PrintWriter out = servletResponse.getWriter();
			
			Thread.sleep(1000);

			try {
				if (ins != null) {
					isr = new InputStreamReader(ins);
					reader = new BufferedReader(isr);
					String word = "";
					while ((word = reader.readLine()) != null) {
						out.println(word);
					}
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
				if (isr != null) {
					isr.close();
				}
				if (reader != null) {
					reader.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.response = resp;
	}

	public HttpServletResponse getServletResponse() {
		return this.response;
	}
}
