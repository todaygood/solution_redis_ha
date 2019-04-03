
[简单高可用redis架构实践](https://segmentfault.com/a/1190000008262643)

[Redis主从复制的HA部署](https://blog.csdn.net/wilbertzhou/article/details/17784965)


Sentinel方案，加上Redis的Master/Slave的复制，可以打造一个高可用的Redis集群




# 安装redis 

epel 中有redis rpm包


#! /bin/bash
 
PATH=/usr/local/bin:$PATH
redis-cli SAVE
 
date=$(date +"%Y%m%d")
cp /var/lib/redis/6379/dump.rdb /data01/cache_backup/$date.rdb
 
echo "done!"


首先必须进行SAVE, 因为redis的rdb文件并非总是内存数据的完整镜像，备份之前必须进行SAVE，即向其发送SAVE命令，其次拷贝走其rdb文件即可。


