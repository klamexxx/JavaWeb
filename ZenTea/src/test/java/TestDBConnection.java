import com.klame.Dao.OrderDao;
import com.klame.Dao.OrderItemDao;
import com.klame.Dao.TeaDao;
import com.klame.Dao.impl.ConsumerDaoImpl;
import com.klame.Dao.impl.OrderDaoImpl;
import com.klame.Dao.impl.OrderItemDaoImpl;
import com.klame.Dao.impl.TeaDaoImpl;
import com.klame.beans.Consumer;
import com.klame.service.OrderServiceImpl;
import com.klame.service.TeaService;
import com.klame.service.TeaServiceImpl;
import com.klame.utils.DBUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestDBConnection {
    private ConsumerDaoImpl consumerDaoImpl;
    @Test
    public void testDBConnection() throws SQLException {
        Connection conn= DBUtil.getConnection();

    }
    @Test
    public void tesrInsertConsumer(){
        consumerDaoImpl=new ConsumerDaoImpl();
        Consumer consumer=new Consumer();
        consumer.setConsumerName("张三");
        consumer.setConsumerPwd("123456");
        consumer.setConsumerTelNum("12345678901");
        System.out.println(consumerDaoImpl.addConsumer(consumer));
    }
    @Test
    public void testCheckTel(){
        ConsumerDaoImpl consumerDao=new ConsumerDaoImpl();
        System.out.println(consumerDao.checkTel("11111222223"));
    }
    @Test
    public void listTea(){
        TeaDao teaDao=new TeaDaoImpl();
        TeaServiceImpl teaService=new TeaServiceImpl(teaDao);
        System.out.println(teaService.getAllTea().size());
    }

    @Test
    public void testOrders(){
        OrderDao orderDao=new OrderDaoImpl();
        OrderItemDao  orderItemDao=new OrderItemDaoImpl();
        OrderServiceImpl orderService=new OrderServiceImpl(orderItemDao,orderDao);
        System.out.println(orderService.getAllOrders().size());
    }

}
