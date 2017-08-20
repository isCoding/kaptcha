# kaptcha - A kaptcha generation engine.

### 原版本可参考fork源.本版本在fork的版本上扩展了一个自定义背景图片的实现,可通过简单的配置增加一个图片作为验证码背景.

#### 安装简略说明:  
1.在本地测试时, 请先安装Gradle, 编译生成jar包.  
2.将项目中附带的pom.xml放至jar包生成同级下, cmd进入同级目录.  
3.使用如下maven命令将生成的jar安装至本地maven库中:  
```
mvn install:install-file -Dfile=kaptcha-2.3.2.jar -DgroupId=cn.codeh -DartifactId=kaptcha -Dversion=2.3.3 -Dpackaging=jar

mvn install:install-file -Dfile=pom.xml -DgroupId=cn.codeh -DartifactId=kaptcha -Dversion=2.3.3 -Dpackaging=pom
```
以上命令应成功安装jar至本地maven, 需使用本项目的则可以如下pom引入:
```
<dependency>
    <groupId>cn.codeh</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3.3</version>
</dependency>
```
以上为本地引用测试时的简单说明, 如使用时公司有私服, 可上传私服, 或按照fork源版本, 上传至中央仓库使用.  

#### 使用简略说明:  
1.修改spring配置, 以下为参考配置:  
```xml
<bean id="defaultKaptcha" class="com.google.code.kaptcha.impl.DefaultKaptcha" >
        <property name="config">
            <bean class="com.google.code.kaptcha.util.Config">
                <constructor-arg>
                    <props>
                        <!-- 验证码宽度 -->
                        <prop key="kaptcha.image.width">110</prop>
                        <!-- 验证码高度 -->
                        <prop key="kaptcha.image.height">50</prop>
                        <!-- 生成验证码内容范围 -->
                        <prop key="kaptcha.textproducer.char.string">0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ</prop>
                        <!-- 验证码个数 -->
                        <prop key="kaptcha.textproducer.char.length">4</prop>
                        <!-- 是否有边框 -->
                        <prop key="kaptcha.border">no</prop>
                        <!-- 边框颜色 -->
                        <prop key="kaptcha.border.color">105,179,90</prop>
                        <!-- 边框厚度 -->
                        <prop key="kaptcha.border.thickness">1</prop>
                        <!-- 验证码字体颜色 -->
                        <prop key="kaptcha.textproducer.font.color">black</prop>
                        <!-- 验证码字体大小 -->
                        <prop key="kaptcha.textproducer.font.size">30</prop>
                        <!-- 验证码所属字体样式 -->
                        <prop key="kaptcha.textproducer.font.names">楷体</prop>
                        <!-- 干扰线颜色 -->
                        <prop key="kaptcha.noise.color">black</prop>
                        <!-- 验证码文本字符间距 -->
                        <prop key="kaptcha.textproducer.char.space">5</prop>
                        <!-- 文字样式 :阴影-->
                        <prop key="kaptcha.obscurificator.impl">com.google.code.kaptcha.impl.ShadowGimpy</prop>
                        <!--文字渲染-->
                        <prop key="kaptcha.word.impl">com.google.code.kaptcha.text.impl.DefaultWordRenderer</prop>
                        <!--自定义背景处理类-->
                        <prop key="kaptcha.background.impl">com.google.code.kaptcha.impl.CustomBackgroundImage</prop>
                        <!--自定义背景路径-->
                        <prop key="kaptcha.background.image">image/kaptchaBG.jpg</prop>
                    </props>
                </constructor-arg>
            </bean>
        </property>
    </bean>
```
其中:  
```
<!--自定义背景处理类-->
<prop key="kaptcha.background.impl">com.google.code.kaptcha.impl.CustomBackgroundImage</prop>
<!--自定义背景路径-->
<prop key="kaptcha.background.image">image/kaptchaBG.jpg</prop>
```
为扩展新增配置, `image/kaptchaBG.jpg`代表项目实际路径:`resources/image/kpatchaBG.jpg`  
如此项不配置, 则寻找jvm环境变量`kaptchaBG`配置的图片路径.  
如也寻找不到, 则默认为项目`resources`路径下寻找名称为`kaptchaBG.jpg`的图片.  
如图片未配置, 则背景图片为黑色背景.(chrome下显示, 由于我是按照jpg返回的, 如果是png格式返回前台, 则可能为透明底, 未测试)





以下为原README:  
>This repo is the copy of http://code.google.com/p/kaptcha/ and published to maven central
>```
><dependency>
>  <groupId>com.github.penggle</groupId>
>  <artifactId>kaptcha</artifactId>
>  <version>2.3.2</version>
></dependency>
>```
>Please see the website for more information about this project.
>
>http://code.google.com/p/kaptcha/
>
>thanks!
