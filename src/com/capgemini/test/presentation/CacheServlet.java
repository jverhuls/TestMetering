package com.capgemini.test.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CacheServlet extends HttpServlet {
	/*
		CACHE MANIFEST
		#commentss
		cache.html
		images/email.jpg
	
		FALLBACK:
		images/main.png images/email.jpg
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(req.getRequestURL().indexOf("manifest") != -1){
			resp.setContentType("text/cache-manifest");
			
			String path = this.getServletContext().getRealPath("cache.manifest");
			File f = new File(path);
			
			if(req.getSession().getAttribute("cacheLastModified") == null || 
					(Long)req.getSession().getAttribute("cacheLastModified") != f.lastModified()
					|| req.getSession().getAttribute("imageGenerated") == null ||
					(Boolean)req.getSession().getAttribute("imageGenerated")){
				FileInputStream fis = new FileInputStream(path);
				int in;
				while ((in = fis.read()) != -1){
					resp.getOutputStream().write(in);
				}	
				resp.getOutputStream().println();
				resp.getOutputStream().println("#" + new Date());
				req.getSession().setAttribute("cacheLastModified", f.lastModified());
				req.getSession().setAttribute("imageGenerated", false);
			}else{
				resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);				
			}
		}
		/*else if(req.getRequestURL().indexOf("index.jsp") != -1){
			String path = this.getServletContext().getRealPath("index.jsp");
			File f = new File(path);
			if(this.getServletContext().getAttribute("indexLastModified") == null || 
					(Long)this.getServletContext().getAttribute("indexLastModified") != f.lastModified()){
				this.getServletContext().setAttribute("indexLastModified", f.lastModified());
				req.getRequestDispatcher("index2.jsp").forward(req, resp);
			}else{
				resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			}
		}*/
		
		
		
	}

}
