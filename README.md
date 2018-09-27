# eduroamControlSystem-Backend

### 说明
该系统主要用于对eduroam网络的安全管控，旨在对本校学生在外使用eduroam网络时的行为进行威胁分析与安全管控，避免学生账号密码泄露等情况导致对本校网络安全与学生信息安全造成不良影响。

本项目基于spring boot框架并使用maven进行构建。

### 测试用账户
系统为方便测试，会在运行时自动创建一个管理员(用户名与密码均为devAdmin)和一个普通用户(用户名与密码均为devUser), 代码写在[DevConfig文件中](https://github.com/UPC-eduroam/eduroamControlSystem-Backend/blob/fc8da15d242722a3d05d8c6c63f416e951fcabc5/src/main/java/cn/edu/upc/eduroamcontrolsystembackend/security/config/DevConfig.java#L17), 如果不需要请删除或注释掉该文件。

### License
[GPL-3.0](https://github.com/UPC-eduroam/eduroamControlSystem-Backend/blob/master/LICENSE)
