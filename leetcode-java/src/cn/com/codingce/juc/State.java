package cn.com.codingce.juc;

public enum State {
    // 新生
    NEW,
    // 运行
    RUNNABLE,
    // 阻塞
    BLOCKED,
    // 等待，死死地等
    WAITING,
    // 超时等待
    TIMED_WAITING,
    // 终止
    TERMINATED;
}
