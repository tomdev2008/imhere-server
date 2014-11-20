/**
 * 
 */
package org.xiaoxiancai.imhere.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.xiaoxiancai.imhere.common.protos.business.LocationProtos.Location;

/**
 * 连接服务器
 * 
 * @author linxianneng
 */
@Component("linkServer")
public class LinkServer extends AbstractServer {

	/**
	 * 用户最后定位位置
	 */
	private Map<Integer, Location> latestLocation = new HashMap<Integer, Location>();

	/**
	 * 用户当前定位位置
	 */
	private Map<Integer, Location> currentLocation = new HashMap<Integer, Location>();

	/**
	 * 用户位置更新时间
	 */
	private Map<Integer, Long> locationUpdateTime = new HashMap<Integer, Long>();

	private static final int SCHEDULED_PERIOD_SECS = 5 * 60;

	private static final int SCHEDULED_INITIAL_DELAY_SECS = 60;

	@Override
	protected void doInit() throws Exception {
		logger.info("link server init");
	}

	@Override
	protected void doStart() throws Exception {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(new ExpireOnlineTask(),
				SCHEDULED_INITIAL_DELAY_SECS, SCHEDULED_PERIOD_SECS,
				TimeUnit.SECONDS);
		logger.info("link server start success");
	}

	@Override
	protected void doStop() throws Exception {
		logger.info("link server stop");
	}

	public Map<Integer, Location> getLatestLocation() {
		return latestLocation;
	}

	public Map<Integer, Location> getCurrentLocation() {
		return currentLocation;
	}

	public Map<Integer, Long> getLocationUpdateTime() {
		return locationUpdateTime;
	}

	/**
	 * 定期清理下线任务
	 *
	 * @author xiannenglin
	 */
	class ExpireOnlineTask implements Runnable {

		/**
		 * 最长idle时间(1min), 超过则从内存中清理
		 */
		private static final int MAX_IDLE_MILLIS = 1 * 60 * 1000;

		@Override
		public void run() {
			logger.debug("expire task start");
			long now = System.currentTimeMillis();
			for (Entry<Integer, Long> entry : locationUpdateTime.entrySet()) {
				int userId = entry.getKey();
				long updateTime = entry.getValue();
				if (isTimeOut(now, updateTime)
						&& currentLocation.containsKey(userId)) {
					currentLocation.remove(userId);
					logger.debug("expire user {} online", userId);
				}
			}
		}

		/**
		 * 是否超时
		 * 
		 * @param now
		 * @param updateTime
		 * @return
		 */
		private boolean isTimeOut(long now, long updateTime) {
			return now - updateTime > MAX_IDLE_MILLIS ? true : false;
		}
	}
}
