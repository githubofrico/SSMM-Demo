package cn.edu.tju.rico.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Title: 序列化/反序列化工具，负责byte[]和Java 对象之间的相互转换
 * Description:借助于JDK提供的序列化技术，使Redis能够对Java对象进行缓存 1.
 * 要求缓存实体(即Java对象)实现序列化Serializable接口； 2.
 * 序列化工具类：Jedis对序列化的支持，是提供了字节数组byte[]作为参数。
 * 
 * @author rico
 * @created 2017年6月27日 上午11:13:34
 */

public class SerializeUtil {

	/**
	 * @description 序列化 
	 * @author rico
	 * @created 2017年6月27日 上午11:13:22
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj) {

		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;

		try {
			// 字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);

			// 对指定的obj对象进行序列化，把得到的字节序列写到一个目标输出流中
			oos.writeObject(obj);
			// 创建一个新分配的字节数组，数组的大小和当前输出流的大小相同，内容是当前输出流的拷贝
			byte[] byteArray = baos.toByteArray();
			return byteArray;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @description 反序列化
	 * @author rico
	 * @created 2017年6月27日 上午11:13:26
	 * @param bytes
	 * @return
	 */
	public static Object unSerialize(byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			// 从源输入流中读取字节序列，再将其反序列化成为一个Java对象并返回
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
