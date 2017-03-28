/**
 *
 *
 #### jibx 1.2.6 用到的lib下载地址
 ###  https://sourceforge.net/projects/jibx/files/jibx/jibx-1.3.1/
 ###cmd下运行
 cd 到 工程目录下
 cd  E:\code\myGit\experiment\
 mvn compile
 cd target/classes                                                                                                                      Order类包含了所有类，只需对该类执行即可
 java -Djava.ext.dirs=E:\JD\software\development_software\jibx_1_3_1\jibx\lib    org.jibx.binding.generator.BindGen -b bind.xml nettyTest.NIOTest.pojo.Order
 cd ../..
 mvn jibx:bind
 *
 */
package nettyTest.NIOTest.pojo;