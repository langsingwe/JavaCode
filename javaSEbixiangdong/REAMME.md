笔记  
[TOC]  
# String类
基本数据类型 | 引用数据类型
-- | --
byte | Byte
short | short
int   | Integer
long | Long
boolean | Boolean
float | Float
double | Double
char | Character

**基本数据类型对象包装类的最常见作用，就是用于基本数据类型和字符串类型之间的转换**

## 基本数据类型转成字符串  
* 基本数据类型 + ""
* 基本数据类型.toString(基本数据类型值);  
如：`Integer.toString(34);`  //将34整数变成"34"

## 字符串转成基本数据类型  
* xxx a = Xxx.parseXxx(String);  
如：`int a = Integer.parseInt("123");`  
`double b = Double.parseDouble("123");`  
`boolean b = Boolean.parseBoolean(true);`

##StringBuffer
`StringBuffer`是字符串缓冲区，是一个容器
* 长度是可变的
* 可以操作多个数据类型
* 最终会通过`toString()`方法变成字符串

### 存储 
`StringBuffer append()`：将指定数据作为参数添加到结尾  
`StringBuffer insert(index,数据)`：将数据插入到指定index位置

### 删除
`StringBuffer delete(start,end)`：删除缓冲区中的数据，包含start，不包含end
`StringBuffer deleteCharAt(index)`：删除指定位置的字符

### 获取
`char charAt(int index)`  
`int indexOf(String str)`  
`int lastIndexOf(String str)`  
`int length()`  
`String substring(int start,int end)`  
### 修改
`StringBuffer replace(start,end,数据)`  
`void setCharAt(int index, char ch)`   

[参考代码](https://raw.githubusercontent.com/weiliangchun/JavaCoe/master/javaSEbixiangdong/src/com/java/string/stringBuffer/StringBufferDemo.java)

> JDK1.5 版本之后出现了StringBuilder

### StringBuffer和StringBuilder区别
* StringBuffer是线程同步的。 多现成用
* StringBuilder是线程不同步。单线程用  
 以后开发，建议使用StringBuilder