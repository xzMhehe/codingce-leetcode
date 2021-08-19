package cn.com.codingce.我的.天理.juc;

/**
 * 给某个对象加锁
 *
 * @author williamma
 */
public class MySynchronizedCodeObject {

    class Account {

        String name;
        float amount;

        public Account(String name, float amount) {
            this.name = name;
            this.amount = amount;
        }

        public Account() {
        }

        //存钱
        public void deposit(float amt) {
            amount += amt;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //取钱
        public void withdraw(float amt) {
            amount -= amt;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public float getBalance() {
            return amount;
        }
    }

    /**
     * 账户操作类
     */
    class AccountOperator implements Runnable {

        private Account account;

        public AccountOperator(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            synchronized (account) {
                // 存500
                account.deposit(500);
                System.out.println(" 线程存完后 " + Thread.currentThread().getName() + " : " + account.getBalance());
                // 取500
                account.withdraw(500);
                System.out.println(" 线程取完后 " + Thread.currentThread().getName() + " : " + account.getBalance());
            }
        }
    }

    public static void main(String[] args) {
        // 账户
        Account account = new MySynchronizedCodeObject().new Account("zhang san", 10000.0f);
        // 账户操作
        AccountOperator accountOperator = new MySynchronizedCodeObject().new AccountOperator(account);

        final int THREAD_NUM = 5;

        Thread threads[] = new Thread[THREAD_NUM];

        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(accountOperator, "Thread" + i);
            threads[i].start();
        }
    }
}
