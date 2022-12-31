package cn.com.codingce.设计模式.组合模式;

/**
 * 何时使用： 1、您想表示对象的部分-整体层次结构（树形结构）；
 * 希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
 * 优点：高层模块调用简单；节点自由增加。
 * 缺点：在使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒置原则。
 * 依赖倒置原则（Dependence Inversion Principle）是程序要依赖于抽象接口，不要依赖于具体实现。
 * 简单的说就是要求对抽象进行编程，不要对实现进行编程，这样就降低了客户与实现模块间的耦合。
 *
 * @author mxz
 */
public class CompositePatternDemo {

    public static void main(String[] args) {
        Employee CEO = new Employee("刘备", "CEO", 30000);

        Employee headSales = new Employee("诸葛亮", "销售主管", 20000);

        Employee headMarketing = new Employee("张飞", "市场营销主管", 20000);

        Employee clerk1 = new Employee("法正", "营销员", 10000);
        Employee clerk2 = new Employee("李严", "营销员", 10000);

        Employee salesExecutive1 = new Employee("魏延", "销售员", 10000);
        Employee salesExecutive2 = new Employee("蒋琬", "销售员", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        // 打印该组织的所有员工
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}
