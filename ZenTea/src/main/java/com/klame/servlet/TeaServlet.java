package com.klame.servlet;

import com.klame.Dao.TeaDao;
import com.klame.Dao.impl.TeaDaoImpl;
import com.klame.beans.Tea;
import com.klame.service.TeaService;
import com.klame.service.TeaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "teaServlet", value = "/tea")
@MultipartConfig(
        fileSizeThreshold = 1024 * 256, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class TeaServlet extends HttpServlet {
    private TeaDao teaDao = new TeaDaoImpl();
    private TeaServiceImpl teaService = new TeaServiceImpl(teaDao);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("queryAll")) {
            queryAll(req, resp);
        } else if (method.equals("addTea")) {
            addTea(req, resp);
        }else if(method.equals("updateTea")){
            updateTea(req,resp);
        }else if (method.equals("delete")){
            deleteTea(req,resp);
        }else if(method.equals("queryByName")){
            queryByName(req,resp);
        }else if (method.equals("queryCatrgory")){
            queryCatrgory(req,resp);
        }else if(method.equals("queryById")){
            queryById(req,resp);
        }
    }
    //查询
    protected void queryCatrgory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  category = req.getParameter("teaCategory");
        System.out.println(category);
        List<Tea> teaList = teaService.getTeaByCategory(category);
        req.setAttribute("teaList", teaList);
        req.getRequestDispatcher("manage.jsp").forward(req, resp);
    }
    protected void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teaId = Integer.parseInt(req.getParameter("teaId"));
        System.out.println(teaId);
        List<Tea> teaList = teaService.getTeaById(teaId);
        req.setAttribute("teaList", teaList);
        req.getRequestDispatcher("manage.jsp").forward(req, resp);
    }
    protected void queryByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  teaName = req.getParameter("teaName");
        List<Tea> teaList = teaService.getTeaByName(teaName);
        req.setAttribute("teaList", teaList);
        req.getRequestDispatcher("manage.jsp").forward(req, resp);
    }
    //下架
    protected void deleteTea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teaId = Integer.parseInt(req.getParameter("teaId"));
        boolean flag=teaService.deleteTea(teaId);
        if (flag) {
            resp.getWriter().write("<script>alert('删除成功');window.location.href='manage.jsp';</script>");
        } else {
            resp.getWriter().write("<script>alert('删除失败');window.location.href='manage.jsp';</script>");
        }
    }

    protected void updateTea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teaId = Integer.parseInt(req.getParameter("teaId"));
        String teaName = req.getParameter("teaName2");
        String teaType = req.getParameter("teaType");
        Double teaPrice = Double.parseDouble(req.getParameter("teaPrice"));
        Double teaNum = Double.parseDouble(req.getParameter("teaNum"));
        String teaOrigin = req.getParameter("teaOrigin");
        String teaIntroduce = req.getParameter("teaIntroduce");
        Tea tea=new Tea();
        tea.setTeaId(teaId);
        tea.setTeaName(teaName);
        tea.setTeaCategory(teaType);
        tea.setTeaPrice(teaPrice);
        tea.setTeaNum(teaNum);
        tea.setTeaProduce(teaOrigin);
        tea.setTeaIntroduction(teaIntroduce);
        System.out.println(tea.toString());
        boolean flag =teaService.updateTea(tea);
        if (flag) {
            resp.getWriter().write("<script>alert('修改成功');window.location.href='manage.jsp';</script>");
        } else {
            resp.getWriter().write("<script>alert('修改失败');window.location.href='manage.jsp';</script>");
        }

    }
    //查询所有
    protected void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tea> teaList = teaService.getAllTea();
        req.setAttribute("teaList", teaList);
        String page=req.getParameter("page");
        if(page.equals("1")){
            req.getRequestDispatcher("teabody.jsp").forward(req, resp);
        }else if(page.equals("2")){
            req.getRequestDispatcher("manage.jsp").forward(req, resp);
        }

    }

    protected void addTea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teaName = req.getParameter("teaName");
        String teaType = req.getParameter("teaType");
        Double teaPrice = Double.parseDouble(req.getParameter("teaPrice"));
        Double teaNum = Double.parseDouble(req.getParameter("teaNum"));
        String teaOrigin = req.getParameter("teaOrigin");
        String teaIntroduce = req.getParameter("teaIntroduce");
        Part part = req.getPart("teaImg");
        String teaImg = part.getSubmittedFileName();
        if (part != null) {
            // 文件字段
            String fileName = part.getSubmittedFileName();
            // 保存上传的图片文件到指定目录
            String savePath = "D:\\java\\IDEA\\idea-project\\JavaWeb\\ZenTea\\src\\main\\webapp\\image\\";
            savePath += fileName;
            part.write(savePath);
        }
        Tea tea=new Tea();
        tea.setTeaName(teaName);
        tea.setTeaCategory(teaType);
        tea.setTeaPrice(teaPrice);
        tea.setTeaNum(teaNum);
        tea.setTeaProduce(teaOrigin);
        tea.setTeaIntroduction(teaIntroduce);
        tea.setTeaImg(teaImg);
        boolean flag = teaService.addTea(tea);
        if (flag) {
            resp.getWriter().write("<script>alert('添加成功');window.location.href='manage.jsp';</script>");
        } else {
            resp.getWriter().write("<script>alert('添加失败');window.location.href='manage.jsp';</script>");
        }
    }
}
