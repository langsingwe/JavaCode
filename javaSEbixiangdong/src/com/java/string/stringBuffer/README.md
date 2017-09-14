`StringBuffer`是字符串缓冲区，是一个容器
* 长度是可变的
* 可以操作多个数据类型
* 最终会通过`toString()`方法变成字符串

##### 存储 
`StringBuffer append()`：将指定数据作为参数添加到结尾  
`StringBuffer insert(index,数据)`：将数据插入到指定index位置

##### 删除
`StringBuffer delete(start,end)`：删除缓冲区中的数据，包含start，不包含end
`StringBuffer deleteCharAt(index)`：删除指定位置的字符

##### 获取
`char charAt(int index)`  
`int indexOf(String str)`  
`int lastIndexOf(String str)`  
`int length()`  
`String substring(int start,int end)`  

##### 修改
`StringBuffer replace(start,end,数据)`  
`void setCharAt(int index, char ch)`  
