import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//cpLoadFormDataAppv2
public class CpAddReportv2 implements ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;

	public String execute() throws Exception {
		HttpServletRequest request = getServletRequest();
		String file;
		FileOutputStream fop = null;
		try {
			
			HttpServletResponse servletResponse = getServletResponse();
			servletResponse.setHeader("content-type", "application/json");
			servletResponse.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Cache-Control");
			servletResponse.setHeader("Access-Control-Allow-Origin", "*");

			file = "C:/Users/malaka.sam/eclipse-workspace/inspector-app/JSON/cpLoadFormDataAppv2.json";
			fop = new FileOutputStream(file);

			String line;
			BufferedReader rd = new BufferedReader(request.getReader());
			while ((line = rd.readLine()) != null) {
				fop.write(line.getBytes());
			}
			rd.close();
			fop.flush();
			fop.close();
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
