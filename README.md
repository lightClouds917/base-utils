# base-utils 工具目录
##### 有问题请随时联系，weChat:w1186355422
##### 贡献者须知：虽然此项目是个人使用的工具包，但为了大家可以共同维护和方便其他人使用，请提交维护时，遵守以下规则：
* 1.提交高质量代码，代码必须经过自己核实校验，且若和版本相关的特殊工具，请指出适用版本；
* 2.请提交规范，清晰，有合理注释的代码；
* 3.新增工具类后，或者工具类中新增方法后，请在类头上注释中添加此方法，并在README.md中更新一下类名或者方法名；
* 4.任何提交，请commit时都添加上说明，并请在必要时及时同步更新README.md
* 5.一切为了质量，便捷，清晰，易用；
* 6.不规范的或者有问题的代码，维护者有权利删除或者修改；
##### 以下为目录，持续更新......
### DateUtil
 * int类型转为日期
 * 从日期中获取年/月/日
 * 字符串转日期：2015-03-24,2015/03/24,2015年3月24日,2015.03.34
 * 日期转字符串 如：2017年7月1日
 * 日期转字符串 如：2017-7-1
 * 获取当前年
 * 获取当前月
### BinUtil
 * 文件转为二进制
 * 文件转为二进制字符串
 * 二进制字符串转文件
 * 文件转为二进制数组
 * 文件转为base64字符串
 * base64字符串转为文件
 * MultipartFile 转为File
 * 解析多文件上传
 * 使用字节流复制文件
 * 使用字符流复制文件
### JsonUtil
 * map转json
 * Object转json
 * json转map
 * json转List<T>
 * json转到对象
 * json转List<Map<String, Object>>
 * List<Map<String,Object>>转json
 * 将json字符串转为Map结构（复杂json，嵌套结构都可以）
### ReflectUtil
 * 利用反射获取指定对象的指定属性
 * 利用反射获取指定对象里面的指定属性
 * 利用反射设置指定对象的指定属性为指定的值
 * 两者属性名一致时，拷贝source里的属性到dest里
 * 调用Getter方法
 * 直接调用对象方法, 无视private/protected修饰符
 * 循环向上转型, 获取对象的DeclaredMethod
 * 调用set方法把值set到对象当中
 * 通过class类型获取获取对应类型的值
 * 将对象中所有属性值为空字符串的，转换为null
### MyCollectionUtil
 * 根据map的value值排序List<Map>
 * 根据对象属性值排序List<Object>
### redis
 * 整合redis
 * redis存取String类型数据 IStringCache
 * redis存取Set结构类型数据 ISetCache
 * redis存取List结构类型数据 IListCache
 * redis存取Hash结构类型数据 IHashCache
### DateConstant
 * 常用的日期常量
### FormulaUtil
 * 计算同比 （a-b）/b
 * a / b   计算百分比
 * a / b
### 封装类
#### BaseInfo 常用封装类，查询结果封装
#### ResponseWrapper 常用封装类，返回前端标准格式
#### ReturnCode 常用封装类，枚举返回码
