package com.klame.servlet;

import com.klame.Dao.OrderDao;
import com.klame.Dao.OrderItemDao;
import com.klame.Dao.impl.OrderDaoImpl;
import com.klame.Dao.impl.OrderItemDaoImpl;
import com.klame.beans.Cart;
import com.klame.beans.Order;
import com.klame.beans.OrderItem;
import com.klame.service.OrderServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private OrderServiceImpl orderService=new OrderServiceImpl(orderItemDao,orderDao);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if (method.equals("createOrder")){
            createOrder(req,resp);
        }else if(method.equals("listOrders")){
            listAllOrders(req,resp);
        }else if(method.equals("updateOrder")){
            updateOrder(req,resp);
        }else if(method.equals("queryItems")){
            queryItems(req,resp);
        }
    }

    private void listAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList=orderService.getAllOrders();
        req.setAttribute("orderList",orderList);
        req.getRequestDispatcher("manage.jsp").forward(req,resp);
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String consumerName=req.getParameter("consumerName");
        String reaciveName=req.getParameter("reaciveName");
        String telnum=req.getParameter("telNum");
        String address=req.getParameter("address");
        Order order=new Order();
        order.setConsumerName(consumerName);
        order.setConsignee(reaciveName);
        order.setTelNum(telnum);
        order.setAddress(address);
        ServletContext servletContext = req.getServletContext();
        // 获取 CartServlet 类的实例
        CartServlet cartServlet = (CartServlet) servletContext.getAttribute("cartServlet");
        // 获取 myCart 对象
        Cart myCart = cartServlet.getMyCart();
        boolean flag=orderService.addOrder(order, myCart);
        if (flag){
            myCart.clear();
            resp.getWriter().write("<script>alert('购物完成！');window.location.href='cart.jsp';</script>");
        }else{
            resp.getWriter().write("<script>alert('服务错误！');window.location.href='cart.jsp';</script>");
        }

    }
    public void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId=req.getParameter("orderId");
        String recPerson=req.getParameter("consignee");
        String telNum=req.getParameter("telNum");
        String address=req.getParameter("address");
        String deliveryTime=req.getParameter("deliveryTime");
        String deliveryStauts=req.getParameter("deliveryStauts");
        Order order=new Order();
        order.setOrderId(orderId);
        order.setConsignee(recPerson);
        order.setTelNum(telNum);
        order.setAddress(address);
        order.setDeliveryTime(deliveryTime);
        order.setDeliveryStatus(deliveryStauts);
        boolean flag=orderService.updateOrder(order);
        if (flag){
            resp.getWriter().write("<script>alert('修改成功！');window.location.href='manage.jsp';</script>");
        }else{
            resp.getWriter().write("<script>alert('输入有误，请重新输入！');window.location.href='manage.jsp';</script>");
        }
    }
    public void queryItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId=req.getParameter("orderId");
        OrderItem orderItems = orderService.getOrderItemById(orderId);
        System.out.println(orderItems);
        req.setAttribute("orderItems", orderItems);
        req.getRequestDispatcher("manage.jsp").forward(req, resp);

    }
}
