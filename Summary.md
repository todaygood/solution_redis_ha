
[简单高可用redis架构实践](https://segmentfault.com/a/1190000008262643)

[Redis主从复制的HA部署](https://blog.csdn.net/wilbertzhou/article/details/17784965)
[Redis高可用方案-哨兵与集群](https://blog.csdn.net/sunhuiliang85/article/details/78361211)
[Redis数据备份、恢复与持久化](https://www.jianshu.com/p/e04a9e80a998)

Sentinel方案，加上Redis的Master/Slave的复制，可以打造一个高可用的Redis集群


例如你有3个哨兵，当设置至少2个哨兵认为主库跪了才切换的话，只有一个宕机了是没法做出选举决策的

[Redis哨兵模式（sentinel）学习总结及部署记录（主从复制、读写分离、主从切换）](https://www.cnblogs.com/kevingrace/p/9004460.html)


[sentinel、JedisSentinelPool实战](https://lanjingling.github.io/2015/12/29/redis-sentinel-jedis-shizhan/)

# 安装redis 

epel 中有redis rpm包


#! /bin/bash
 
PATH=/usr/local/bin:$PATH
redis-cli SAVE
 
date=$(date +"%Y%m%d")
cp /var/lib/redis/6379/dump.rdb /data01/cache_backup/$date.rdb
 
echo "done!"


首先必须进行SAVE, 因为redis的rdb文件并非总是内存数据的完整镜像，备份之前必须进行SAVE，即向其发送SAVE命令，其次拷贝走其rdb文件即可。


