package cn.itcast.goods.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 鑾峰彇session涓殑user
		 * 2. 鍒ゆ柇鏄惁涓簄ull
		 *   > 濡傛灉涓簄ull锛氫繚瀛橀敊璇俊鎭紝杞彂鍒癿sg.jsp
		 *   > 濡傛灉涓嶄负null锛氭斁琛�
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		Object user = req.getSession().getAttribute("sessionUser");
		if(user == null) {
			req.setAttribute("code", "error");//涓轰簡鏄剧ずX鍥剧墖
			req.setAttribute("msg", "鎮ㄨ繕娌℃湁鐧诲綍锛屼笉鑳借闂湰璧勬簮");
			req.getRequestDispatcher("/jsps/msg.jsp").forward(req, response);
		} else {
			chain.doFilter(request, response);//鏀捐
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
