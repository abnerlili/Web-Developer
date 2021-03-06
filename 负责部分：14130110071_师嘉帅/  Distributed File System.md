### 分布式储存系统

为了简化用户端的使用，提供了一个分布式缓存系统来提供对旧书管理系统的访问接口以及本地数据缓冲以降低网络压力。分布式存储一般都采用副本的方式来确保数据的安全性。写入磁盘前不需要进行额外复杂的计算，就可以将数据写入磁盘，是最快速的方式。是一种空间换时间的方法，即想得到较好的存储性能，就采用副本的方式。

##### 采用分布式储存的原因

我们对于旧书交易平台采用了分布式的储存系统，以期解决可能会遇到的大规模并发性的问题，比如结算交易和点评回馈等模块。我们在了解相关知识后，对储存系统的特性进行了如下的分析：

  1) 存储量很大，经常会达到单台服务器无法提供的规模，比如结算模块、评论板块等。因此需要专业的大规模存储系统。
  
  2) 集中存储的缺点是，物理介质集中布放;视频流上传到中心对机房环境要求高，要求机房空间大，承重、空调等都是需要考虑的问题。对于我们初创项目而言，较高的维护成本是急需解决的一个问题。
  
  3）分布存储的优点是，物理介质分布到不同的地理位置;视频流就近上传，对骨干网带宽没有什么要求;可采用多套低端的小容量的存储设备分布部署，设备价格和维护成本较低;小容量设备分布部署，对机房环境要求低。分布式对不同地区和网络状态的旧书系统会员，可以起到及时响应，优化展开的效果，很好的改善了用户的实际体验，有利于增强用户的粘度和满意的程度。

因此，针对以上的需求和实际的业务运行评估，考虑到潜在的并发情况，我们对旧书管理系统采取了分布式的储存系统

![](https://github.com/Topologies/learnbook/blob/master/Picture/%E5%88%86%E5%B8%83%E5%BC%8F%E5%82%A8%E5%AD%98%E7%B3%BB%E7%BB%9F.jpg)

##### 分布式储存系统的实现

HDFS是Hadoop应用程序使用的主要分布式存储，主要被设计在商用硬件上运行，满足低成本、高容错、高吞吐的特性。这也正是我们选择HDFS的原因。

HDFS具有主从架构，由文件系统元数据管理NameNode和实际数据存储DataNode组成。 一个HDFS集群只有一个NameNode，可以有许多DataNode，一个集群中有一个节点是主节点做为NameNode，其他节点是从节点做为DataNode。

##### 分布式储存优缺点及潜在问题
 
我们在了解其优势的同时，对于面临的挑战进行了详细的分析。

  1) 存分布存储的缺点是，备份困难，如果用户将数据存储在各自的系统上，而不是将他们存储在中央系统中，难于制定一项有效的备份计划。这种情况还可能导致用户使用同一文件的不同版本。
  
  2) 为了运行程序要求性能更好的PC机；要求使用适当的程序；不同计算机的文件数据需要复制；对某些PC机要求有足够的存储容量，形成不必要的存储成本；管理和维护比较复杂；设备须要互相兼容。
 
当然，对于这样潜在的问题引燃引起我们足够的重视。我们在后期运维的过程中会对系统进行性能和安全性方面的保障，以保证用户能够正常体验我们提供的服务。
  


