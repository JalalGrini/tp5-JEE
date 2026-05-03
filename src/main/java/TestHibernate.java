import util.HibernateUtil;

public class TestHibernate {
    public static void main(String[] args) {
        System.out.println("Testing Hibernate...");
        try {
            HibernateUtil.getSessionFactory();
            System.out.println("Success!");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
