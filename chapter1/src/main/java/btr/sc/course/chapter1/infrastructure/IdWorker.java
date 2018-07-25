package btr.sc.course.chapter1.infrastructure;

import lombok.Synchronized;
import lombok.val;

/**
 * 分布式全局ID生成器
 * tweeter的snowflake算法
 * id构成:42位的时间前缀+5位的数据节点标识+5位机器id+12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 * 42位时间戳可以使用(2的42次方-1)/(1000360024*365)=139.5年
 * 10机器编码支持最多部署1024个节点
 * 12位sequence可以表示4096个数字,它是在time相同的情况下，递增该值直到为0，
 * 即一个循环结束，此时便只能等到下一个ms到来，一般情况下4096/ms的请求是不太可能出现的，所以足够使用了。
 * Created by ryze on 2017/5/11.
 */
public final class IdWorker
{
  //机器编码所占位数
  private final long workerIdBits     = 5L;
  //数据中心标识所占位数
  private final long dataCenterIdBits = 5L;
  //毫秒内序列 0-4095
  private       long sequence         = 0L;
  //生成Id的时间戳
  private       long lastTimestamp    = -1L;
  private final long workerId;
  private final long dataCenterId;

  private IdWorker(final long workerId, final long dataCenterId)
  {
    //支持的最大机器编码(这个移位算法可以很快计算出几位二进制数能表示的最大十进制数)
    val maxWorkerId     = -1L ^ (-1L << workerIdBits);
    //支持的最大数据标识
    val maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    if (workerId > maxWorkerId || workerId < 0)
    {
      throw new IllegalArgumentException(String.format("workerId不能大于%d或者小于0", maxWorkerId));
    }
    if (dataCenterId > maxDataCenterId || dataCenterId < 0)
    {
      throw new IllegalArgumentException(String.format("dataCenterId不能大于%d或者小于0", maxWorkerId));
    }
    this.workerId     = workerId;
    this.dataCenterId = dataCenterId;
  }

  private long timeGenerator()
  {
    return System.currentTimeMillis();
  }

  /**
   * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
   */
  private void blockNextMillis(final long lastTimestamp)
  {
    val timestamp = timeGenerator();
    if (timestamp <= lastTimestamp) { timeGenerator(); }
  }

  @Synchronized
  private long createId()
  {
    val sequenceBits       = 12L;
    //开始时间
    val startEpoch         = 1403854494756L;
    //数据中心标识<<12+5
    val dataCenterIdShift  = sequenceBits + workerIdBits;
    //时间戳<<12+10
    val timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    val timestamp          = timeGenerator();

    //当前时间戳小于上一次Id生成的时间戳,说明系统时间回退过,这个时候应当抛出异常
    if (timestamp < lastTimestamp) throw new RuntimeException("系统时间不正常,拒绝为" + (lastTimestamp - timestamp) + "毫秒生成id");
    if (timestamp == lastTimestamp)
    {
      //生成序列的掩码, 0xfff=4095
      final long sequenceMask = -1L ^ (-1L << sequenceBits);
      sequence = (sequence + 1) & sequenceMask;
      //毫秒内序列溢出
      if (0 == sequence) blockNextMillis(lastTimestamp);
    }
    //时间戳改变,毫秒内序列重置
    else sequence = 0L;
    //上次生成ID的时间截
    lastTimestamp = timestamp;

    //移位并通过或运算拼到一起组成64位的ID
    return ((timestamp - startEpoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << sequenceBits) | sequence;
  }

  private static IdWorker flowIdWorker = new IdWorker(1, 1);

  public static String getId()
  {
    return flowIdWorker.createId() + "";
  }
}