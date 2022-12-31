package cn.com.codingce.设计模式.组合模式;

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
