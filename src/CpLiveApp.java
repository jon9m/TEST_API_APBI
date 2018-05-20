import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class CpLiveApp implements ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;

	public String execute() throws Exception {
		HttpServletResponse servletResponse = getServletResponse();
		servletResponse.setHeader("content-type", "application/json");
		servletResponse.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = servletResponse.getWriter();

		try {
			out.println("{\"a\":\"true\"}");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
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

	public HttpServletResponse getServletResponse() {
		return this.response;
	}

	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.response = resp;
	}
}