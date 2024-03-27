package com.klame.servlet;

import com.klame.Dao.impl.ConsumerDaoImpl;
import com.klame.beans.Consumer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name="LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account=req.getParameter("username");
        String password=req.getParameter("password");
        String chkCode=req.getParameter("validate");
        HttpSession session1= req.getSession();
        String msg2="",msg="";
        String randStr=(String) session1.getAttribute("randStr");
        String loginType = req.getParameter("loginType");
        if (loginType.equals("user")) {
            // 处理用户登录逻辑
            if(randStr.equalsIgnoreCase(chkCode)){
                ConsumerDaoImpl consumerDao=new ConsumerDaoImpl();
                String id=consumerDao.checkId(account);
                String tel=consumerDao.checkTel(account);
                if((account.equals(id)||account.equals(tel))&&(password.equals(
                        consumerDao.checkConsumer(account))||password.equals(consumerDao.checkConsumer2(account)))){
                    //登录成功
                    HttpSession session = req.getSession();
                    session.setAttribute("username", account);
                    resp.sendRedirect("index.jsp");
                }else{
                    //登录失败
                    msg="用户名或密码错误";
                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            }else{
                msg2="验证码错误";
                req.setAttribute("msg2", msg2);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else if(loginType.equals("manager"))  {
            // 处理管理员登录逻辑
            if(randStr.equalsIgnoreCase(chkCode)){
                if(account.equals("admin")&&password.equals("123456")){
                    //登录成功
                    HttpSession session = req.getSession();
                    session.setAttribute("username", account);
                    resp.sendRedirect("index.jsp");
                }else{
                    //登录失败
                    msg="用户名或密码错误";
                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("managerlogin.jsp").forward(req, resp);
                }
            }else{
                msg2="验证码错误";
                req.setAttribute("msg2", msg2);
                req.getRequestDispatcher("managerlogin.jsp").forward(req, resp);
            }
        }else{
            //处理注册请求
            String  username = req.getParameter("name");
            String  pwd = req.getParameter("pwd");
            String  telNum=req.getParameter("telNum");
            Consumer consumer=new Consumer();
            consumer.setConsumerName(username);
            consumer.setConsumerPwd(pwd);
            consumer.setConsumerTelNum(telNum);
            ConsumerDaoImpl consumerDao=new ConsumerDaoImpl();
            int affectRow=consumerDao.addConsumer(consumer);
            if (affectRow>0){
                msg="注册成功";
                req.setAttribute("msg3", msg);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }else{
                msg="注册失败";
                req.setAttribute("msg3", msg);
                req.getRequestDispatcher("managerlogin.jsp").forward(req, resp);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 移除当前用户的HttpSession对象
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() +"/index.jsp");
    }
}
