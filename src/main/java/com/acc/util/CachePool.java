package com.acc.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * for cache object
 * 
 */
public class CachePool implements Serializable {

	private static final long serialVersionUID = 3257001042985433396L;
	// 定时调度Timer类
	private static final Timer timer = new Timer(true);
	// 默认的超时时限为一小时
	private long delay;
	private Map<Object, Object> taskMap = new HashMap<Object, Object>();
	// HashMap<Object, Object>,put是存放的Map
	private Map<Object, Object> valueMap = new HashMap<Object, Object>();
	
	/**
	 * 单例模式
	 */
	private static CachePool cachePool = null;

	/**
	 * 默认数据保存10秒
	 */
	private CachePool() {
		delay = 10 * 1000;
	}

	public static CachePool getInstance() {
		if (cachePool == null) {
			cachePool = new CachePool();
		}
		return cachePool;
	}

	/**
	 * 从容器中取出指定key映射的value
	 * @param key 需要查找的value
	 * @return 指定key映射的value
	 */
	public synchronized Object get(Object key) {
		return valueMap.get(key);
	}

	/**
	 * 往容器中放入一个映射关系，超时时限为默认时限
	 * @param key 需要存放映射的key
	 * @param value 需要存放映射的value
	 * @return 放入value对应的key
	 */
	public synchronized Object put(Object key, Object value) {
		return put(key, value, delay);
	}

	/**
	 * 往容器中放入一个映射关系，并指定超时时限
	 * @param key 需要存放映射的key
	 * @param value 需要存放映射的value
	 * @param delay0 清除时限
	 * @return 放入value对应的key
	 */
	public synchronized Object put(Object key, Object value, long delay0) {
		return put(key, value, delay0, null);
	}

	/**
	 * 往容器中放入一个映射关系，并指定超时时限和超时后的处理方法
	 * @param key 需要存放映射的key
	 * @param value 需要存放映射的value
	 * @param delay0 清除时限
	 * @param overtimeProcessTask 超时后处理的任务
	 * @return 放入value对应的key
	 */
	public synchronized Object put(Object key, Object value, long delay0,
			OvertimeProcessTask overtimeProcessTask) {
		if (delay0 <= 0)
			throw new IllegalArgumentException("清除时限必须为大于0");
		Object result = valueMap.put(key, value);
		refresh(key, delay0, overtimeProcessTask);
		return result;
	}

	/**
	 * 从容器中移除指定key的映射关系
	 * @param key 移除映射关系的key
	 * @return 放入key对应的value
	 */
	public synchronized Object remove(Object key) {
		TaskObject task = (TaskObject) taskMap.remove(key);
		if (task != null) {
			task.getTimerTask().cancel();
		}
		if (key == null) {
			return "=====内存信息已经清除=======";
		} else {
			return valueMap.remove(key);
		}
	}

	/**
	 * 清空容器
	 */
	public synchronized void clear() {
		valueMap.clear();
		taskMap.clear();
	}

	/**
	 * 返回 <tt>true</tt> 如果容器为空
	 * @return <tt>true</tt> 如果容器为空
	 */
	public synchronized boolean isEmpty() {
		return valueMap.isEmpty();
	}

	/**
	 * 返回 <tt>true</tt> 如果容器包含指定key的映射
	 * @param key 需要查找的key
	 * @return <tt>true</tt> 如果容器包含指定key的映射
	 */
	public synchronized boolean containsKey(Object key) {
		return valueMap.containsKey(key);
	}

	/**
	 * 返回 <tt>true</tt> 如果容器包含指定value的映射
	 * @param value 需要查找的value
	 * @return <tt>true</tt> 如果容器包含指定value的映射
	 */
	public synchronized boolean containsValue(Object value) {
		return valueMap.containsValue(value);
	}

	/**
	 * toString
	 * @see java.util.Map#toString()
	 */
	public synchronized String toString() {
		return valueMap.toString();
	}

	/**
	 * 超时处理监听接口
	 */
	private interface OvertimeProcessTask {
		/**
		 * 超时处理动作
		 */
		void overtimeProcess();
	}

	/**
	 * taskMap 中存放的任务对象定义
	 */
	private static class TaskObject {
		private OvertimeProcessTask overtimeProcessTask;

		private TimerTask timerTask;

		private TaskObject(TimerTask timerTask) {
			this(null, timerTask);
		}

		private TaskObject(OvertimeProcessTask listener, TimerTask timerTask) {
			this.overtimeProcessTask = listener;
			this.timerTask = timerTask;
		}

		@SuppressWarnings("unused")
		private OvertimeProcessTask getOvertimeProcessTask() {
			return overtimeProcessTask;
		}

		private TimerTask getTimerTask() {
			return timerTask;
		}
	}

	/**
	 * 刷新已经在Map中的对象，如果不存在要刷新的对象返回null
	 */
	private synchronized void refresh(final Object key, long delay0,
			final OvertimeProcessTask overtimeProcessTask) {
		if (delay0 <= 0)
			throw new IllegalArgumentException("超时时限必须为大于0");

		TaskObject oldTask = (TaskObject) taskMap.get(key);

		if (null != oldTask && null != oldTask.getTimerTask())
			oldTask.getTimerTask().cancel();

		TimerTask task = new TimerTask() {
			public void run() {
				if (overtimeProcessTask != null) {
					overtimeProcessTask.overtimeProcess();
				}
				remove(key);
			}
		};

		taskMap.put(key, new TaskObject(overtimeProcessTask, task));
		timer.schedule(task, delay0);
	}

}
