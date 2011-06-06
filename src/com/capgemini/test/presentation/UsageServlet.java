package com.capgemini.test.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.test.util.Util;

public class UsageServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String path = this.getServletContext().getRealPath("generatedImages");
		String url = req.getRequestURI();
		boolean isIpad = url.indexOf("ipad") != -1;
		String filename = url.substring(url.lastIndexOf('/') + 1, url.length());
		String fullPath = path + "\\" + filename;
		File f = new File(fullPath);
		// overview image not yet generated
		if (!Util.exists(fullPath) || req.getParameter("refresh") != null) {
			req.getSession().setAttribute("imageGenerated", true);
			Util.generateImage(fullPath, isIpad);
		}
		resp.setContentType("image/png");
		
		if(req.getHeader("If-None-Match") != null && req.getHeader("If-None-Match").equals(Long.toString(f.lastModified()))){
			resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return;
		}
		resp.setHeader("ETag", Long.toString(f.lastModified()));
		FileInputStream fis = new FileInputStream(fullPath);
		int in;
		while ((in = fis.read()) != -1){
			resp.getOutputStream().write(in);
		}
	}

}
