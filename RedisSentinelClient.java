public class RedisSentinelClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set sentinels = new HashSet();
        sentinels.add(new HostAndPort("172.30.37.73", 26379).toString());
        sentinels.add(new HostAndPort("172.30.37.73", 26380).toString());
        sentinels.add(new HostAndPort("172.30.37.73", 26381).toString());
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
        
        Jedis master = sentinelPool.getResource();
        master.set("username","liangzhichao");
        sentinelPool.returnResource(master);
        
        Jedis master2 = sentinelPool.getResource();
        String value = master2.get("username");
        System.out.println("username: " + value);
        master2.close();
        sentinelPool.destroy();
	}

}