import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//cpUploadImageApp
public class CpUploadImageApp implements ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;

	public String execute() throws Exception {

		HttpServletResponse servletResponse = getServletResponse();
		servletResponse.setHeader("content-type", "application/json");
		servletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input type="text|radio|checkbox|etc", select,
					// etc).
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					// ... (do your job here)
				} else {
					// Process form file field (input type="file").
					String fieldName = item.getFieldName();
					String fileName = FilenameUtils.getName(item.getName());
					InputStream is = item.getInputStream();
					// ... (do your job here)

					String filePath = getServletRequest().getServletContext().getRealPath("/") + "upload";
					System.out.println("File path : " + filePath);

					File newFile = new File(filePath + File.separator + fileName);
					try (FileOutputStream out = new FileOutputStream(newFile)) {
						byte buf[] = new byte[8192];
						int qt = 0;
						while ((qt = is.read(buf)) != -1) {
							out.write(buf, 0, qt);
						}
						System.out.println("File upload complted !");
					}
				}
			}

		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
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
