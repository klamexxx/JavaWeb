package com.klame.servlet;

import com.klame.Dao.TeaDao;
import com.klame.Dao.impl.TeaDaoImpl;
import com.klame.beans.Cart;
import com.klame.beans.CartItem;
import com.klame.beans.Tea;
import com.klame.service.TeaServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CartServlet" ,value="/cart")
public class CartServlet extends HttpServlet {
    private Cart myCart;

    @Override
    public void init() throws ServletException {
        myCart=new Cart();
        ServletContext servletContext = getServletContext();

        // 将 CartServlet 实例存储在 Servlet 上下文对象中
        servletContext.setAttribute("cartServlet", this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("add")){
            addItem(req,resp);
        }else if (method.equals("remove")){
            removeItem(req,resp);
        }else if(method.equals("list")){
            listItems(req,resp);
        }else if(method.equals("clear")){
            clearCart(req,resp);
        }
    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        myCart.clear();
        req.getRequestDispatcher("cart.jsp").forward(req,resp);
    }

    private void listItems(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("cartList",myCart);
        req.getRequestDispatcher("cart.jsp").forward(req,resp);
    }

    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id= Integer.parseInt(req.getParameter("teaId"));
        myCart.removeItem(id);
        req.getRequestDispatcher("cart.jsp").forward(req,resp);
    }

    private void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id= Integer.parseInt(req.getParameter("teaId"));
        TeaDao teaDao =new TeaDaoImpl();
        TeaServiceImpl teaService=new TeaServiceImpl(teaDao);
        Tea tea=teaService.getTeaById(id).get(0);
        CartItem cartItem=new CartItem(tea.getTeaId(),tea.getTeaName(),tea.getTeaImg(),tea.getTeaPrice(),1.00);
        myCart.addItem(cartItem);
        req.getRequestDispatcher("teabody.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    public Cart getMyCart() {
        return myCart;
    }
}
