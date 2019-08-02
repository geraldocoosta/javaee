package br.com.casadocodigo.loja.servlet;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.casadocodigo.loja.infra.FileSaver;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getRequestURI().split("/file")[1];

		Path source = Paths.get(FileSaver.SERVER_PATH + "/" + path);

		String contentType = getContentType(source);

		setResponseHeader(res, source, contentType);

		FileSaver.transfer(source, res.getOutputStream());

	}

	private String getContentType(Path source) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor("file:" + source);
		return contentType;
	}

	private void setResponseHeader(HttpServletResponse res, Path source, String contentType) throws IOException {
		res.reset();
		res.setContentType(contentType);
		res.setHeader("Content-Length", String.valueOf(Files.size(source)));
		res.setHeader("Content-Disposition", "filename=\"" + source.getFileName().toString() + "\"");
	}
}
