import entity.*;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.*;
import service.impl.CustomerDaoImpl;
import util.HibernateUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        final UserService service = new UserService();
        final CustomerService customerService = new CustomerService();
        final ProductService productService = new ProductService();
        final OrderService orderService = new OrderService();
        final OrderDetailsService orderDetailsService = new OrderDetailsService();


        List<User> users = Stream.of(
                        UserService.createUser("Anna", "Anna1337@example.com", "Customer"),
                        UserService.createUser("Bob", "BoB2002@example.com", "Customer"),
                        UserService.createUser("Kate", "Kate33@example.com", "Customer"),
                        UserService.createUser("Lucio", "BadLucio@example.com", "Customer"),
                        UserService.createUser("Shrek", "ShrekStar@example.com", "Admin"))
                .collect(Collectors.toList());

//                users.forEach(service::saveUser);

        List<Customer> customers = Stream.of(
                        CustomerService.createCustomer("Anna", "Merit", "+123-456-7890", users.get(0)),
                        CustomerService.createCustomer("Bob", "Kislyi", "+987-654-3210", users.get(1)),
                        CustomerService.createCustomer("Kate", "Diva", "+555-123-4567", users.get(2)),
                        CustomerService.createCustomer("Lucio", "Badboy", "+999-888-7777", users.get(3)),
                        CustomerService.createCustomer("Shrek", "Swampovich", "+000-111-2222", users.get(4)))
                .collect(Collectors.toList());

//        customers.forEach(customerService::saveCustomer);

        List<Product> products = Stream.of(
                        ProductService.createProduct("Шоколад", 20.99, 5),
                        ProductService.createProduct("Кофе", 50.49, 100),
                        ProductService.createProduct("Молоко ", 45.99, 5),
                        ProductService.createProduct("Яблоки", 23.99, 25),
                        ProductService.createProduct("Морж", 100, 1),
                        ProductService.createProduct("Авакадо", 33.22, 33),
                        ProductService.createProduct("Фигурка супермена", 150, 1),
                        ProductService.createProduct("Пальто", 320, 1),
                        ProductService.createProduct("Уксус", 200.50, 150),
                        ProductService.createProduct("Варенье банановое", 33.33, 33))
                .collect(Collectors.toList());

//        products.forEach(productService::saveProduct);


        List<Order> orders = Stream.of(
                        OrderService.createOrder("Шоколад и кофе", 71.48, customers.get(0), products.subList(0, 2)),
                        OrderService.createOrder("Морж и Авакадо", 133.22, customers.get(0), products.subList(4, 6)),
                        OrderService.createOrder("Молоко и Яблоки", 69.98, customers.get(1), products.subList(2, 4)),
                        OrderService.createOrder("Морж и Авакадо", 133.22, customers.get(2), products.subList(4, 6)),
                        OrderService.createOrder("Фигурка супермена и пальто", 470, customers.get(3), products.subList(6, 8)),
                        OrderService.createOrder("Морж и Авакадо", 133.22, customers.get(3), products.subList(4, 6)),
                        OrderService.createOrder("Варенье банановое и Уксус", 72, customers.get(4), products.subList(8, 10)))
                .collect(Collectors.toList());

//                orders.forEach(orderService::saveOrder);


        List<OrderDetails> orderDetails = Stream.of(
                OrderDetailsService.createOrderDetails("Ммм, шоколадки", orders.get(0)),
                OrderDetailsService.createOrderDetails("Морж!!??", orders.get(1)),
                OrderDetailsService.createOrderDetails("Так себе сочетание", orders.get(2)),
                OrderDetailsService.createOrderDetails("Снова Морж!?", orders.get(3)),
                OrderDetailsService.createOrderDetails("Ну тут багато-багато", orders.get(4)),
                OrderDetailsService.createOrderDetails("Терпеть не могу моржей!", orders.get(5)),
                OrderDetailsService.createOrderDetails("Мда, про прошлое сочетание я уже молчу", orders.get(6))
        ).collect(Collectors.toList());

//        orderDetails.forEach(orderDetailsService::saveOrderDetails);

        //Отримати всі продукти
//        productService.getAllProducts().forEach(u -> System.out.println(u.getName()));

        //Отримати кількість ордерів у кожного кастомера
//        System.out.println(orderService.countOrders(1));
//        System.out.println(orderService.countOrders(2));
//        System.out.println(orderService.countOrders(3));
//        System.out.println(orderService.countOrders(4));
//        System.out.println(orderService.countOrders(5));

        //Отримати всі замовлення з деталями, відсортувавши за датою розміщення

//        List<Order> ordersSortedByTimePlaced = orderService.getAllOrdersWithDetails();
//
//        for (Order order : ordersSortedByTimePlaced) {
//            System.out.println("Заказ #" + order.getId());
//            System.out.println("Дата размещения: " + order.getOrderDetails().getTimePlaced());
//            System.out.println("Комментарий: " + order.getOrderDetails().getComment());
//            System.out.println("Дата мзменения: " + order.getOrderDetails().getTimeUpdated());
//        }

        //Змінити будь-що у замовленні
//        orderService.updateCustomerOrder(1, "Новый комментарий", LocalDateTime.now());


//        List<Customer> customerListWithOrders = customerService.getAllCustomersOrders();
//        for (Customer customer : customerListWithOrders) {
//            System.out.println("Имя кастомера: " + customer.getName());
//            System.out.println("Фамилия кастомера: " + customer.getSurname());
//            System.out.println("Номер кастомера: " + customer.getPhone());
//            System.out.println("Заказы кастомера:");
//
//            for (Order order : customer.getOrders()) {
//                System.out.println("  Заказ #" + order.getId());
//                System.out.println("  Название заказа: " + order.getName());
//                System.out.println("  Сумма заказа: " + order.getTotalSum());
//
//                int totalProductCount = orderService.getTotalQuantityForOrder(order.getId()); // Получаем общее количество продуктов
//                System.out.println("  Количество продуктов в заказе (ИМЕННО ОБЩЕЕ КОЛ-ВО ПРОДУКТОВ, А НЕ КОЛ-ВО ПОЗИЦИЙ В ЗАКАЗЕ): " + totalProductCount);
//                System.out.println("  -----------------------------");
//            }
//
//            System.out.println("===============================");
//        }
    }
}
