# Redis HA方案

官方提供的是sentinel方案，参见[redis sentinel](https://redis.io/topics/sentinel)
Sentinel方案，加上Redis的Master/Slave的复制，可以打造一个高可用的Redis集群

[简单高可用redis架构实践](https://segmentfault.com/a/1190000008262643)

[Redis主从复制的HA部署](https://blog.csdn.net/wilbertzhou/article/details/17784965)
[Redis高可用方案-哨兵与集群](https://blog.csdn.net/sunhuiliang85/article/details/78361211)
[Redis数据备份、恢复与持久化](https://www.jianshu.com/p/e04a9e80a998)

## 原理
[选举的原理和机制](https://my.oschina.net/u/3371837/blog/1790026)
redis提供了sentinel（哨兵）机制，通过sentinel模式启动redis后，自动监控master/slave的运行状态，基本原理是：心跳机制+投票裁决

每个sentinel会向其它sentinal、master、slave定时发送消息，以确认对方是否“活”着，如果发现对方在指定时间（可配置）内未回应，则暂时认为对方已挂（所谓的“主观认为宕机” Subjective Down，简称SDOWN）。

若“哨兵群”中的多数sentinel，都报告某一master没响应，系统才认为该master"彻底死亡"(即：客观上的真正down机，Objective Down，简称ODOWN)，通过一定的vote算法，从剩下的slave节点中，选一台提升为master，然后自动修改相关配置

两个基本概念
S_DOWN:subjectively down,直接翻译的为"主观"失效,即当前sentinel实例认为某个redis服务为"不可用"状态.
O_DOWN:objectively down,直接翻译为"客观"失效,即多个sentinel实例都认为master处于"SDOWN"状态,那么此时master将处于ODOWN,ODOWN可以简单理解为master已经被集群确定为"不可用",将会开启failover.

# 关键参数

https://blog.csdn.net/qq_32090861/article/details/83113653
https://blog.csdn.net/baidu_31945865/article/details/78799249

[Redis哨兵模式（sentinel）学习总结及部署记录（主从复制、读写分离、主从切换）](https://www.cnblogs.com/kevingrace/p/9004460.html)


[sentinel、JedisSentinelPool实战](https://lanjingling.github.io/2015/12/29/redis-sentinel-jedis-shizhan/)




# Question

3台机器




# 安装redis 

epel 中有redis rpm包


#! /bin/bash
 
PATH=/usr/local/bin:$PATH
redis-cli SAVE
 
date=$(date +"%Y%m%d")
cp /var/lib/redis/6379/dump.rdb /data01/cache_backup/$date.rdb
 
echo "done!"


首先必须进行SAVE, 因为redis的rdb文件并非总是内存数据的完整镜像，备份之前必须进行SAVE，即向其发送SAVE命令，其次拷贝走其rdb文件即可。


